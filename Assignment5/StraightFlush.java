/**
 * StraightFlush is a subclass of Hand and is used to model a hand of straight flush in a Big Two card game.
 * It overrides the isValid(), getType(), getTopCard() and beats() methods it inherits from Hand.
 * @author Sonny Wong
 */
public class StraightFlush extends Hand{
    private final Straight straight;
    private final Flush flush;
    
    /**
     * a constructor for building a StraightFlush with the specified player and list of cards.
     * @param player the player who plays this hand
     * @param cards the list of cards
     */
    public StraightFlush(CardGamePlayer player, CardList cards){
        super(player, cards);
        straight = new Straight(player, cards);
        flush = new Flush(player, cards);
    }

    /**
     * a method for checking if this is a valid hand.
     * @return true if the hand is a valid straight flush, false otherwise
     */
    @Override
    public boolean isValid(){
        return (straight.isValid() && flush.isValid());
    }

    /**
     * a method for returning a string specifying the type of this hand.
     * @return a string specifying the type of hand, "StraightFlush"
     */
    @Override
    public String getType(){
        return "StraightFlush";
    }

    /**
     * a method for retrieving the top card of this hand
     * @return the top card of this hand   
    */
    @Override
    public Card getTopCard(){
        return straight.getTopCard();
    }

    /**
     * a method for checking if this hand beats a specified hand
     * @param hand the target hand to compare to
     * @return true if this hand beats the target hand, false otherwise
     */
    @Override
    public boolean beats(Hand hand){
        if (hand.size() != 5){
            return false;
        }
        if (!hand.getType().equals("StraightFlush")){
            return true;
        }
        BigTwoCard topCard = new BigTwoCard(this.getTopCard().getSuit(), this.getTopCard().getRank());
        return topCard.compareTo(hand.getTopCard()) > 0;
    }
}
