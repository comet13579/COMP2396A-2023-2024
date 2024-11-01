import java.util.ArrayList;

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
        ui.repaint();
        ui.promptActivePlayer();
    }

    public void makeMove(int playerIdx, int[] cardIdx) {
        checkMove(playerIdx, cardIdx);
    }

    public void checkMove(int playerIdx, int[] cardIdx) {

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
        Deck deck = new Deck();
        deck.shuffle();
        game.start(deck);
    }

    public static Hand composeHand(CardGamePlayer player, CardList cards) {
        if (cards != null) {
            Single testHand = (Single) cards;
            if (testHand.isValid()) {
                return testHand;
            }
            Pair testHand1 = (Pair) cards;
            if (testHand1.isValid()) {
                return testHand1;
            }
            Triple testHand2 = (Triple) cards;
            if (testHand2.isValid()) {
                return testHand2;
            }
            Straight testHand3 = (Straight) cards;
            if (testHand3.isValid()) {
                return testHand3;
            }
            Flush testHand4 = (Flush) cards;
            if (testHand4.isValid()) {
                return testHand4;
            }
            FullHouse testHand5 = (FullHouse) cards;
            if (testHand5.isValid()) {
                return testHand5;
            }
            Quad testHand6 = (Quad) cards;
            if (testHand6.isValid()) {
                return testHand6;
            }
            StraightFlush testHand7 = (StraightFlush) cards;
            if (testHand7.isValid()) {
                return testHand7;
            }
        }
        return null;
    }

}
