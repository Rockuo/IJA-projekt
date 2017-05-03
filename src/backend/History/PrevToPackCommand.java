package backend.History;

import interfaces.CardDeck;
import interfaces.Command;

/**
 * Created by rockuo on 3.5.17..nvg
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
        while (!this.pack.isEmpty()) {
            this.prev.put(this.pack.pop());
        }
    }
}