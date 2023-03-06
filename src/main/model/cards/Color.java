package model.cards;

import exceptions.InvalidColorException;

import java.util.HashMap;
import java.util.Map;

// The four Uno card colors, plus a placeholder for +4 and Wild cards
public enum Color {
    BLUE, RED, YELLOW, GREEN, WILD;

    // weird way to do this, but jacoco hates switch statements on enums
    private static final Map<Color, String> COLOR_TO_STRING_MAP = new HashMap<Color, String>() {{
            put(BLUE, "Blue");
            put(RED, "Red");
            put(YELLOW, "Yellow");
            put(GREEN, "Green");
            put(WILD, "Wild");
        }};

    // Effects: return the color formatted as a string
    @Override
    public String toString() {
        return COLOR_TO_STRING_MAP.get(this);
    }

    // Effects: produces the color from given string,
    //          throws InvalidColorException if s is not a valid color
    public static Color fromString(String s) throws InvalidColorException {
        s = s.toLowerCase();
        switch (s) {
            case "blue": return BLUE;
            case "red": return RED;
            case "yellow": return YELLOW;
            case "green": return GREEN;
            case "wild": return WILD;
        }
        throw new InvalidColorException();
    }

}
