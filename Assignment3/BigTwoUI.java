import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class is used for modeling a user interface for the Big Two card game.
 * 
 * @author Kenneth Wong
 */
public class BigTwoUI {
	private final static int MAX_CARD_NUM = 13; // max. no. of cards each player holds
	private BigTwo game = null; // a BigTwo object
	private ArrayList<CardGamePlayer> playerList; // the list of players
	private ArrayList<Hand> handsOnTable; // the list of hands played on the
	private Scanner scanner; // the scanner for reading user in put
	private int activePlayer = -1; // the index of the active player
	private boolean[] selected = new boolean[MAX_CARD_NUM]; // selected cards

	/**
	 * Creates and returns an instance of the BigTwoUI class.
	 * 
	 * @param game a BigTwo object associated with this UI
	 */
	public BigTwoUI(BigTwo game) {
		this.game = game;
		playerList = game.getPlayerList();
		handsOnTable = game.getHandsOnTable();
		scanner = new Scanner(System.in);
	}

	/**
	 * Sets the index of the active player.
	 * 
	 * @param activePlayer the index of the active player (i.e., the player who can
	 *                     make a move)
	 */
	public void setActivePlayer(int activePlayer) {
		if (activePlayer < 0 || activePlayer >= playerList.size()) {
			this.activePlayer = -1;
		} else {
			this.activePlayer = activePlayer;
		}
	}

	/**
	 * Redraws the UI.
	 */
	public void repaint() {
		for (int i = 0; i < playerList.size(); i++) {
			CardGamePlayer player = playerList.get(i);
			String name = player.getName();
			if (activePlayer == i) {
				System.out.println("<" + name + ">");
				System.out.print("==> ");
				player.getCardsInHand().print(true, true);
			} else if (activePlayer == -1) {
				System.out.println("<" + name + ">");
				System.out.print("    ");
				player.getCardsInHand().print(true, true);
			} else {
				System.out.println("<" + name + ">");
				System.out.print("    ");
				player.getCardsInHand().print(false, true);
			}
		}
		System.out.println("<Table>");
		Hand lastHandOnTable = (handsOnTable.isEmpty()) ? null : handsOnTable.get(handsOnTable.size() - 1);
		if (lastHandOnTable != null) {
			System.out
					.print("    <" + lastHandOnTable.getPlayer().getName() + "> {" + lastHandOnTable.getType() + "} ");
			lastHandOnTable.print(true, false);
		} else {
			System.out.println("  [Empty]");
		}
	}

	/**
	 * Prints the specified string to the UI.
	 * 
	 * @param msg the string to be printed to the UI
	 */
	public void printMsg(String msg) {
		System.out.print(msg);
	}

	/**
	 * Clears the message area of the UI.
	 */
	public void clearMsgArea() {
		// not used in non-graphical UI
	}

	/**
	 * Resets the UI.
	 */
	public void reset() {
		// not used in non-graphical UI
	}

	/**
	 * Enables user interactions.
	 */
	public void enable() {
		// not used in non-graphical UI
	}

	/**
	 * Disables user interactions.
	 */
	public void disable() {
		// not used in non-graphical UI
	}

	/**
	 * Prompts active player to select cards and make his/her move.
	 */
	public void promptActivePlayer() {
		printMsg(playerList.get(activePlayer).getName() + "'s turn: ");
		int[] cardIdx = getSelected();
		resetSelected();
		game.makeMove(activePlayer, cardIdx);
	}
	
	/**
	 * Returns an array of indices of the cards selected through the UI.
	 * 
	 * @return an array of indices of the cards selected, or null if no valid cards
	 *         have been selected
	 */
	private int[] getSelected() {
		CardGamePlayer player = playerList.get(activePlayer);
		String input = scanner.nextLine();

		StringTokenizer st = new StringTokenizer(input);
		while (st.hasMoreTokens()) {
			try {
				int idx = Integer.parseInt(st.nextToken());
				if (idx >= 0 && idx < MAX_CARD_NUM && idx < player.getCardsInHand().size()) {
					selected[idx] = true;
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		int[] cardIdx = null;
		int count = 0;
		for (int j = 0; j < selected.length; j++) {
			if (selected[j]) {
				count++;
			}
		}

		if (count != 0) {
			cardIdx = new int[count];
			count = 0;
			for (int j = 0; j < selected.length; j++) {
				if (selected[j]) {
					cardIdx[count] = j;
					count++;
				}
			}
		}
		return cardIdx;
	}

	/**
	 * Resets the list of selected cards to an empty list.
	 */
	private void resetSelected() {
		for (int j = 0; j < selected.length; j++) {
			selected[j] = false;
		}
	}
}
