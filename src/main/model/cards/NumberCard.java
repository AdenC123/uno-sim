package model.cards;

import model.Player;

// A normal card with a number face
public class NumberCard extends Card {

    // Requires: number is 0 to 9
    // Effects: constructs a NumberCard with owner, number, and color
    public NumberCard(Player owner, Color color, int number) {
        super(owner, color); //stub
    }

    @Override
    // Effects: returns true if color or number are the same as other
    public boolean isValid(Card other) {
        return false; //stub
    }

    @Override
    // Effects: returns the card as a readable string, in the format <Color> <Number>
    public String toString() {
        return null; //stub
    }
}
