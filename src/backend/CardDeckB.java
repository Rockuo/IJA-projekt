package backend;

import interfaces.Card;
import interfaces.CardDeck;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CardDeckB implements CardDeck {

    protected Stack<Card> stack;
    private int maxSize;
    private Card.Color color = null;
    protected ArrayList<Map<String,Object>> memory;
    protected static final int  memoryMax = 1;

    public CardDeckB(){
        this.stack = new Stack<Card>();
        this.maxSize = 13;
        this.memory = new ArrayList<>(CardDeckB.memoryMax);
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
        this.memory = new ArrayList<>(CardDeckB.memoryMax);
    }

    public CardDeckB(int maxSize, Card.Color color) {
        this.maxSize = maxSize;
        this.stack = new Stack<Card>();
        this.color = color;
        this.memory = new ArrayList<>(CardDeckB.memoryMax);
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
        this.remember();
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
        this.remember();
        this.stack.push(card);
        return true;
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    @Override
    public boolean undo() {
        if(this.memory.isEmpty()){
            return false;
        }
        Map<String, Object> hashMap = memory.get(this.memory.size()-1);
        this.stack = (Stack<Card>) hashMap.get("stack");
        this.maxSize = (int)hashMap.get("maxSize");
        this.color = (Card.Color) hashMap.get("color");
        return true;
    }

    private void remember() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("stack",this.stack);
        hashMap.put("maxSize",this.maxSize);
        hashMap.put("color",this.color);
        if (this.memory.size() == CardDeckB.memoryMax) {
            this.memory.remove(0);
        }
        this.memory.add(hashMap);
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("stack",this.stack);
        hashMap.put("maxSize",this.maxSize);
        hashMap.put("color",this.color);
        jsonObject.putAll(hashMap);
        return jsonObject.toJSONString();
    }

}
