package model;

import model.cards.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// A player in the Uno game, with a hand of cards
public class Player implements Writable {
    private final int id;
    private final List<Card> hand;

    // TODO: deck stuff should probably be in Game or its own class
    private static final double POWER_CHANCE = 0.3;

    // Requires: id > 0
    // Effects: constructs a player with id and an empty hand
    public Player(int id) {
        this.id = id;
        this.hand = new ArrayList<>();
    }

    // Requires: id > 0
    // Effects: constructs a player with id and given hand of cards
    public Player(int id, List<Card> hand) {
        this.id = id;
        this.hand = hand;
    }

    public int getId() {
        return id;
    }

    // Requires: num >= 0
    // Modifies: this
    // Effects: adds num randomized cards to the player's hand
    public void drawCards(int num) {
        for (int i = 0; i < num; i++) {
            Card randomCard = makeRandomCard();
            drawGivenCard(randomCard);
        }
    }

    // Effects: returns a randomly generated card
    private Card makeRandomCard() {
        // random choice from the first 4 colors
        Color randomColor = Color.values()[(int)(Math.random() * 4)];
        if (Math.random() <= POWER_CHANCE) {
            return makeRandomPowerCard(randomColor);
        } else {
            return makeRandomNumberCard(randomColor);
        }
    }

    private Card makeRandomNumberCard(Color color) {
        // random number from 0 to 9
        int num = (int)(Math.random() * 10);
        return new NumberCard(color, num);
    }

    private Card makeRandomPowerCard(Color color) {
        // random choice from the 5 powers
        Face randomPower = Face.values()[(int)(Math.random() * 5)];
        if (randomPower == Face.PLUS4 || randomPower == Face.WILD) {
            return new PowerCard(Color.WILD, randomPower);
        } else {
            return new PowerCard(color, randomPower);
        }
    }


    // Requires: 0 <= index < handSize()
    // Effects: returns the card in the player's hand at index
    public Card getCard(int index) {
        return hand.get(index);
    }

    // Modifies: this
    // Effects: adds the given card to the player's hand
    public void drawGivenCard(Card card) {
        hand.add(card);
    }

    // Requires: 0 <= index < handSize()
    // Modifies: this
    // Effects: removes the card at index from the player's hand and returns it
    public Card removeCard(int index) {
        return hand.remove(index);
    }

    // Effects: return the size of the player's hand
    public int handSize() {
        return hand.size();
    }

    // Effects: returns the contents of the player's hand in the format index+1:card
    public String handToString() {
        StringBuilder rsf = new StringBuilder();
        for (int i = 0; i < handSize(); i++) {
            rsf.append(i + 1);
            rsf.append(":");
            rsf.append(getCard(i).toString());
            rsf.append(", ");
        }
        // remove the extra comma and space
        return rsf.substring(0, rsf.length() - 2);
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", getId());
        jsonObject.put("hand", handToJson());
        return jsonObject;
    }

    // Effects: returns player's hand as a JSON array of card objects
    private JSONArray handToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Card card : hand) {
            jsonArray.put(card.toJson());
        }
        return jsonArray;
    }
}
