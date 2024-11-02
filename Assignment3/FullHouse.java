public class FullHouse extends Hand{
    private int[] rankCount;
    private int TripleRank;
    public FullHouse(CardGamePlayer player, CardList cards){
        super(player, cards);
        rankCount = new int[13];
        for (int i = 0; i < 5; i++) {
            rankCount[cards.getCard(i).getRank()]++;
        }
        for (int i = 0; i < 5; i++) {
            if (rankCount[cards.getCard(i).getRank()] == 3) {
                TripleRank = i;
                break;
            }
        }
    }

    private BigTwoCard findTripletMax(BigTwoCard card1, BigTwoCard card2, BigTwoCard card3){
        if (card1.compareTo(card2) == 1 && card1.compareTo(card3) == 1){
            return card1;
        }
        else if (card2.compareTo(card1) == 1 && card2.compareTo(card3) == 1){
            return card2;
        }
        else{
            return card3;
        }
    }

    @Override
    public boolean isValid(){
        if(this.size() != 5){
            return false;
        }
        boolean triplettrue = false;
        boolean pairtrue = false;
        for (int i = 0; i < 13; i++){
            if (rankCount[i] == 3){
                triplettrue = true;
            }
            else if (rankCount[i] == 2){
                pairtrue = true;
            }
        }
        return triplettrue && pairtrue;
    }

    @Override
    public String getType(){
        return "FullHouse";
    }

    @Override
    public Card getTopCard() {
        int maxTripleSuit = 0;
        for (int i = 0; i < 5; i++) {
            if (this.getCard(i).getRank() == TripleRank && this.getCard(i).getSuit() > maxTripleSuit) {
                maxTripleSuit = this.getCard(i).getSuit();
            }
        }
        return new Card(maxTripleSuit, TripleRank);
    }
}
