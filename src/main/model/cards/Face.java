package model.cards;

// All possible faces on an Uno card
public enum Face {
    PLUS2, PLUS4, WILD, SKIP, REVERSE, NUMBER;

    // Requires: this is not NUMBER
    // Effects: return the face formatted as a string
    public String toString() {
        switch (this) {
            case PLUS2: return "+2";
            case PLUS4: return "+4";
            case WILD: return "Wild";
            case SKIP: return "Skip";
            case REVERSE: return "Reverse";
        }
        return null;
    }
}
