package model.cards;

import exceptions.InvalidFaceException;

import java.util.HashMap;
import java.util.Map;

// All possible faces on an Uno card
public enum Face {
    PLUS2, PLUS4, WILD, SKIP, REVERSE, NUMBER;

    // Effects: return the face formatted as a string
    @Override
    public String toString() {
        // weird way to do this, but jacoco hates switch statements on enums
        Map<Face, String> map = new HashMap<>();
        map.put(PLUS2, "+2");
        map.put(PLUS4, "+4");
        map.put(WILD, "Wild");
        map.put(SKIP, "Skip");
        map.put(REVERSE, "Reverse");
        map.put(NUMBER, "Number");
        return map.get(this);
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
