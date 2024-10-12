public class Quad extends Hand{
    public Quad(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

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
        if(card2.getRank() == card3.getRank() && card3.getRank() == card4.getRank() && card4.getRank() == card5.getRank()){
            return true;
        }
        return false;
    }
    
    @Override
    public String getType(){
        return "Single";
    }
}
