package model.cards;

import exceptions.InvalidColorException;

import java.util.HashMap;
import java.util.Map;

// The four Uno card colors, plus a placeholder for +4 and Wild cards
public enum Color {
    BLUE, RED, YELLOW, GREEN, WILD;

    // Effects: return the color formatted as a string
    @Override
    public String toString() {
        // weird way to do this, but jacoco hates switch statements on enums
        Map<Color, String> map = new HashMap<>();
        map.put(BLUE, "Blue");
        map.put(RED, "Red");
        map.put(YELLOW, "Yellow");
        map.put(GREEN, "Green");
        map.put(WILD, "Wild");
        return map.get(this);
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
