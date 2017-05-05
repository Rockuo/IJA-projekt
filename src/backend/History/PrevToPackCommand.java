package backend.History;

import interfaces.CardDeck;
import interfaces.Command;

/**
 * Příkaz pro přenos celého balíčku z Preview na Pack
 *
 * @author xbures29+xhalam14
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

    @Override
    public boolean exec() {
        return false;
    }
}
