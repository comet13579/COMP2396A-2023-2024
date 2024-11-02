/**
 * Pair is a subclass of Hand which is used to model a hand of pair in a Big Two card game.
 * It overrides the isValid() and getType() methods it inherits from Hand.
 * @author Sonny Wong
 */
public class Pair extends Hand{
    /**
     * a constructor for building a Pair with the specified player and list of cards.
     * @param player the player who plays this hand
     * @param cards the list of cards
     */
    public Pair(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    /**
     * a method for checking if this is a valid hand.
     * @return true if the hand is a valid pair, false otherwise
     */
    @Override
    public boolean isValid(){
        if(this.size() != 2){
            return false;
        }
        Card card1 = this.getCard(0);
        Card card2 = this.getCard(1);
        return card1.getRank() == card2.getRank();
    }

    /**
     * a method for returning a string specifying the type of this hand.
     * @return a string specifying the type of hand, "Pair"
     */
    @Override
    public String getType(){
        return "Pair";
    }
}
