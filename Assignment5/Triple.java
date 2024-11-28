/**
 * Triple is a subclass of Hand which is used to model a hand of triple in a Big Two card game.
 * It overrides the isValid() and getType() methods it inherits from Hand.
 * @author Sonny Wong
 */
public class Triple extends Hand{
    /**
     * a constructor for building a Triple with the specified player and list of cards.
     * @param player the player who plays this hand
     * @param cards the list of cards
     */
    public Triple(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    /**
     * a method for checking if this is a valid hand.
     * @return true if the hand is a valid triple, false otherwise
     */
    @Override
    public boolean isValid(){
        if(this.size() != 3){
            return false;
        }
        Card card1 = this.getCard(0);
        Card card2 = this.getCard(1);
        Card card3 = this.getCard(2);
        return card1.getRank() == card2.getRank() && card2.getRank() == card3.getRank();
    }

    /**
     * a method for returning a string specifying the type of this hand.
     * @return a string specifying the type of hand, "Triple"
     */
    @Override
    public String getType(){
        return "Triple";
    }

}
