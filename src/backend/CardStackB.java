package backend;


import interfaces.Card;
import interfaces.CardStack;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CardStackB extends CardDeckB implements CardStack {

    private boolean ignoreFirst = false;

    public CardStackB(boolean ignoreFirst){
        this.stack = new Stack<Card>();
        this.ignoreFirst = ignoreFirst;
    }

    public CardStackB(){
        this.stack = new Stack<Card>();
    }

    @Override
    public CardStack pop(Card card) {
        int place = this.stack.search(card);
        if (place <= 0) {
            return null;
        }
        CardStack cd = new CardStackB(true);
        Stack<Card> tempStack = new Stack<Card>();
        for (int i = 0; i < place; i++) {
            tempStack.push(this.stack.pop());
        }
        while (!tempStack.isEmpty()) {
            cd.put(tempStack.pop());
        }
        return cd;
    }

    @Override
    public boolean put(Card card) {
        if (this.stack.size() != 0 && this.stack.peek().similarColorTo(card)) {
            return false;
        }


        if(!this.ignoreFirst && this.stack.size() == 0 && card.value() != 13){
            return false;
        }

        if(this.stack.size() != 0 && (card.value()+1) != this.stack.peek().value()) {
            return false;
        }

        this.stack.push(card);
        return true;
    }

    @Override
    public boolean put(CardStack stack) {
        if(stack.size() == 0){
            return false;
        }
        Stack<Card> fakeStack = ((CardStackB)stack).getClonedStack();
        Stack<Card> tmpStack = new Stack<Card>();
        while (!fakeStack.isEmpty()) {
            tmpStack.push(fakeStack.pop());
        }

        if(!this.put(tmpStack.pop())){
            return false;
        }

        while (!tmpStack.empty()) {
            this.stack.push(tmpStack.pop());
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private Stack<Card> getClonedStack(){
        Object clone = this.stack.clone();
        if (clone instanceof Stack) {
            return (Stack<Card>)clone;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("stack",this.stack);
        hashMap.put("ignoreFirst",this.ignoreFirst);
        jsonObject.putAll(hashMap);
        return jsonObject.toJSONString();
    }

    @Override
    public boolean undo() {
        if(this.memory.isEmpty()){
            return false;
        }
        Map<String, Object> hashMap = memory.get(this.memory.size()-1);
        this.stack = (Stack<Card>) hashMap.get("stack");
        this.ignoreFirst = (boolean)hashMap.get("ignoreFirst");
        return true;
    }

    private void remember() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("stack",this.stack);
        hashMap.put("ignoreFirst",this.ignoreFirst);
        if (this.memory.size() == CardDeckB.memoryMax) {
            this.memory.remove(0);
        }
        this.memory.add(hashMap);
    }
}
