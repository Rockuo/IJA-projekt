package interfaces;

import java.io.Serializable;

public interface CardDeck extends Serializable {

    Card get();

    Card get(int index);

    boolean isEmpty();

    Card pop();

    boolean put(Card card);

    int size();

    void putForce(Card card);

    Card.Color getColor();
}
