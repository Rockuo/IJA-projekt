package backend.History;

import interfaces.Card;
import interfaces.CardDeck;
import interfaces.CardStack;
import interfaces.Command;

/**
 * Created by rockuo on 3.5.17.
 */
public class CommonCommand implements Command {
    private CardDeck src;
    private CardDeck dest;
    private Card card;

    public CommonCommand(CardDeck src, CardDeck dest, Card card) {
        this.card = card;
        this.src = src;
        this.dest = dest;
    }

    @Override
    public void undo() {
        if (card != null) {
            ((CardStack)src).putForce(((CardStack)dest).pop(card));
        } else {
            src.putForce(dest.pop());
        }
    }


}
