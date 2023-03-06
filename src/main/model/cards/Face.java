package model.cards;

import exceptions.InvalidFaceException;

import java.util.HashMap;
import java.util.Map;

// All possible faces on an Uno card
public enum Face {
    PLUS2, PLUS4, WILD, SKIP, REVERSE, NUMBER;

    // weird way to do this, but jacoco hates switch statements on enums
    private static final Map<Face, String> FACE_TO_STRING_MAP = new HashMap<Face, String>() {{
            put(PLUS2, "+2");
            put(PLUS4, "+4");
            put(WILD, "Wild");
            put(SKIP, "Skip");
            put(REVERSE, "Reverse");
            put(NUMBER, "Number");
        }};

    // Effects: return the face formatted as a string
    @Override
    public String toString() {
        return FACE_TO_STRING_MAP.get(this);
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
