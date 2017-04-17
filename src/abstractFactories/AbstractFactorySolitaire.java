package abstractFactories;

import interfaces.Card;
import interfaces.CardDeck;
import interfaces.CardStack;

public abstract class AbstractFactorySolitaire {
    public abstract Card createCard(Card.Color color, int value);

    public abstract CardDeck createCardDeck();

    public abstract CardDeck createTargetPack(Card.Color color);

    public abstract CardStack createWorkingPack();
}
