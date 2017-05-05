package interfaces;

import backend.History.History;

import java.io.Serializable;

/**
 * Inrerface pro implementaci karty
 *
 * @author xbures29+xhalam14
 */
public interface Card extends Serializable{

    enum Color {
        DIAMONDS('D'),
        HEARTS('H'),
        CLUBS('C'),
        SPADES('S');
        private final char c;

        Color(char c) {
            this.c = c;
        }

        @Override
        public String toString() {
            switch (this) {
                case CLUBS:
                    return "C";
                case DIAMONDS:
                    return "D";
                case HEARTS:
                    return "H";
                case SPADES:
                    return "S";
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Získání hodnoty karty.
     * @return hodnota karty
     */
    int value();

    /**
     * Testuje, zda je karta otočená lícem nahoru.
     * @return true ak je karta otočená lícem nahoru, jinak false
     */
    boolean isTurnedFaceUp();

    /**
     * Otočí kartu lícem nahoru. Ak karta už je lícem nahoru, nedelá nic.
     * @return true ak sa kartu podařilo otočit
     */
    boolean turnFaceUp();

    /**
     * Získáni barvy karty.
     * @return barva karty
     */
    Color color();

    /**
     * Testuje, zda barva karty je podobná s barvou zadané karty. Podobná barva pro piky jsou kříže (černá) a pro káry jsou srdce (červená).
     * @param c karta, s kterou se porovnává
     * @return true pokud jsou barvy karet podobné
     */
    boolean similarColorTo(Card c);

    /**
     * Porovná hodnotu karty s hodnotou zadané karty a vrací kladný rozdíl hodnot.
     * @param c karta s kterou se porovnává
     * @return rozdíl hodnot karet
     */
    int compareValue(Card c);

    /**
     * Pokud bylo použito Undo pro kartu, při které po posunu ze sloupce otočila kartu, vrátí se otočení zpět.
     */
    void revertTurn();
}
