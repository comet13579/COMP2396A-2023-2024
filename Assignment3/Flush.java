public class Flush extends Hand{
    public Flush(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    
    @Override
    public boolean isValid(){
        if(this.size() != 5){
            return false;
        }
        int suit = this.getCard(0).getSuit();
        for(int i = 1; i < 5; i++){
            if(this.getCard(i).getSuit() != suit){
                return false;
            }
        }
        return true;
    }

    @Override
    public String getType(){
        return "Flush";
    }

    @Override
    public Card getTopCard(){
        BigTwoCard maxCard = new BigTwoCard(this.getCard(0).getSuit(), this.getCard(0).getRank());
        for (int i = 1; i < 5; i++) {
            BigTwoCard currentCard = new BigTwoCard(this.getCard(i).getSuit(), this.getCard(i).getRank());
            if (maxCard.compareTo(currentCard) == 1) {
                maxCard = currentCard;
            }
        }
        return maxCard;
    }

    @Override
    public boolean beats(Hand hand){
        if (hand.size() != 5){
            return false;
        }
        if (hand.getType().equals("Straight")){
            return true;
        }
        if (hand.getType().equals("Flush")){
            BigTwoCard topCard = new BigTwoCard(this.getTopCard().getSuit(), this.getTopCard().getRank());
            return topCard.compareTo(hand.getTopCard()) > 0;
        }
        return false;
    }
}
