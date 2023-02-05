package model;

import model.cards.Card;

import java.util.List;

// A player in the Uno game, with a hand of cards
public class Player {

    // Requires: id > 0
    // Effects: constructs a player with id and an empty hand
    public Player(int id) {
        //stub
    }

    public int getId() {
        return -1; //stub
    }

    // Requires: num >= 0
    // Modifies: this
    // Effects: adds num randomized cards to the player's hand
    public void drawCards(int num) {
        //stub
    }

    // Requires: 0 <= index < handSize()
    // Effects: returns the card in the player's hand at index
    public Card getCard(int index) {
        return null; //stub
    }

    // Modifies: this
    // Effects: adds the given card to the player's hand
    public void drawGivenCard(Card card) {
        //stub
    }

    // Requires: 0 <= index < handSize()
    // Modifies: this
    // Effects: removes the card at index from the player's hand and returns it
    public Card removeCard(int index) {
        return null; //stub
    }

    // Effects: return the size of the player's hand
    public int handSize() {
        return -1;
    }

    // Effects: returns the contents of the player's hand in the format index:card
    public String handToString() {
        return ""; //stub
    }
}
