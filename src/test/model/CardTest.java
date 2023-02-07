package model;

import model.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card yellow0;
    Card green9;
    Card red4;

    Card bluePlus2;
    Card plus4;
    Card wild;
    Card redSkip;
    Card greenReverse;

    @BeforeEach
    public void setup() {
        yellow0 = new NumberCard(Color.YELLOW, 0);
        green9 = new NumberCard(Color.GREEN, 9);
        red4 = new NumberCard(Color.RED, 4);

        bluePlus2 = new PowerCard(Color.BLUE, Face.PLUS2);
        plus4 = new PowerCard(Color.WILD, Face.PLUS4);
        wild = new PowerCard(Color.WILD, Face.WILD);
        redSkip = new PowerCard(Color.RED, Face.SKIP);
        greenReverse = new PowerCard(Color.GREEN, Face.REVERSE);
    }

    @Test
    public void testNumberCardConstructor() {
        assertEquals(Color.YELLOW, yellow0.getColor());
        assertEquals(0, yellow0.getNumber());

        assertEquals(Color.GREEN, green9.getColor());
        assertEquals(9, green9.getNumber());

        assertEquals(Color.RED, red4.getColor());
        assertEquals(4, red4.getNumber());
    }

    @Test
    public void testPowerCardConstructor() {
        assertEquals(Color.BLUE, bluePlus2.getColor());
        assertEquals(Face.PLUS2, bluePlus2.getFace());
        assertEquals(-1, bluePlus2.getNumber());

        assertEquals(Color.WILD, plus4.getColor());
        assertEquals(Face.PLUS4, plus4.getFace());
        assertEquals(-1, plus4.getNumber());
    }

    @Test
    public void testSetColor() {
        plus4.setColor(Color.GREEN);
        assertEquals(Color.GREEN, plus4.getColor());
    }

    @Test
    public void testNumberCardCanPlayOn() {
        Card yellow5 = new NumberCard(Color.YELLOW, 5);
        Card red0 = new NumberCard(Color.RED, 0);

        assertTrue(green9.canPlayOn(green9));

        assertFalse(yellow0.canPlayOn(green9));
        assertFalse(green9.canPlayOn(yellow0));

        assertTrue(yellow0.canPlayOn(yellow5));
        assertTrue(yellow5.canPlayOn(yellow0));
        assertTrue(red0.canPlayOn(yellow0));
        assertTrue(yellow0.canPlayOn(red0));
    }

    @Test
    public void testPowerCardCanPlayOn() {
        Card redPlus2 = new PowerCard(Color.RED, Face.PLUS2);
        Card blueSkip = new PowerCard(Color.BLUE, Face.SKIP);

        assertFalse(bluePlus2.canPlayOn(redSkip));
        assertTrue(bluePlus2.canPlayOn(redPlus2));
        assertTrue(bluePlus2.canPlayOn(blueSkip));

        assertTrue(plus4.canPlayOn(bluePlus2));
        assertTrue(plus4.canPlayOn(redSkip));
        assertTrue(wild.canPlayOn(bluePlus2));
        assertTrue(wild.canPlayOn(redSkip));
    }

    @Test
    public void testCanPlayOnBoth() {
        assertFalse(yellow0.canPlayOn(wild));

        assertFalse(green9.canPlayOn(bluePlus2));
        assertTrue(green9.canPlayOn(greenReverse));

        assertTrue(redSkip.canPlayOn(red4));
        assertTrue(greenReverse.canPlayOn(green9));
    }

    @Test
    public void testCanPlayOnWild() {
        assertTrue(wild.canPlayOn(redSkip));
        assertTrue(wild.canPlayOn(red4));

        assertFalse(red4.canPlayOn(wild));
        assertFalse(redSkip.canPlayOn(wild));
        wild.setColor(Color.RED);
        assertTrue(red4.canPlayOn(wild));
        assertTrue(redSkip.canPlayOn(wild));
    }

    @Test
    public void testNumberCardToString() {
        assertEquals("Yellow 0", yellow0.toString());
        assertEquals("Green 9", green9.toString());
        assertEquals("Red 4", red4.toString());
    }

    @Test
    public void testPowerCardToString() {
        assertEquals("Blue +2", bluePlus2.toString());
        assertEquals("+4", plus4.toString());
        assertEquals("Wild", wild.toString());
        assertEquals("Red Skip", redSkip.toString());
        assertEquals("Green Reverse", greenReverse.toString());
    }

    @Test
    public void testToStringChangeColor() {
        plus4.setColor(Color.BLUE);
        wild.setColor(Color.RED);

        assertEquals("Blue +4", plus4.toString());
        assertEquals("Red Wild", wild.toString());
    }
}