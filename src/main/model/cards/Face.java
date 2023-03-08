package model.cards;

import exceptions.InvalidFaceException;

import java.util.HashMap;
import java.util.Map;

// All possible faces on an Uno card
public enum Face {
    PLUS2("+2"),
    PLUS4("+4"),
    WILD("Wild"),
    SKIP("Skip"),
    REVERSE("Reverse"),
    NUMBER("Number");

    private final String asString;
    private static final Map<String, Face> STRING_FACE_MAP = new HashMap<>();
    // setup STRING_FACE_MAP with all values
    static {
        for (Face face : Face.values()) {
            String s = face.asString.toLowerCase();
            STRING_FACE_MAP.put(s, face);
        }
    }

    Face(String asString) {
        this.asString = asString;
    }

    // Effects: return the face formatted as a string
    @Override
    public String toString() {
        return this.asString;
    }

    // Effects: produces the face from given string,
    //          throws InvalidFaceException if s is not a valid face
    public static Face fromString(String s) throws InvalidFaceException {
        s = s.toLowerCase();
        if (!STRING_FACE_MAP.containsKey(s)) {
            throw new InvalidFaceException();
        }
        return STRING_FACE_MAP.get(s);
    }
}
