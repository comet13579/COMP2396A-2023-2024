public class Hand extends CardList{
    private CardGamePlayer player;

    public Hand(CardGamePlayer player, CardList cards){
        this.player = player;
        for(int i = 0; i < cards.size(); i++){
            this.addCard(cards.getCard(i));
        }
    }

    public CardGamePlayer getPlayer(){
        return this.player;
    }

    public Card getTopCard(){
        return this.getCard(this.size()-1);
    }

    public boolean beats(Hand hand){

    }

    public boolean isValid(){
    }

    public String getType(){
    }
}
