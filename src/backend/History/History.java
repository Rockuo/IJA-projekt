package backend.History;

import interfaces.Command;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Historie
 *
 * @author xbures29+xhalam14
 */
public class History implements Serializable{
    private ArrayList<Command> history;

    public History() {
        this.history = new ArrayList<>();
    }

    /**
     * Přidat příkaz Do historie
     * @param command příkaz
     */
    public void add(Command command) {
        history.add(command);
        if(history.size() > 5) {
            history.remove(0);
        }
    }

    /**
     * Zpět o jeden krok
     */
    public void undo() {
        if(this.history.size()>0) (this.history.remove(this.history.size()-1)).undo();
    }
}
