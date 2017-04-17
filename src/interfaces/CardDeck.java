package interfaces;

public interface CardDeck {

    Card get();

    Card get(int index);

    boolean isEmpty();

    Card pop();

    boolean put(Card card);

    int size();
}
