package backend;

import interfaces.Card;

import java.util.*;

public class CardB implements Card {

    private int value;
    private Color color;
    private boolean faceUp = false;

    public CardB(Color color, int value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public int value() {
        return this.value;
    }

    @Override
    public boolean isTurnedFaceUp() {
        return this.faceUp;
    }

    @Override
    public boolean turnFaceUp() {
        if (this.faceUp) {
            return false;
        }
        this.faceUp = true;
        return true;
    }


    @Override
    public Color color() {
        return this.color;
    }

    @Override
    public boolean similarColorTo(Card c) {
        return (
                (this.color == Color.HEARTS || this.color == Color.DIAMONDS) &&
                        (c.color() == Color.HEARTS || c.color() == Color.DIAMONDS)
        ) || (
                (this.color == Color.CLUBS || this.color == Color.SPADES) &&
                        (c.color() == Color.CLUBS || c.color() == Color.SPADES)
        );
    }

    @Override
    public int compareValue(Card c) {
        return this.value - c.value();
    }

    @Override
    public void revertTurn() {
        this.faceUp = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardB cardB = (CardB) o;

        return this.color == cardB.color && this.faceUp == cardB.faceUp && this.value == cardB.value;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (faceUp ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.color.toString() + this.value;
    }
}
