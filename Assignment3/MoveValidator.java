import java.util.ArrayList;
import java.util.Arrays;

/**
 * The MoveValidator class is used to validate the move made by the player.
 */
public class MoveValidator {
    private final BigTwoUI ui;
    private final ArrayList<Hand> handsOnTable;
    private final ArrayList<CardGamePlayer> playerList;

    /**
     * Constructor for MoveValidator.
     * @param ui BigTwoUI object
     * @param handsOnTable ArrayList of Hand objects
     * @param playerList ArrayList of CardGamePlayer objects
     */
    public MoveValidator(BigTwoUI ui, ArrayList<Hand> handsOnTable, ArrayList<CardGamePlayer> playerList) {
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
    }
}

