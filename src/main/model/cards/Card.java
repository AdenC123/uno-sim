package model.cards;

import persistence.Writable;

// An Uno card with a color and a player who owns it
public interface Card extends Writable {
    Color getColor();

    void setColor(Color color);

    Face getFace();

    int getNumber();

    // Effects: returns true if this card can be played on the given card
    boolean canPlayOn(Card other);

    // Effects: returns the card in string format
    @Override
    String toString();
}
