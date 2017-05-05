package interfaces;

import java.io.Serializable;

/**
 * Inrerface pro implementaci balíčku karet
 *
 * @author xbures29+xhalam14
 */
public interface CardDeck extends Serializable {

    /**
     * Vrátí kartu, která byla na vrcholu zásobníku
     * @return karta z vrcholu balíku, pokud je prázdný, vrací null
     */
    Card get();

    /**
     * Vrátí kartu, která byla na uvedenom indexu zásobníku
     * @param index pořadí karty v balíku
     * @return karta z balíku
     */
    Card get(int index);

    /**
     * Testuje, jestli je balíček prázdný
     * @return true pokud je balíček prázdny, jinak false
     */
    boolean isEmpty();

    /**
     * Odebere kartu, která byla na vrcholu zásobníku
     * @return karta z vrcholu balíku, pokud je prázdný, vrací null
     */
    Card pop();

    /**
     * Vloží kartu na vrchol zásobníku
     * @param card karta, která se vkládá na balíček
     * @return true pokud se podařilo vložit kartu na balíček
     */
    boolean put(Card card);

    /**
     * Získání velikosti balíčku
     * @return počet karet v balíčku
     */
    int size();

    /**
     * Vloží kartu na vrchol zásobníku
     * @param card karta, která se vkládá na balíček
     */
    void putForce(Card card);

    /**
     * Získání barvy karty
     * @return barva karty
     */
    Card.Color getColor();
}
