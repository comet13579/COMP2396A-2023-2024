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
}
