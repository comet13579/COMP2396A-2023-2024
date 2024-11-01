public class Quad extends Hand{
    public Quad(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    private BigTwoCard findQuadMax(BigTwoCard card1, BigTwoCard card2, BigTwoCard card3, BigTwoCard card4){
        if (card1.compareTo(card2) == 1 && card1.compareTo(card3) == 1 && card1.compareTo(card4) == 1){
            return card1;
        }
        else if (card2.compareTo(card1) == 1 && card2.compareTo(card3) == 1 && card2.compareTo(card4) == 1){
            return card2;
        }
        else if (card3.compareTo(card1) == 1 && card3.compareTo(card2) == 1 && card3.compareTo(card4) == 1){
            return card3;
        }
        else{
            return card4;
        }
    }

    @Override
    public boolean isValid(){
        if(this.size() != 5){
            return false;
        }
        Card card1 = this.getCard(0);
        Card card2 = this.getCard(1);
        Card card3 = this.getCard(2);
        Card card4 = this.getCard(3);
        Card card5 = this.getCard(4);
        if(card1.getRank() == card2.getRank() && card2.getRank() == card3.getRank() && card3.getRank() == card4.getRank()){
            return true;
        }
        return card2.getRank() == card3.getRank() && card3.getRank() == card4.getRank() && card4.getRank() == card5.getRank();
    }
    
    @Override
    public String getType(){
        return "Single";
    }

    @Override
    public Card getTopCard(){
        this.sort();
        BigTwoCard card1 =  (BigTwoCard) this.getCard(0);
        BigTwoCard card2 =  (BigTwoCard) this.getCard(1);
        BigTwoCard card3 =  (BigTwoCard) this.getCard(2);
        BigTwoCard card4 =  (BigTwoCard) this.getCard(3);
        BigTwoCard card5 =  (BigTwoCard) this.getCard(4);
        if (card1.getSuit() == card2.getSuit()){
            return findQuadMax(card1, card2, card3,card4);
        }
        else{
            return findQuadMax(card2, card3, card4, card5);
        }
    }
}
