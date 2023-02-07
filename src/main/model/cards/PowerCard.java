package model.cards;

// A special card that has effects when played
public class PowerCard implements Card {
    private Color color;
    private final Face power;

    // Requires: Face is not NUMBER
    // Effects: constructs a power card with color and power
    public PowerCard(Color color, Face power) {
        this.color = color;
        this.power = power;
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
        return this.power;
    }

    @Override
    // Effects: returns -1 (power cards don't have numbers)
    public int getNumber() {
        return -1;
    }

    @Override
    // Effects: returns true if this card can be played on the other card
    public boolean canPlayOn(Card other) {
        if (this.color == Color.WILD) {
            // wilds can be played on any card
            return true;
        } else if (other.getFace() == Face.NUMBER) {
            // other is a number card, colors must be the same
            return other.getColor() == this.getColor();
        } else {
            // non-wild power card, faces or colors must be equal
            return other.getFace() == this.getFace() || other.getColor() == this.getColor();
        }
    }

    @Override
    // Effects: return the card as a string, in the format <Color> <Power>
    //          or just <Power> for Wild and +4
    public String toString() {
        if (this.getColor() == Color.WILD) {
            return this.getFace().toString();
        } else {
            return this.getColor().toString() + " " + this.getFace().toString();
        }
    }
}
