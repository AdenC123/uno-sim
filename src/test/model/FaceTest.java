package model;

import exceptions.InvalidFaceException;
import model.cards.Face;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FaceTest {

    @Test
    public void testFaceToString() {
        assertEquals("+2", Face.PLUS2.toString());
        assertEquals("+4", Face.PLUS4.toString());
        assertEquals("Wild", Face.WILD.toString());
        assertEquals("Skip", Face.SKIP.toString());
        assertEquals("Reverse", Face.REVERSE.toString());
        assertEquals("Number", Face.NUMBER.toString());
    }

    @Test
    public void testValidFaceFromString() {
        try {
            assertEquals(Face.PLUS2, Face.fromString("+2"));
            assertEquals(Face.PLUS4, Face.fromString("+4"));
            assertEquals(Face.WILD, Face.fromString("Wild"));
            assertEquals(Face.SKIP, Face.fromString("skip"));
            assertEquals(Face.REVERSE, Face.fromString("REVERSE"));
            assertEquals(Face.NUMBER, Face.fromString("Number"));
        } catch (InvalidFaceException e) {
            fail("Unexpected InvalidFaceException");
        }
    }

    @Test
    public void testInvalidFaceFromString() {
        try {
            Face.fromString("nah");
            fail("Expected InvalidFaceException");
        } catch (InvalidFaceException e) {
            // pass
        }
    }
}
