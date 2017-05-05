package interfaces;

/**
 * Inrerface pro implementaci balíčku karet pro hrací sloupce
 *
 * @author xbures29+xhalam14
 */
public interface CardStack extends CardDeck {

    /**
     * Odebere ze zásobníku všechny karty, od zadané karty po vrchol zásobníku
     * @param card karta, od které se mají odebrat karty
     * @return balíčrk přesouvaných karet, pokud karta není na zásobníku, vrací null
     */
    CardStack pop(Card card);

    /**
     * Vloží karty na vrchol zásobníku ve stejném pořadí, v jakém byly před tím odebrány
     * @param stack balíček přesouvaných karet
     * @return true pokud se podařilo vložit karty
     */
    boolean put(CardStack stack);

    /**
     * Vloží karty na vrchol zásobníku ve stejném pořadí, v jakém byly před tím odebrány
     * @param stack balíček přesouvaných karet
     */
    void putForce(CardStack stack);
}
