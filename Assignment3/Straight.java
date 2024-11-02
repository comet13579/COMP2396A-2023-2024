public class Straight extends Hand{
    public Straight(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    @Override
    public boolean isValid() {
        if (this.size() != 5) {
            return false;
        }
        int rankArray[] = new int[5];
        for (int i = 0; i < 5; i++) {
            int num = this.getCard(i).getRank();
            if (num < 2) {
                rankArray[i] = num + 13;
            } else {
                rankArray[i] = num;
            }
        }
        java.util.Arrays.sort(rankArray);
        for (int i = 0; i < 4; i++) {
            if (rankArray[i] + 1 != rankArray[i + 1]) {
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
        BigTwoCard maxCard = new BigTwoCard(this.getCard(0).getSuit(), this.getCard(0).getRank());  
        for (int i = 1; i < 5; i++) {
            BigTwoCard currentCard = new BigTwoCard(this.getCard(i).getSuit(), this.getCard(i).getRank());
            if (maxCard.getRank() < currentCard.getRank()) {
                maxCard = currentCard;
            }
        }
        return maxCard;
    }
}
