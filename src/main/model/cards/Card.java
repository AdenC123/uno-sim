package model.cards;

import model.Player;

// An Uno card with a color and a player who owns it
public abstract class Card {
    private final Player owner;
    private Color color;

    // Effects: constructs a card with owner and color
    public Card(Player owner, Color color) {
        this.owner = owner;
        this.color = color;
    }

    public Color getColor() {
        return color; //stub
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Effects: returns the player who played this card or has it in their hand
    public Player getOwner() {
        return owner;
    }

    // Effects: returns true if this card can be played on the given card
    public abstract boolean canPlayOn(Card other);

    // Effects: returns the card in string format
    @Override
    public abstract String toString();
}
