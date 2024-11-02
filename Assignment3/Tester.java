public class Tester {
    public static void main(String[] args) {
        testSingle();
        testPair();
        testTriple();
        testStraight();
        testFlush();
        testFullHouse();
        testQuads();
        testStraightFlush();
    }

    private static void testSingle() {
        System.out.println("Testing Single:");
        CardList cards = new CardList();
        cards.addCard(new Card(0, 0)); // Diamond A
        testHand(cards);
    }

    private static void testPair() {
        System.out.println("\nTesting Pair:");
        CardList cards = new CardList();
        cards.addCard(new Card(0, 0)); // Diamond A
        cards.addCard(new Card(1, 0)); // Club A
        testHand(cards);

    }

    private static void testTriple() {
        System.out.println("\nTesting Triple:");
        CardList cards = new CardList();
        cards.addCard(new Card(0, 0)); // Diamond A
        cards.addCard(new Card(1, 0)); // Club A
        cards.addCard(new Card(2, 0)); // Heart A
        testHand(cards);
    }

    private static void testStraight() {
        System.out.println("\nTesting Straight:");
        CardList cards = new CardList();
        cards.addCard(new Card(0, 3)); // A
        cards.addCard(new Card(1, 4)); // 2
        cards.addCard(new Card(2, 5)); // 3
        cards.addCard(new Card(3, 6)); // 4
        cards.addCard(new Card(0, 7)); // 5
        testHand(cards);
    }

    private static void testFlush() {
        System.out.println("\nTesting Flush:");
        CardList cards = new CardList();
        cards.addCard(new Card(0, 0)); // Diamond A
        cards.addCard(new Card(0, 3)); // Diamond 4
        cards.addCard(new Card(0, 5)); // Diamond 6
        cards.addCard(new Card(0, 7)); // Diamond 8
        cards.addCard(new Card(0, 9)); // Diamond 10
        testHand(cards);
    }

    private static void testFullHouse() {
        System.out.println("\nTesting Full House:");
        CardList cards = new CardList();
        cards.addCard(new Card(0, 0)); // Diamond A
        cards.addCard(new Card(1, 0)); // Club A
        cards.addCard(new Card(2, 0)); // Heart A
        cards.addCard(new Card(0, 1)); // Diamond 2
        cards.addCard(new Card(1, 1)); // Club 2
        testHand(cards);
    }

    private static void testQuads() {
        System.out.println("\nTesting Four of a Kind:");
        CardList cards = new CardList();
        cards.addCard(new Card(0, 0)); // Diamond A
        cards.addCard(new Card(1, 0)); // Club A
        cards.addCard(new Card(2, 0)); // Heart A
        cards.addCard(new Card(3, 0)); // Spade A
        cards.addCard(new Card(0, 1)); // Diamond 2
        testHand(cards);
    }

    private static void testStraightFlush() {
        System.out.println("\nTesting Straight Flush:");
        CardList cards = new CardList();
        cards.addCard(new Card(0, 5)); // Diamond A
        cards.addCard(new Card(0, 6)); // Diamond 2
        cards.addCard(new Card(0, 2)); // Diamond 3
        cards.addCard(new Card(0, 3)); // Diamond 4
        cards.addCard(new Card(0, 4)); // Diamond 5
        testHand(cards);
    }

    private static void testHand(CardList cards) {
        Hand hand = BigTwo.composeHand(null, cards);
        if (hand != null) {
            System.out.println("Hand type: " + hand.getType());
            System.out.println("Cards: " + cards.toString());
            System.out.println("Top card: " + hand.getTopCard().toString());
        } else {
            System.out.println("Invalid hand combination");
        }
    }
    
}

