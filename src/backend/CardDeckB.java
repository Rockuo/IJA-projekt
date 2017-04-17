package backend;

import interfaces.Card;
import interfaces.CardDeck;

import java.util.Stack;

public class CardDeckB implements CardDeck {

    protected Stack<Card> stack;
    private int maxSize;
    private Card.Color color = null;

    public CardDeckB(){
        this.stack = new Stack<Card>();
        this.maxSize = 13;
    }

    public CardDeckB(int maxSize) {
        int byColor = maxSize / 4;
        this.maxSize = maxSize;
        this.stack = new Stack<Card>();
        for (Card.Color color : Card.Color.values()) {
            for (int i = 1; i <= byColor; i++) {
                stack.push(new CardB(color, i));
            }
        }
    }

    public CardDeckB(int maxSize, Card.Color color) {
        this.maxSize = maxSize;
        this.stack = new Stack<Card>();
        this.color = color;
    }

    @Override
    public Card get() {
        if (this.isEmpty()) {
            return null;
        }
        return this.stack.peek();
    }

    @Override
    public Card get(int index) {
        if (this.isEmpty() && (index) >= (this.stack.size())) {
            return null;
        }

        int iMax = this.stack.size() - index;

        Stack<Card> tempDeck = new Stack<Card>();
        for (int i = 0; i < iMax; i++) {
            tempDeck.push(this.stack.pop());
        }

        Card peekCard = tempDeck.peek();

        for (int i = 0; i < iMax; i++) {
            this.stack.push(tempDeck.pop());
        }

        return peekCard;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.size() == 0;
    }

    @Override
    public Card pop() {
        if (this.isEmpty()) {
            return null;
        }
        return this.stack.pop();
    }

    @Override
    public boolean put(Card card) {
        if (stack.size() >= this.maxSize) {
            return false;
        }
        if (this.color != null && (this.color != card.color() ||
                (this.stack.size() == 0 && card.value() != 1) ||
                (this.stack.size() != 0 && (this.stack.peek().value() + 1) != card.value())
        )) {
            return false;
        }

        this.stack.push(card);
        return true;
    }

    @Override
    public int size() {
        return this.stack.size();
    }
}
