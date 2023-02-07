package model.cards;

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
    // Effects: returns Face.NUMBER;
    public Face getFace() {
        return Face.NUMBER;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    // Effects: returns true if this card can be played on the other card
    public boolean canPlayOn(Card other) {
        if (other.getFace() == Face.NUMBER) {
            // number card: need same color or same number
            return other.getNumber() == this.getNumber() || other.getColor() == this.getColor();
        } else {
            // power card: colors must be equal
            return other.getColor() == this.getColor();
        }
    }

    @Override
    // Effects: returns the card as a readable string, in the format <Color> <Number>
    public String toString() {
        return getColor().toString() + " " + number;
    }
}
