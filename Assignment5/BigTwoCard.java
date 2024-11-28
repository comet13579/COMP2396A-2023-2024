/**
 * The BigTwoCard class is a subclass of the Card class and is used to model a card used in a
 * Big Two card game. It should override the compareTo() method it inherits from the Card
 * class to reflect the ordering of cards used in a Big Two card game. Below is a detailed
 * description for the BigTwoCard class.
 * 
 * @author Sonny Wong
 */
public class BigTwoCard extends Card { 
    /**
     * BigTwoCard(int suit, int rank) – a constructor for building a card with the specified
     * suit and rank. suit is an integer between 0 and 3, and rank is an integer between 0 and 12.
     * @param suit the suit of the card, 0 = Diamond, 1 = Club, 2 = Heart, 3 = Spade
     * @param rank the rank of the card, 0 = 'A', 1 = '2', 2 = '3', ..., 8 = '9', 9 = '0', 10 = 'J', 11 ='Q', 12 = 'K'
     */ 
    public BigTwoCard(int  suit, int rank) {
        super(suit, rank);
    }

    private int cardRankAdj(int rank){
        if (rank < 2){
            return rank + 13;
        }
        return rank;
    }

    /**
     * a method for comparing the order of this card with the
     * specified card. Returns a negative integer, zero, or a positive integer when this card is
     * less than, equal to, or greater than the specified card.
     * @param card the card to be compared
     */
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