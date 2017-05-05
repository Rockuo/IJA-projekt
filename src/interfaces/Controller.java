package interfaces;

/**
 * Interface pro každý kontroller
 *
 * @author xbures29+xhalam14
 */
public interface Controller {
    /**
     * Aktualizuje vzhled elementu
     */
    void updateView();

    /**
     * Získá data potřebná pro uložení.
     * @return Data
     */
    Object save();
}
