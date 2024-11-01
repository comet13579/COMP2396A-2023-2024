public class FullHouse extends Hand{
    public FullHouse(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    private BigTwoCard findTripletMax(BigTwoCard card1, BigTwoCard card2, BigTwoCard card3){
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

    public boolean isValid(){
        if(this.size() != 5){
            return false;
        }

        this.sort();
        Card card1 = this.getCard(0);
        Card card2 = this.getCard(1);
        Card card3 = this.getCard(2);
        Card card4 = this.getCard(3);
        Card card5 = this.getCard(4);
        if(card1.getRank() == card2.getRank() && card2.getRank() == card3.getRank()){
            if(card4.getRank() == card5.getRank() && card4.getRank() != card1.getRank()){
                return true;
            }
        }
        if(card1.getRank() == card2.getRank() && card1.getRank() != card3.getRank()){
            if(card3.getRank() == card4.getRank() && card4.getRank() == card5.getRank()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getType(){
        return "FullHouse";
    }

    @Override
    public Card getTopCard(){
        this.sort();
        BigTwoCard card1 =  (BigTwoCard) this.getCard(0);
        BigTwoCard card2 =  (BigTwoCard) this.getCard(1);
        BigTwoCard card3 =  (BigTwoCard) this.getCard(2);
        BigTwoCard card4 =  (BigTwoCard) this.getCard(3);
        BigTwoCard card5 =  (BigTwoCard) this.getCard(4);
        if (card2.getSuit() == card3.getSuit()){
            return findTripletMax(card1, card2, card3);
        }
        else{
            return findTripletMax(card3, card4, card5);
        }
    }
}
