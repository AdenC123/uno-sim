package model.cards;

import exceptions.InvalidFaceException;

// All possible faces on an Uno card
public enum Face {
    PLUS2, PLUS4, WILD, SKIP, REVERSE, NUMBER;

    // Effects: return the face formatted as a string
    @Override
    public String toString() {
        String result = null;
        switch (this) {
            case PLUS2:
                result = "+2";
                break;
            case PLUS4:
                result = "+4";
                break;
            case WILD:
                result = "Wild";
                break;
            case SKIP:
                result = "Skip";
                break;
            case REVERSE:
                result = "Reverse";
                break;
            case NUMBER:
                result = "Number";
                break;
        }
        return result;
    }

    // Effects: produces the face from given string,
    //          throws InvalidFaceException if s is not a valid face
    public static Face fromString(String s) throws InvalidFaceException {
        s = s.toLowerCase();
        switch (s) {
            case "+2": return PLUS2;
            case "+4": return PLUS4;
            case "wild": return WILD;
            case "skip": return SKIP;
            case "reverse": return REVERSE;
            case "number": return NUMBER;
        }
        throw new InvalidFaceException();
    }
}
