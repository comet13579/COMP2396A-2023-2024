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
        if(card1.getRank() == card2.getRank()){
            return true;
        }
        return false;
    }

    @Override
    public String getType(){
        return "Pair";
    }
}
