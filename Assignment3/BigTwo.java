import java.util.ArrayList;

/**
 * The BigTwo class implements the CardGame interface and is used to model a Big Two card
 * game. It has private instance variables for storing the number of players, a deck of cards, a
 * list of players, a list of hands played on the table, an index of the current player, and a user
 * interface.
 * 
 * @author Sonny Wong
 */
public class BigTwo {
    private int numberOfPlayers;
    private Deck deck;
    private ArrayList<CardGamePlayer> playerList;
    private ArrayList<Hand> handsOnTable;
    private int currentPlayerIdx;
    private BigTwoUI ui;

    private void nextPlayer() {
        currentPlayerIdx = (currentPlayerIdx + 1) % 4;
        ui.setActivePlayer(currentPlayerIdx);
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
        handsOnTable = new ArrayList<>();
        ui = new BigTwoUI(this);
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

        while (!endOfGame()){
            playerList.get(currentPlayerIdx).sortCardsInHand();
            ui.repaint();
            ui.promptActivePlayer();
        }
        ui.printMsg("Game ends\n");
        for (i = 0; i < 4; i++) {
            if (playerList.get(i).getNumOfCards() != 0) {
                ui.printMsg(playerList.get(i).getName() + " wins the game.\n");
            }
            else{
                ui.printMsg(playerList.get(i).getName() + " wins the game\n");
            }
        }
    }

    /**
     * a method for checking a move made by a player.
     * @param playerIdx the player index
     * @param cardIdx   the list of indices of the cards selected by the player
     */
    public void checkMove(int playerIdx, int[] cardIdx) {
        MoveValidator validator = new MoveValidator(ui, handsOnTable, playerList);
        if (validator.validateMove(playerIdx, cardIdx)) {
            nextPlayer();
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
        checkMove(playerIdx, cardIdx);
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
     * – a method for starting a Big Two card game. It should (i)
     * create a Big Two card game, (ii) create and shuffle a deck of cards, and (iii) start the
     * game with the deck of cards.
     * @param args not used
     */
    public static void main(String[] args) {
        BigTwo game = new BigTwo();
        Deck deck = new BigTwoDeck();
        deck.shuffle();
        game.start(deck);
    }

    /**
     *  a method for
     *  returning a valid hand from the specified list of cards of the player. Returns null if no
     *  valid hand can be composed from the specified list of cards.
     */
    public static Hand composeHand(CardGamePlayer player, CardList cards) {
        if (cards != null) {
            try {
                StraightFlush straightFlush = new StraightFlush(player, cards);
                if (straightFlush.isValid()) return straightFlush;
            } catch (Exception e) {}
            
            try {
                Single single = new Single(player, cards);
                if (single.isValid()) return single;
            } catch (Exception e) {}
        
            try {
                Pair pair = new Pair(player, cards);
                if (pair.isValid()) return pair;
            } catch (Exception e) {}
        
            try {
                Triple triple = new Triple(player, cards);
                if (triple.isValid()) return triple;
            } catch (Exception e) {}
        
            try {
                Straight straight = new Straight(player, cards);
                if (straight.isValid()) return straight;
            } catch (Exception e) {}
        
            try {
                Flush flush = new Flush(player, cards);
                if (flush.isValid()) return flush;
            } catch (Exception e) {}
        
            try {
                FullHouse fullHouse = new FullHouse(player, cards);
                if (fullHouse.isValid()) return fullHouse;
            } catch (Exception e) {}
        
            try {
                Quad quad = new Quad(player, cards);
                if (quad.isValid()) return quad;
            } catch (Exception e) {}
        
        }
        return null;
    }

}
