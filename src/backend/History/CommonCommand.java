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

    public CommonCommand() {
        this.card = Logger.getCard();
        this.src = Logger.getSrc();
        this.dest = Logger.getDest();
    }

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

    public boolean exec() {
        if (card != null) {
            CardStack stack = ((CardStack)src).pop(card);
            if(((CardStack)dest).put(stack)) {
                return true;
            }
            ((CardStack)src).putForce(stack);
        } else {
            Card card = src.pop();
            if(dest.put(card)) {
                return true;
            }
            src.putForce(card);
        }
        return false;
    }

}
