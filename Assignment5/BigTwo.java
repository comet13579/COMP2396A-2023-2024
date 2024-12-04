import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * The BigTwo class implements the CardGame interface and is used to model a Big Two card
 * game. It has private instance variables for storing the number of players, a deck of cards, a
 * list of players, a list of hands played on the table, an index of the current player, and a user
 * interface.
 * 
 * @author Sonny Wong
 */
public class BigTwo {
    private final int numberOfPlayers;
    private Deck deck;
    private final ArrayList<CardGamePlayer> playerList;
    private final ArrayList<Hand> handsOnTable;
    private int currentPlayerIdx;
    private final BigTwoGUI ui;
    private final BigTwoClient client;

    private void nextPlayer() {
        currentPlayerIdx = (currentPlayerIdx + 1) % 4;
        ui.setActivePlayer(currentPlayerIdx);
        ui.promptActivePlayer();
    }

    /**
     * a private class for checking a move made by a player.
     */
    private class MoveValidator {
        private final BigTwoGUI ui;
        private final ArrayList<Hand> handsOnTable;
        private final ArrayList<CardGamePlayer> playerList;

        /**
         * Constructor for MoveValidator.
         * @param ui BigTwoUI object
         * @param handsOnTable ArrayList of Hand objects
         * @param playerList ArrayList of CardGamePlayer objects
         */
        public MoveValidator(BigTwoGUI ui, ArrayList<Hand> handsOnTable, ArrayList<CardGamePlayer> playerList) {
            this.ui = ui;
            this.handsOnTable = handsOnTable;
            this.playerList = playerList;
        }

        /**
         * Validates the move made by the player.
         * @param playerIdx index of the player
         * @param cardIdx array of indices of the cards
         * @return true if the move is valid, false otherwise
         */
        public boolean validateMove(int playerIdx, int[] cardIdx) {
            if (cardIdx == null) {
                return validatePassMove(playerIdx);
            }
        
            if (handsOnTable.isEmpty()) {
                return validateFirstMove(playerIdx, cardIdx);
            }
        
            return validateRegularMove(playerIdx, cardIdx);
        }

        private boolean validatePassMove(int playerIdx) {
            if (handsOnTable.isEmpty()) {
                ui.printMsg("Not a legal move!!!\n");
                return false;
            }
        
            if (isLastPlayedByCurrentPlayer(playerIdx)) {
                ui.printMsg("Not a legal move!!!\n");
                return false;
            }
        
            ui.printMsg("{Pass}\n");
            return true;
        }

        private boolean validateFirstMove(int playerIdx, int[] cardIdx) {
            Arrays.sort(cardIdx);
            if (cardIdx[0] != 0) {
                ui.printMsg("Not a legal move!!!\n");
                return false;
            }
        
            Hand tempHand = BigTwo.composeHand(playerList.get(playerIdx), playerList.get(playerIdx).play(cardIdx));
            if (tempHand == null) {
                ui.printMsg("Not a legal move!!!\n");
                return false;
            }
        
            executeMove(playerIdx, tempHand);
            return true;
        }

        private boolean validateRegularMove(int playerIdx, int[] cardIdx) {
            Hand tempHand = BigTwo.composeHand(playerList.get(playerIdx), playerList.get(playerIdx).play(cardIdx));
            if (tempHand == null) {
            ui.printMsg("Not a legal move!!!\n");
            return false;
        }
        
            if (!isLastPlayedByCurrentPlayer(playerIdx) && !tempHand.beats(handsOnTable.get(handsOnTable.size() - 1))) {
                ui.printMsg("Not a legal move!!!\n");
                return false;
            }
            executeMove(playerIdx, tempHand);
            return true;
        }

        private boolean isLastPlayedByCurrentPlayer(int playerIdx) {
            return handsOnTable.get(handsOnTable.size() - 1).getPlayer().getName()
            .equals(playerList.get(playerIdx).getName());
        }

        private void executeMove(int playerIdx, Hand hand) {
            handsOnTable.add(hand);
            playerList.get(playerIdx).removeCards(hand);
            ui.printMsg(hand.toString() + "\n");
        }
    }



    /**
     * a constructor for creating a Big Two card game. You should (i) create 4
     * players and add them to the player list; and (ii) create a BigTwoUI object for providing
     * the user interface.
     */
    public BigTwo() {
        numberOfPlayers = 4;
        playerList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            playerList.add(new CardGamePlayer());
        }
        this.ui = new BigTwoGUI(this);
        this.client = new BigTwoClient(this, ui);
        handsOnTable = new ArrayList<>();
        client.setServerIP("127.0.0.1");
        client.setServerPort(2396);
        String name = JOptionPane.showInputDialog("Enter your name:");
        if (name == null || name.equals("")) {
            name = "Nameless";
        }
        client.setPlayerName(name);
    }

    /**
     * a method for getting the number of players.
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * – a method for retrieving the deck of cards being used.
     * @return the deck of cards being used
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * a method for retrieving the list of players.
     * @return the list of players
     */
    public ArrayList<CardGamePlayer> getPlayerList() {
        return playerList;
    }

    /**
     * a method for retrieving the list of hands played on the table.
     * @return the list of hands played on the table
     */
    public ArrayList<Hand> getHandsOnTable() {
        return handsOnTable;
    }

    /**
     * a method for retrieving the index of the current player.
     * @return the index of the current player
     */
    public int getCurrentPlayerIdx() {
        return currentPlayerIdx;
    }

    /**
     * a method for retrieving the index of this client (local).
     * @return the index of this client
     */
    public int getPlayerIDclient() {
        return client.getPlayerID();
    }

    /**
     * a method of counting how many players are in the game.
     * @return the number of players in the game
     */
    public int getPlayerCount() {
        int count = 0;
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getName() != null && !playerList.get(i).getName().equals("") && !playerList.get(i).getName().equals("Player" + i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * a method for starting/restarting the game with a given
     * shuffled deck of cards. You should (i) remove all the cards from the players as well as
     * from the table; (ii) distribute the cards to the players; (iii) identify the player who holds
     * the Three of Diamonds; (iv) set both the currentPlayerIdx of the BigTwo object and
     * the activePlayer of the BigTwoUI object to the index of the player who holds the
     * Three of Diamonds; (v) call the repaint() method of the BigTwoUI object to show the
     * cards on the table; and (vi) call the promptActivePlayer() method of the BigTwoUI
     * object to prompt user to select cards and make his/her move.
     * @param deck the deck of cards to be used
     */
    public void start(Deck deck) {
        int i;
        for (i = 0; i < 4; i++) {
            playerList.get(i).removeAllCards();
        }

        handsOnTable.clear();
    
        for (i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                playerList.get(i).addCard(deck.getCard(i * 13 + j));
            }   
        }
    
        for (i = 0; i < 4; i++) {
            if (playerList.get(i).getCardsInHand().contains(new Card(0,2))) {
                currentPlayerIdx = i;
                ui.setActivePlayer(i);
                break;
            }
        }
        
        for (i = 0; i < 4; i++) {
            playerList.get(i).sortCardsInHand();
        }

        ui.repaint();
        ui.promptActivePlayer();
    }


    /**
     * a method for checking a move made by a player.
     * @param playerIdx the player index
     * @param cardIdx   the list of indices of the cards selected by the player
     */
    public void checkMove(int playerIdx, int[] cardIdx) {
        if (handsOnTable == null) {
            System.out.println("handsOnTable is null");
        }
        MoveValidator validator = new MoveValidator(this.ui, this.handsOnTable, this.playerList);
        if (validator.validateMove(playerIdx, cardIdx)) {
            nextPlayer();
            playerList.get(currentPlayerIdx).sortCardsInHand();
        }
        if (endOfGame()) {
            String displayEndMessage = "";
            displayEndMessage += "Game ends\n";
            for (int i = 0; i < 4; i++) {
                if (playerList.get(i).getNumOfCards() != 0) {
                    displayEndMessage += playerList.get(i).getName() + " looses the game.\n";
                }
                else {
                    displayEndMessage += playerList.get(i).getName() + " wins the game.\n";
                }
            }
            JOptionPane.showMessageDialog(null, displayEndMessage);
            ui.disable();
            client.sendMessage(new CardGameMessage(CardGameMessage.READY,-1,null));
        }
    }

    /**
     * a method for making a move by a
     *  player with the specified index using the cards specified by the list of indices. This
     *  method should be called from the BigTwoUI after the active player has selected cards
     *  to make his/her move.
     * @param playerIdx the player index
     * @param cardIdx   the list of indices of the cards selected by the player
     */
    public void makeMove(int playerIdx, int[] cardIdx) {
        client.sendMessage(new CardGameMessage(CardGameMessage.MOVE, playerIdx, cardIdx));
    }

    /**
     * a method for checking if the game ends.
     * @return true if the game ends; false otherwise
     */
    public boolean endOfGame() {
        for (CardGamePlayer player : playerList) {
            if (player.getNumOfCards() == 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * a method for connecting to the server by calling the connect() method of the client
     */
    public void connect() {
        EnterIPOption enterIP = new EnterIPOption();
        if (enterIP.getPort() != -1){
            client.setServerIP(enterIP.getIP());
            client.setServerPort(enterIP.getPort());
            client.connect();
            client.sendMessage(new CardGameMessage(CardGameMessage.START, -1, null));
        }
    }

    /**
     * a method for updating PlayerNames in playerList
     * @param playerNames the array of player names
     */
    public void updatePlayerNames(String[] playerNames){
        for (int i = 0; i < 4; i++) {
            if (playerNames[i] != null) {
                playerList.get(i).setName(playerNames[i]);
            }
        }
    }

    /**
     * a method for adding player names to the playerList of specific index
     */
    public void addPlayerNames(String name, int index){
        playerList.get(index).setName(name);
    }

    /**
     * a method for sending chats to the server
     */
    public void sendChat(String msg){
        client.sendMessage(new CardGameMessage(CardGameMessage.MSG,-1,msg));
    }

    /**
     * – a method for starting a Big Two card game. 
     * @param args not used
     */
    public static void main(String[] args) {
        new BigTwo();
    }

    /**
     *  a method for
     *  returning a valid hand from the specified list of cards of the player. Returns null if no
     *  valid hand can be composed from the specified list of cards.
     *  @param player the player who plays the hand
     *  @param cards  the list of cards to play
     */
    public static Hand composeHand(CardGamePlayer player, CardList cards) {
        if (cards != null) {
            StraightFlush straightFlush = new StraightFlush(player, cards);
            if (straightFlush.isValid()) return straightFlush;
            
            Single single = new Single(player, cards);
            if (single.isValid()) return single;
        
            Pair pair = new Pair(player, cards);
            if (pair.isValid()) return pair;
        
            Triple triple = new Triple(player, cards);
            if (triple.isValid()) return triple;
        
            Straight straight = new Straight(player, cards);
            if (straight.isValid()) return straight;
        
            Flush flush = new Flush(player, cards);
            if (flush.isValid()) return flush;
        
            FullHouse fullHouse = new FullHouse(player, cards);
            if (fullHouse.isValid()) return fullHouse;
        
            Quad quad = new Quad(player, cards);
            if (quad.isValid()) return quad;

        
        }
        return null;
    }

}
