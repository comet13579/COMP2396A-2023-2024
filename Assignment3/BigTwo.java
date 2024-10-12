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
            Hand testHand = new Hand(player, cards);
            testHand = (Single) testHand;
            if (testHand.isValid()) {
                return testHand;
            }
            testHand = (Pair) testHand;
            if (testHand.isValid()) {
                return testHand;
            }
            testHand = (Triple) testHand;
            if (testHand.isValid()) {
                return testHand;
            }
            testHand = (Straight) testHand;
            if (testHand.isValid()) {
                return testHand;
            }
            testHand = (Flush) testHand;
            if (testHand.isValid()) {
                return testHand;
            }
            testHand = (FullHouse) testHand;
            if (testHand.isValid()) {
                return testHand;
            }
            testHand = (Quad) testHand;
            if (testHand.isValid()) {
                return testHand;
            }
            testHand = (StraightFlush) testHand;
            if (testHand.isValid()) {
                return testHand;
            }
        }
        return null;
    }

}
