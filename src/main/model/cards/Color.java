package model.cards;

import exceptions.InvalidColorException;

import java.util.HashMap;
import java.util.Map;

// The four Uno card colors, plus a placeholder for +4 and Wild cards
public enum Color {
    BLUE("Blue"),
    RED("Red"),
    YELLOW("Yellow"),
    GREEN("Green"),
    WILD("Wild");

    private final String asString;
    private static final Map<String, Color> STRING_COLOR_MAP = new HashMap<>();
    // setup STRING_COLOR_MAP with all values
    static {
        for (Color color : Color.values()) {
            String s = color.asString.toLowerCase();
            STRING_COLOR_MAP.put(s, color);
        }
    }

    Color(String asString) {
        this.asString = asString;
    }

    // Effects: return the color formatted as a string
    @Override
    public String toString() {
        return this.asString;
    }

    // Effects: produces the color from given string,
    //          throws InvalidColorException if s is not a valid color
    public static Color fromString(String s) throws InvalidColorException {
        s = s.toLowerCase();
        if (!STRING_COLOR_MAP.containsKey(s)) {
            throw new InvalidColorException();
        }
        return STRING_COLOR_MAP.get(s);
    }
}
