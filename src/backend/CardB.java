package backend;

import interfaces.Card;
import org.json.simple.JSONObject;

import java.util.*;

public class CardB implements Card {

    private int value;
    private Color color;
    private boolean faceUp = false;
    private ArrayList<Map<String,Object>> memory;
    private static final int  memoryMax = 1;

    public CardB(Color color, int value) {
        this.color = color;
        this.value = value;
        this.memory = new ArrayList<>(CardB.memoryMax);
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
        remember();
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
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("value", this.value);
        hashMap.put("color", this.color);
        hashMap.put("faceUp",this.faceUp);
        jsonObject.putAll(hashMap);
        return jsonObject.toJSONString();
    }

    private void remember(){
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("faceUp",this.faceUp);
        hashMap.get("");
        if (this.memory.size() == CardB.memoryMax) {
           this.memory.remove(0);
        }
        this.memory.add(hashMap);
    }

    public boolean revert() {
        if(this.memory.isEmpty()) {
            return false;
        }
        if ((boolean)this.memory.get(0).get("faceUp")){
           this.faceUp = false;
        }
        return true;
    }
}
