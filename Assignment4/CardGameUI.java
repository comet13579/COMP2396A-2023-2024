/**
 * An interface for a general card game user interface
 * 
 * @author Kenneth Wong
 * 
 */
public interface CardGameUI {
	/**
	 * Sets the index of the active player (i.e., the current player).
	 * 
	 * @param activePlayer an int value representing the index of the active player
	 */
	public void setActivePlayer(int activePlayer);

	/**
	 * Repaints the user interface.
	 */
	public void repaint();

	/**
	 * Prints the specified string to the message area of the card game user
	 * interface.
	 * 
	 * @param msg the string to be printed to the message area of the card game user
	 *            interface
	 */
	public void printMsg(String msg);

	/**
	 * Clears the message area of the card game user interface.
	 */
	public void clearMsgArea();

	/**
	 * Resets the card game user interface.
	 */
	public void reset();

	/**
	 * Enables user interactions.
	 */
	public void enable();

	/**
	 * Disables user interactions.
	 */
	public void disable();

	/**
	 * Prompts active player to select cards and make his/her move.
	 */
	public void promptActivePlayer();
}
