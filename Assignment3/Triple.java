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
    
}
