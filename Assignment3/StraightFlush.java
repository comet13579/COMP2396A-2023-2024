public class StraightFlush extends Hand{
    private final Straight straight;
    private final Flush flush;
    
    public StraightFlush(CardGamePlayer player, CardList cards){
        super(player, cards);
        straight = new Straight(player, cards);
        flush = new Flush(player, cards);
    }

    @Override
    public boolean isValid(){
        return (straight.isValid() && flush.isValid());
    }

    @Override
    public String getType(){
        return "StraightFlush";
    }

    @Override
    public Card getTopCard(){
        return straight.getTopCard();
    }

    @Override
    public boolean beats(Hand hand){
        if (hand.size() != 5){
            return false;
        }
        if (!hand.getType().equals("StraightFlush")){
            return true;
        }
        BigTwoCard topCard = new BigTwoCard(this.getTopCard().getSuit(), this.getTopCard().getRank());
        return topCard.compareTo(hand.getTopCard()) > 0;
    }
}
