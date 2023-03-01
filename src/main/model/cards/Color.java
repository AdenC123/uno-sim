package model.cards;

import exceptions.InvalidColorException;

// The four Uno card colors, plus a placeholder for +4 and Wild cards
public enum Color {
    BLUE, RED, YELLOW, GREEN, WILD;

    // Effects: return the color formatted as a string
    @Override
    public String toString() {
        String result = null;
        switch (this) {
            case BLUE:
                result = "Blue";
                break;
            case RED:
                result = "Red";
                break;
            case YELLOW:
                result =  "Yellow";
                break;
            case GREEN:
                result = "Green";
                break;
            case WILD:
                result = "Wild";
                break;
        }
        return result;
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
