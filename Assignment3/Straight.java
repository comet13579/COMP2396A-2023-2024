public class Straight extends Hand{
    public Straight(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    @Override
    public boolean isValid() {
        if (this.size() != 5) {
            return false;
        }

        this.sort();
        BigTwoCard currentCard;
        BigTwoCard nextCard;
        for (int i = 0; i < 4; i++) {
            currentCard = (BigTwoCard) this.getCard(i);
            nextCard = (BigTwoCard) this.getCard(i + 1);
            if (currentCard.getRank() != nextCard.getRank() - 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getType(){
        return "Straight";
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
