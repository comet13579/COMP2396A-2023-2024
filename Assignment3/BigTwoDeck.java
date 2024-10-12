public class BigTwoDeck extends Deck{
    @Override
    public void initialize() {
        removeAllCards();
        BigTwoCard card;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                card = new BigTwoCard(i, j);
                addCard(card);
            }
        }
    }
}
