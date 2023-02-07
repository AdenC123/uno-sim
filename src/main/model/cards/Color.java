package model.cards;

// The four Uno card colors, plus a placeholder for +4 and Wild cards
public enum Color {
    BLUE, RED, YELLOW, GREEN, WILD;

    // Effects: return the color formatted as a string
    public String toString() {
        switch (this) {
            case BLUE: return "Blue";
            case RED: return "Red";
            case YELLOW: return "Yellow";
            case GREEN: return "Green";
            case WILD: return "Wild";
        }
        return null;
    }
}
