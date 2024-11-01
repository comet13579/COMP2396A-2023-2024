public class StraightFlush extends Hand{
    private Straight straight;
    private Flush flush;
    
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
    public  String getType(){
        return "StraightFlush";
    }

    @Override
    public Card getTopCard(){
        return straight.getTopCard();
    }
}
