package model.cards;

import model.Player;

// A special card that has effects when played
public class PowerCard extends Card {
    public enum Power {
        PLUS2, PLUS4, WILD, SKIP, REVERSE
    }

    // Effects: constructs a power card with owner, color, and power
    public PowerCard(Player owner, Color color, Power power) {
        super(owner, color); //stub
    }

    @Override
    public boolean isValid(Card other) {
        return false;
    }
}
