package backend.History;

import interfaces.Card;
import interfaces.CardDeck;
import interfaces.CardStack;
import interfaces.Command;

/**
 * Created by rockuo on 3.5.17.
 */
public class PrevToPackCommand implements Command {
    private CardDeck prev;
    private CardDeck pack;

    public PrevToPackCommand(CardDeck prev, CardDeck pack) {
        this.prev = prev;
        this.pack = pack;
    }

    @Override
    public void undo() {

    }


}
