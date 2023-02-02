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
    public boolean isValid(Card other) {
        return false; //stub
    }
}
