public class Pair extends Hand{
    public Pair(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    @Override
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
        BigTwoCard card1 = new BigTwoCard(this.getCard(0).getSuit(), this.getCard(0).getRank());
        BigTwoCard card2 = new BigTwoCard(this.getCard(1).getSuit(), this.getCard(1).getRank());

        if (card1.compareTo(card2) == 1){
            return card1;
        }
        else{
            return card2;
        }
    }
}
