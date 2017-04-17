package factories;


import abstractFactories.AbstractFactorySolitaire;
import backend.CardB;
import backend.CardDeckB;
import backend.CardStackB;
import interfaces.CardDeck;
import interfaces.CardStack;

public class FactoryKlondike extends AbstractFactorySolitaire {

    @Override
    public interfaces.Card createCard(interfaces.Card.Color color, int value) {
        return (value >= 1 && value <= 13) ? new CardB(color, value) : null;
    }

    @Override
    public CardDeck createCardDeck() {
        return new CardDeckB(52);
    }

    @Override
    public CardDeck createTargetPack(interfaces.Card.Color color) {
        return new CardDeckB(13,color);
    }

    @Override
    public CardStack createWorkingPack() {
        return new CardStackB();
    }

    @Override
    public CardDeck createPreview(){
        return new CardDeckB();
    }
}
