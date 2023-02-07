package model.cards;

import model.Player;

// A normal card with a number face
public class NumberCard implements Card {
    private Color color;
    private final int number;

    // Requires: number is 0 to 9
    // Effects: constructs a NumberCard with number and color
    public NumberCard(Color color, int number) {
        this.color = color;
        this.number = number;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Face getFace() {
        return Face.NUMBER;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    // Effects: returns true if color or number are the same as other
    public boolean canPlayOn(Card other) {
        return false; //stub
    }

    @Override
    // Effects: returns the card as a readable string, in the format <Color> <Number>
    public String toString() {
        return getColor().toString() + " " + number;
    }
}
