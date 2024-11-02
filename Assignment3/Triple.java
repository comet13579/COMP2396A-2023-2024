public class Triple extends Hand{
    public Triple(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

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

    @Override
    public String getType(){
        return "Triple";
    }
    
    @Override
    public Card getTopCard(){
        BigTwoCard card1 = new BigTwoCard(this.getCard(0).getSuit(), this.getCard(0).getRank());
        BigTwoCard card2 = new BigTwoCard(this.getCard(1).getSuit(), this.getCard(1).getRank());
        BigTwoCard card3 = new BigTwoCard(this.getCard(2).getSuit(), this.getCard(2).getRank());
        if (card1.compareTo(card2) == 1 && card1.compareTo(card3) == 1){
            return card1;
        }
        else if (card2.compareTo(card1) == 1 && card2.compareTo(card3) == 1){
            return card2;
        }
        else{
            return card3;
        }
    }
}
