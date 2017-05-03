package backend.History;

import interfaces.Command;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rockuo on 3.5.17.
 */
public class History implements Serializable{
    private ArrayList<Command> history;

    public History() {
        this.history = new ArrayList<>();
    }

    public void add(Command command) {
        history.add(command);
        if(history.size() > 5) {
            history.remove(0);
        }
    }

    public void undo() {
        if(this.history.size()>0) (this.history.remove(this.history.size()-1)).undo();
    }
}
