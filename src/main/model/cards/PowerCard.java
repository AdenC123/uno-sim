package model.cards;

import model.Player;

// A special card that has effects when played
public class PowerCard extends Card {

    // Effects: constructs a power card with owner, color, and power
    public PowerCard(Player owner, Color color, Power power) {
        super(owner, color); //stub
    }

    @Override
    // Effects: returns true if color or power are same as other
    public boolean isValid(Card other) {
        return false;
    }

    @Override
    // Effects: return the card as a string, in the format <Color> <Power>
    //          or just <Power> for Wild and +4
    public String toString() {
        return null;
    }
}
