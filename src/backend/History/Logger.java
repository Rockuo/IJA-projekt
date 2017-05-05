package backend.History;

import interfaces.Card;
import interfaces.CardDeck;

/**
 * "Statická" třída pro předávání akcí
 *
 * @author xbures29+xhalam14
 */
public class Logger {
    private static CardDeck src;
    private static CardDeck dest;
    private static Card card;
    private static int gameId;

    /**
     * Vyprázdnit Logger
     */
    public static void clean(){
        src = null;
        dest = null;
        card = null;
        gameId = -1;
    }


    /**
     * Získat Zdroj
     * @return Zdroj
     */
    public static CardDeck getSrc() {
        return src;
    }

    /**
     * Nastavit Zdroj
     * @param src Zdroj
     */
    public static void setSrc(CardDeck src) {
        Logger.src = src;
    }

    /**
     * Získat Cíl
     * @return Cíl
     */
    public static CardDeck getDest() {
        return dest;
    }

    /**
     *  Nastavit Cíl
     *  @param dest Cíl
     */
    public static void setDest(CardDeck dest) {
        Logger.dest = dest;
    }

    /**
     *  Získat Kartu
     *  @return Karta
     */
    public static Card getCard() {
        return card;
    }

    /**
     *  Nastavit Kartu
     *  @param card Karta
     */
    public static void setCard(Card card) {
        Logger.card = card;
    }

    /**
     * Získat Id invokující hry
     * @return Id hry
     */
    public static int getGameId() {
        return gameId;
    }

    /**
     * Nastavit Id invokující hry
     * @param gameId Id hry
     */
    public static void setGameId(int gameId) {
        Logger.gameId = gameId;
    }
}
