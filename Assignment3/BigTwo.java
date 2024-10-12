import java.util.ArrayList;

public class BigTwo {
    private int numberOfPlayers;
    private Deck deck;
    private ArrayList<CardGamePlayer> playerList;
    private ArrayList<Hand> handsOnTable;
    private int currentPlayerIdx;
    private BigTwoUI ui;

    public BigTwo() {
        playerList = new ArrayList<CardGamePlayer>();
        playerList.add(new CardGamePlayer());
        playerList.add(new CardGamePlayer());
        playerList.add(new CardGamePlayer());
        playerList.add(new CardGamePlayer());
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
        int i = 0;
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

}
