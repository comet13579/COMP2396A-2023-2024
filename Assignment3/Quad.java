public class Quad extends Hand{
    private int[] rankCount;
    private int QuadRank;
    public Quad(CardGamePlayer player, CardList cards){
        super(player, cards);
        rankCount = new int[13];
        for (int i = 0; i < 5; i++) {
            rankCount[cards.getCard(i).getRank()]++;
        }
        for (int i = 0; i < 5; i++) {
            if (rankCount[cards.getCard(i).getRank()] == 4) {
                QuadRank = i;
                break;
            }
        }
    }


    @Override
    public boolean isValid(){
        if(this.size() != 5){
            return false;
        }
        for (int i = 0; i < 13; i++){
            if (rankCount[i] == 4){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String getType(){
        return "Quad";
    }

    @Override
    public Card getTopCard(){
        return new Card(3, QuadRank);
    }

    @Override
    public boolean beats(Hand hand){
        if (hand.size() != 5){
            return false;
        }
        if (!hand.getType().equals("StraightFlush")){
            return false;
        }
        BigTwoCard topCard = new BigTwoCard(this.getTopCard().getSuit(), this.getTopCard().getRank());
        return topCard.compareTo(hand.getTopCard()) > 0;
    }   
}
