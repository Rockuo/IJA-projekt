package interfaces;

public interface CardStack extends CardDeck {

    CardStack pop(Card card);

    boolean put(CardStack stack);
}
