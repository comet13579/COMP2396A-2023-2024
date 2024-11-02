public class BigTwoCard extends Card {  
    public BigTwoCard(int  suit, int rank) {
        super(suit, rank);
    }

    private int cardRankAdj(int rank){
        if (rank < 2){
            return rank + 13;
        }
        return rank;
    }

    @Override
    public int compareTo(Card card){
        if (this.getRank() > card.getRank()){
            return 1;
        }
        else if (this.getRank() < card.getRank()){
            return -1;
        }
        
        if (cardRankAdj(this.getSuit()) > cardRankAdj(card.getSuit())){
            return 1;
        }
        else if (cardRankAdj(this.getSuit()) < cardRankAdj(card.getSuit())){
            return -1;
        }
        return 0;
    }
}