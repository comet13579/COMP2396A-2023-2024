/**
 * FullHouse class is a subclass of Hand and is used to model a hand of fullhouse in a Big Two card game.
 * It overrides the isValid(), getType(), getTopCard(), and beats() methods it inherits from Hand.\
 * @author Sonny Wong
 */
public class FullHouse extends Hand{
    private int[] rankCount;
    private int TripleRank;
    /**
     * a constructor for building a FullHouse with the specified player and list of cards.
     * @param player the player who plays this hand
     * @param cards the list of cards
     */
    public FullHouse(CardGamePlayer player, CardList cards){
        super(player, cards);
        rankCount = new int[13];
        for (int i = 0; i < 5; i++) {
            rankCount[cards.getCard(i).getRank()]++;
        }
        for (int i = 0; i < 5; i++) {
            if (rankCount[cards.getCard(i).getRank()] == 3) {
                TripleRank = cards.getCard(i).getRank();
                break;
            }
        }
    }

    /**
     * a method for checking if this is a valid hand.
     * @return true if the hand is a valid fullhouse, false otherwise
     */
    @Override
    public boolean isValid(){
        if(this.size() != 5){
            return false;
        }
        boolean triplettrue = false;
        boolean pairtrue = false;
        for (int i = 0; i < 13; i++){
            if (rankCount[i] == 3){
                triplettrue = true;
            }
            else if (rankCount[i] == 2){
                pairtrue = true;
            }
        }
        return triplettrue && pairtrue;
    }

    /**
     * a method for returning a string specifying the type of this hand.
     * @return a string specifying the type of hand, "FullHouse"
     */
    @Override
    public String getType(){
        return "FullHouse";
    }

    /**
     * a method for retrieving the top card of this hand
     * @return the top card of this hand
     */
    @Override
    public Card getTopCard() {
        int maxTripleSuit = 0;
        for (int i = 0; i < 5; i++) {
            if (this.getCard(i).getRank() == TripleRank && this.getCard(i).getSuit() > maxTripleSuit) {
                maxTripleSuit = this.getCard(i).getSuit();
            }
        }
        return new Card(maxTripleSuit, TripleRank);
    }
    
     /**
     * a method for checking if this hand beats a specified hand
     *  @param hand the target hand to compare with
     */
    @Override
    public boolean beats(Hand hand){
        if (hand.size() != 5){
            return false;
        }
        if (hand.getType().equals("Flush") || hand.getType().equals("Straight")){
            return true;
        }
        if (hand.getType().equals("FullHouse")){
            return this.getTopCard().compareTo(hand.getTopCard()) > 0;
        }
        BigTwoCard topCard = new BigTwoCard(this.getTopCard().getSuit(), this.getTopCard().getRank());
        return topCard.compareTo(hand.getTopCard()) > 0;
    }
}
