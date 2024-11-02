/**
 * The Hand class is a subclass of the CardList class and is used to model a hand of cards. It has
 * a private instance variable for storing the player who plays this hand. It also has methods for
 * getting the player of this hand, checking if it is a valid hand, getting the type of this hand,
 * getting the top card of this hand, and checking if it beats a specified hand.
 */
public abstract class Hand extends CardList {
    private final CardGamePlayer player;

    /**
     * a constructor for building a hand with the specified player and list of cards.
     * @param player the player who plays this hand
     * @param cards  the list of cards
     */
    public Hand(CardGamePlayer player, CardList cards){
        this.player = player;
        for(int i = 0; i < cards.size(); i++){
            this.addCard(cards.getCard(i));
        }
    }
    
    /**
     * a method for retrieving the player of this hand
     * @return the player of this hand
     */
    public CardGamePlayer getPlayer(){
        return this.player;
    }

    /**
     * a method for retrieving the top card of this hand
     * @return the top card of this hand
     */
    public Card getTopCard(){
        this.sort();
        return this.getCard(this.size()-1);
    }

    /**
     * – a method for checking if this hand beats a specified hand
     * @param hand the target hand to compare with
     * @return true if this hand beats the target hand, false otherwise
     */
    public boolean beats(Hand hand){
        if(this.size() != hand.size()){
            return false;
        }
        BigTwoCard topCard = new BigTwoCard(this.getTopCard().getSuit(), this.getTopCard().getRank());
        return topCard.compareTo(hand.getTopCard()) > 0;
    }

    /**
     * – a method for checking if this is a valid hand
     * @return true if this is a valid hand, false otherwise
     */
    abstract public boolean isValid();

    /**
     * – a method for checking if this is a valid hand
     * @return the type of this hand
     */
    abstract public String getType();
}
