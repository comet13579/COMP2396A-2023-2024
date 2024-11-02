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
        if (cardRankAdj(this.getRank()) > cardRankAdj(card.getRank())){
            return 1;
        }
        else if (cardRankAdj(this.getRank()) < cardRankAdj(card.getRank())){
            return -1;
        }
        if (this.getSuit() > card.getSuit()){
            return 1;
        }
        else if (this.getSuit() < card.getSuit()){
            return -1;
        }
        return 0;
    }
}