import java.util.ArrayList;
import java.util.Arrays;

public class BigTwo {
    private int numberOfPlayers;
    private Deck deck;
    private ArrayList<CardGamePlayer> playerList;
    private ArrayList<Hand> handsOnTable;
    private int currentPlayerIdx;
    private BigTwoUI ui;

    public BigTwo() {
        numberOfPlayers = 4;
        playerList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            playerList.add(new CardGamePlayer());
        }
        handsOnTable = new ArrayList<>();
        ui = new BigTwoUI(this);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<CardGamePlayer> getPlayerList() {
        return playerList;
    }

    public ArrayList<Hand> getHandsOnTable() {
        return handsOnTable;
    }

    public int getCurrentPlayerIdx() {
        return currentPlayerIdx;
    }

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

    public void makeMove(int playerIdx, int[] cardIdx) {
        checkMove(playerIdx, cardIdx);
    }

    public void checkMove(int playerIdx, int[] cardIdx) {
        if (cardIdx == null) {
            if (handsOnTable.isEmpty()) {
                ui.printMsg("Not a legal move!!!\n");
            } else {
                if (handsOnTable.get(handsOnTable.size() - 1).getPlayer().getName().equals(playerList.get(playerIdx).getName())) {
                    ui.printMsg("Not a legal move!!!\n");
                } 
                else {
                    ui.printMsg("{Pass}\n");
                    currentPlayerIdx = (currentPlayerIdx + 1) % 4;
                    ui.setActivePlayer(currentPlayerIdx);
                }
            }
        }
        else if (handsOnTable.isEmpty()){
            //sort cardIdx
            Arrays.sort(cardIdx);
            if (cardIdx[0] != 0){
                ui.printMsg("Not a legal move!!!\n");
            }
            else {
                Hand tempHand = composeHand(playerList.get(playerIdx), playerList.get(playerIdx).play(cardIdx));
                if (tempHand == null){
                    ui.printMsg("Not a legal move!!!\n");
                }
                else {
                    handsOnTable.add(tempHand);
                    playerList.get(playerIdx).removeCards(tempHand);
                    currentPlayerIdx = (currentPlayerIdx + 1) % 4;
                    ui.setActivePlayer(currentPlayerIdx);
                }

            }
        } 
        else {
            Hand tempHand = composeHand(playerList.get(playerIdx), playerList.get(playerIdx).play(cardIdx));
            if (tempHand == null){
                ui.printMsg("Not a legal move!!!\n");
            }
            else if (!handsOnTable.get(handsOnTable.size() - 1).getPlayer().getName().equals(playerList.get(playerIdx).getName()) &&
                    !tempHand.beats(handsOnTable.get(handsOnTable.size() - 1))){
                    ui.printMsg("Not a legal move!!!\n");
            }
            else{
                handsOnTable.add(tempHand);
                playerList.get(playerIdx).removeCards(tempHand);
                currentPlayerIdx = (currentPlayerIdx + 1) % 4;
                ui.setActivePlayer(currentPlayerIdx);
            }
        }
    }

    public boolean endOfGame() {
        for (CardGamePlayer player : playerList) {
            if (player.getNumOfCards() == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BigTwo game = new BigTwo();
        Deck deck = new BigTwoDeck();
        deck.shuffle();
        game.start(deck);
    }

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
