package abstractFactories;

import backend.History.History;
import interfaces.Card;
import interfaces.CardDeck;
import interfaces.CardStack;

/**
 * Abstraktní třída pro generování backendu
 *
 * @author xbures29+xhalam14
 */
public abstract class AbstractFactorySolitaire {
    /**
     * Vytvoření karty
     * @param color barva karty
     * @param value hodnota karty
     * @return karta implementující interface Card
     */
    public abstract Card createCard(Card.Color color, int value);

    /**
     * Vytvoření Rozdávacího balíčku
     * @return Naplněný balíček karet implementující interface CardDeck
     */
    public abstract CardDeck createCardDeck();

    /**
     * Vytvoření cílového balíčku karet
     * @param color Barva karet v cílovém balíčku
     * @return Prázdný balíček implementující interface CardDeck
     */
    public abstract CardDeck createTargetPack(Card.Color color);

    /**
     * Vytvoření balíčku karet pro hrací sloupce
     * @return Prázdný balíček implementující interface CardStack
     */
    public abstract CardStack createWorkingPack();

    /**
     * Vytvoření balíčku karet pro zobrazování otočených karet z balíčku
     * @return Prázdný balíček implementující interface CardDeck
     */
    public abstract CardDeck createPreview();
}
