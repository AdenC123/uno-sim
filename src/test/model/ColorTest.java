package model;

import com.sun.jdi.IntegerValue;
import exceptions.InvalidColorException;
import model.cards.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {

    @Test
    public void testColorToString() {
        assertEquals("Blue", Color.BLUE.toString());
        assertEquals("Red", Color.RED.toString());
        assertEquals("Yellow", Color.YELLOW.toString());
        assertEquals("Green", Color.GREEN.toString());
        assertEquals("Wild", Color.WILD.toString());
    }

    @Test
    public void testValidColorFromString() {
        try {
            assertEquals(Color.BLUE, Color.fromString("blue"));
            assertEquals(Color.RED, Color.fromString("Red"));
            assertEquals(Color.YELLOW, Color.fromString("YELLOW"));
            assertEquals(Color.GREEN, Color.fromString("greEn"));
            assertEquals(Color.WILD, Color.fromString("wild"));
        } catch (InvalidColorException e) {
            fail("Unexpected InvalidColorException");
        }
    }

    @Test
    public void testInvalidColorFromString() {
        try {
            Color.fromString("nope");
            fail("Expected InvalidColorException");
        } catch (InvalidColorException e) {
            // pass
        }
    }
}
