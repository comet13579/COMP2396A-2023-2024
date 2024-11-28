/**
 * Single is a subclass of Hand which is used to model a hand of single in a Big Two card game.
 * It overrides the isValid() and getType() methods it inherits from Hand.
 * @author Sonny Wong
 */
public class Single extends Hand{
    /**
     * a constructor for building a Single with the specified player and list of cards.
     * @param player the player who plays this hand
     * @param cards the list of cards
     */
    public Single(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    /**
     * a method for checking if this is a valid hand.
     * @return true if the hand is a valid single, false otherwise
     */
    @Override
    public boolean isValid(){
        return this.size() == 1;
    }

    /**
     * a method for returning a string specifying the type of this hand.
     * @return a string specifying the type of hand, "Single"
     */
    @Override
    public String getType(){
        return "Single";
    }
}
