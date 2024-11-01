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
        if(card1.getRank() == card2.getRank() && card2.getRank() == card3.getRank()){
            return true;
        }
        return false;
    }

    @Override
    public String getType(){
        return "Triple";
    }
    
    @Override
    public Card getTopCard(){
        BigTwoCard card1 =  (BigTwoCard) this.getCard(0);
        BigTwoCard card2 =  (BigTwoCard) this.getCard(1);
        BigTwoCard card3 =  (BigTwoCard) this.getCard(2);
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
