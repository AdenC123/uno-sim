package model.cards;

import model.Player;

// A special card that has effects when played
public class PowerCard implements Card {

    // Effects: constructs a power card with owner, color, and power
    public PowerCard(Color color, Face power) {
        //stub
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color color) {

    }

    @Override
    public Face getFace() {
        return null;
    }

    @Override
    // Effects: returns -1 (power cards don't have numbers) TODO: is this the right way to do it?
    public int getNumber() {
        return -1;
    }

    @Override
    // Effects: returns true if color or power are same as other TODO update
    public boolean canPlayOn(Card other) {
        return false;
    }

    @Override
    // Effects: return the card as a string, in the format <Color> <Power>
    //          or just <Power> for Wild and +4
    public String toString() {
        return null;
    }
}
