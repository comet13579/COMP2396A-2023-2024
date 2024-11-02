public abstract class Hand extends CardList {
    private final CardGamePlayer player;

    public Hand(CardGamePlayer player, CardList cards){
        this.player = player;
        for(int i = 0; i < cards.size(); i++){
            this.addCard(cards.getCard(i));
        }
    }

    public CardGamePlayer getPlayer(){
        return this.player;
    }

    public Card getTopCard(){
        this.sort();
        return this.getCard(this.size()-1);
    }

    public boolean beats(Hand hand){
        if(this.size() != hand.size()){
            return false;
        }
        BigTwoCard topCard = new BigTwoCard(this.getTopCard().getSuit(), this.getTopCard().getRank());
        return topCard.compareTo(hand.getTopCard()) > 0;
    }

    abstract public boolean isValid();

    abstract public String getType();
}
