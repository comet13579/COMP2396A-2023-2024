public class BigTwoDeck extends Deck{
    @Override
    public void initialize() {
        removeAllCards();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card card = new Card(i, j);
                addCard(card);
            }
        }
    }
}
