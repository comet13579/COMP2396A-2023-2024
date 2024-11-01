public class Pair extends Hand{
    public Pair(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    public boolean isValid(){
        if(this.size() != 2){
            return false;
        }
        Card card1 = this.getCard(0);
        Card card2 = this.getCard(1);
        return card1.getRank() == card2.getRank();
    }

    @Override
    public String getType(){
        return "Pair";
    }

    @Override
    public Card getTopCard(){
        BigTwoCard card1 =  (BigTwoCard) this.getCard(0);
        BigTwoCard card2 =  (BigTwoCard) this.getCard(1);
        if (card1.compareTo(card2) == 1){
            return card1;
        }
        else{
            return card2;
        }
    }
}
