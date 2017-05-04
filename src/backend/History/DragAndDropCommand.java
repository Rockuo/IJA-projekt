package backend.History;

import interfaces.Card;
import interfaces.CardDeck;
import interfaces.CardStack;
import interfaces.Command;

/**
 * Created by rockuo on 3.5.17.
 */
public class DragAndDropCommand implements Command {
    private CardDeck src;
    private CardDeck dest;
    private Card card;
    private boolean revertTurn = false;

    public DragAndDropCommand() {
        this.card = Logger.getCard();
        this.src = Logger.getSrc();
        this.dest = Logger.getDest();
    }

    public DragAndDropCommand(CardDeck src, CardDeck dest, Card card) {
        this.card = card;
        this.src = src;
        this.dest = dest;
    }

    @Override
    public void undo() {
        if (this.revertTurn) {
            Card card = src.pop();
            card.revertTurn();
            src.putForce(card);
        }
        if (src instanceof CardStack && dest instanceof CardStack) {
            ((CardStack)src).putForce(((CardStack)dest).pop(card));
        } else {
            src.putForce(dest.pop());
        }
    }

    public boolean exec() {
        boolean succes = false;
        if (src instanceof CardStack && dest instanceof CardStack) {
            CardStack stack = ((CardStack)src).pop(card);
            if(((CardStack)dest).put(stack)) {
                succes = true;
            } else {
                ((CardStack) src).putForce(stack);
            }
        } else {
            Card card = src.pop();
            if(dest.put(card)) {
                succes = true;
            } else {
                src.putForce(card);
            }
        }
        if(succes) {
            Card card = src.pop();
            this.revertTurn = !card.isTurnedFaceUp();
            src.putForce(card);
        }
        return succes;
    }

    public boolean executable() {
        if(this.exec()) {
            this.undo();
            return true;
        }
        return false;
    }
}
