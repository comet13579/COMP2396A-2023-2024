/**
 * Quad is a subclass of Hand and is used to model a hand of Quad in a Big Two card game.
 * It overrides the isValid(), getType(), getTopCard() and beats() methods it inherits from Hand.
 * @author Sonny Wong
 */
public class Quad extends Hand{
    private int[] rankCount;
    private int QuadRank;
    
    /**
     * a constructor for building a Quad with the specified player and list of cards.
     * @param player the player who plays this hand
     * @param cards the list of cards
     */
    public Quad(CardGamePlayer player, CardList cards){
        super(player, cards);
        rankCount = new int[13];
        for (int i = 0; i < 5; i++) {
            rankCount[cards.getCard(i).getRank()]++;
        }
        for (int i = 0; i < 5; i++) {
            if (rankCount[cards.getCard(i).getRank()] == 4) {
                QuadRank = i;
                break;
            }
        }
    }

    /**
     * a method for checking if this is a valid hand.
     * @return true if the hand is a valid quad, false otherwise
     */
    @Override
    public boolean isValid(){
        if(this.size() != 5){
            return false;
        }
        for (int i = 0; i < 13; i++){
            if (rankCount[i] == 4){
                return true;
            }
        }
        return false;
    }
    
    /**
     * a method for returning a string specifying the type of this hand.
     * @return a string specifying the type of hand, "Quad"
     */
    @Override
    public String getType(){
        return "Quad";
    }

    /**
     * a method for retrieving the top card of this hand
     * @return the top card of this hand
    */
    @Override
    public Card getTopCard(){
        return new Card(3, QuadRank);
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
            return false;
        }
        BigTwoCard topCard = new BigTwoCard(this.getTopCard().getSuit(), this.getTopCard().getRank());
        return topCard.compareTo(hand.getTopCard()) > 0;
    }   
}
