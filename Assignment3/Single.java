public class Single extends Hand{
    public Single(CardGamePlayer player, CardList cards){
        super(player, cards);
    }

    @Override
    public boolean isValid(){
        return this.size() == 1;
    }

    @Override
    public String getType(){
        return "Single";
    }
}
