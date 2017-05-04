package backend.History;

import interfaces.Card;
import interfaces.CardDeck;

/**
 * Created by rockuo on 3.5.17.
 */
public class Logger {
    private static CardDeck src;
    private static CardDeck dest;
    private static Card card;
    private static boolean done;

    public static void clean(){
        src = null;
        dest = null;
        card = null;
        done = false;
    }
    public static boolean isDone() {
        return done;
    }

    public static void setDone(boolean done) {
        Logger.done = done;
    }

    public static CardDeck getSrc() {
        return src;
    }

    public static void setSrc(CardDeck src) {
        Logger.src = src;
    }

    public static CardDeck getDest() {
        return dest;
    }

    public static void setDest(CardDeck dest) {
        Logger.dest = dest;
    }

    public static Card getCard() {
        return card;
    }

    public static void setCard(Card card) {
        Logger.card = card;
    }
}
