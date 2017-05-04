package interfaces;

import java.io.Serializable;

/**
 * Created by rockuo on 3.5.17.
 */
public interface Command extends Serializable{
    void undo();

    boolean exec();
}
