/**
 * Straight class is a subclass of Hand and is used to model a hand of straight in a Big Two card game.
 * It overrides the isValid(), getType(), getTopCard(), and beats() methods it inherits from Hand.
 * @author Sonny Wong
 */
public class Straight extends Hand{
    /**
     * a constructor for building a Straight with the specified player and list of cards.
     * @param player the player who plays this hand
     * @param cards the list of cards
     */
    public Straight(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    /**
     * a method for checking if this is a valid hand.
     * @return true if the hand is a valid straight, false otherwise
     */
    @Override
    public boolean isValid() {
        if (this.size() != 5) {
            return false;
        }
        int rankArray[] = new int[5];
        for (int i = 0; i < 5; i++) {
            int num = this.getCard(i).getRank();
            if (num < 2) {
                rankArray[i] = num + 13;
            } else {
                rankArray[i] = num;
            }
        }
        java.util.Arrays.sort(rankArray);
        for (int i = 0; i < 4; i++) {
            if (rankArray[i] + 1 != rankArray[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * a method for returning a string specifying the type of this hand.
     * @return a string specifying the type of hand, "Straight"
     */
    @Override
    public String getType(){
        return "Straight";
    }

    /**
     * a method for retrieving the top card of this hand
     * @return the top card of this hand
     */
    @Override
    public Card getTopCard(){
        BigTwoCard maxCard = new BigTwoCard(this.getCard(0).getSuit(), this.getCard(0).getRank());  
        for (int i = 1; i < 5; i++) {
            BigTwoCard currentCard = new BigTwoCard(this.getCard(i).getSuit(), this.getCard(i).getRank());
            if (maxCard.getRank() < currentCard.getRank()) {
                maxCard = currentCard;
            }
        }
        return maxCard;
    }

    /**
     * a method for checking if this hand beats a specified hand
     * @param hand the target hand to compare with
     * @return true if this hand beats the target hand, false otherwise
     */
    @Override
    public boolean beats(Hand hand){
        if (hand.size() != 5){
            return false;
        }
        if (!hand.getType().equals("Straight")){
            return false;
        }
        BigTwoCard topCard = new BigTwoCard(this.getTopCard().getSuit(), this.getTopCard().getRank());
        return topCard.compareTo(hand.getTopCard()) > 0;
    }
}
