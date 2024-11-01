public class Flush extends Hand{
    public Flush(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    
    public boolean isValid(){
        if(this.size() != 5){
            return false;
        }
        int suit = this.getCard(0).getSuit();
        for(int i = 1; i < 5; i++){
            if(this.getCard(i).getSuit() != suit){
                return false;
            }
        }
        return true;
    }

    @Override
    public String getType(){
        return "Flush";
    }

    @Override
    public Card getTopCard(){
        BigTwoCard maxCard = (BigTwoCard) this.getCard(0);  
        for (int i = 1; i < 5; i++) {
            BigTwoCard currentCard = (BigTwoCard) this.getCard(i);
            if (maxCard.compareTo(currentCard) == 1) {
                maxCard = currentCard;
            }
        }
        return maxCard;
    }
}
