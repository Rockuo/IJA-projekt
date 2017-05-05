package interfaces;

import java.io.Serializable;

/**
 * Příkaz uložitelný do historie
 *
 * @author xbures29+xhalam14
 */
public interface Command extends Serializable{
    /**
     * Vrať akci příkazu
     */
    void undo();

    /**
     * Provede příkaz
     * @return příkaz byl proveden bez chyb
     */
    boolean exec();
}
