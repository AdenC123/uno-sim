package model;

import model.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Player player1;
    Player player2;

    NumberCard yellow0;
    NumberCard green9;
    NumberCard red4;

    PowerCard bluePlus2;
    PowerCard plus4;
    PowerCard wild;
    PowerCard redSkip;
    PowerCard greenReverse;

    @BeforeEach
    public void setup() {
        player1 = new Player(1);

        yellow0 = new NumberCard(player1, Color.YELLOW, 0);
        green9 = new NumberCard(player2, Color.GREEN, 9);
        red4 = new NumberCard(player1, Color.RED, 4);

        bluePlus2 = new PowerCard(player1, Color.BLUE, Power.PLUS2);
        plus4 = new PowerCard(player2, Color.WILD, Power.PLUS4);
        wild = new PowerCard(player1, Color.WILD, Power.WILD);
        redSkip = new PowerCard(player1, Color.RED, Power.SKIP);
        greenReverse = new PowerCard(player1, Color.GREEN, Power.REVERSE);
    }

    @Test
    public void testNumberCardConstructor() {
        assertEquals(1, yellow0.getOwner().getId());
        assertEquals(2, green9.getOwner().getId());

        assertEquals(Color.YELLOW, yellow0.getColor());
        assertEquals(0, yellow0.getNumber());

        assertEquals(Color.GREEN, green9.getColor());
        assertEquals(9, green9.getNumber());

        assertEquals(Color.RED, red4.getColor());
        assertEquals(4, red4.getNumber());
    }

    @Test
    public void testPowerCardConstructor() {
        assertEquals(1, bluePlus2.getOwner().getId());
        assertEquals(2, plus4.getOwner().getId());

        assertEquals(Color.BLUE, bluePlus2.getColor());
        assertEquals(Power.PLUS2, bluePlus2.getPower());

        assertEquals(Color.WILD, plus4.getColor());
        assertEquals(Power.PLUS4, plus4.getPower());
    }

    @Test
    public void testSetColor() {
        plus4.setColor(Color.GREEN);
        assertEquals(Color.GREEN, plus4.getColor());
    }

    @Test
    public void testNumberCardCanPlayOn() {
        Card yellow5 = new NumberCard(player1, Color.YELLOW, 5);
        Card red0 = new NumberCard(player1, Color.RED, 0);

        assertFalse(yellow0.canPlayOn(green9));
        assertTrue(yellow0.canPlayOn(yellow5));
        assertTrue(yellow5.canPlayOn(red0));
    }

    @Test
    public void testPowerCardCanPlayOn() {
        Card redPlus2 = new PowerCard(player1, Color.RED, Power.PLUS2);
        Card blueSkip = new PowerCard(player1, Color.BLUE, Power.SKIP);

        assertFalse(bluePlus2.canPlayOn(redSkip));
        assertTrue(bluePlus2.canPlayOn(redPlus2));
        assertTrue(bluePlus2.canPlayOn(blueSkip));

        assertTrue(plus4.canPlayOn(bluePlus2));
        assertTrue(wild.canPlayOn(bluePlus2));
    }

    @Test
    public void testCanPlayOnBoth() {
        assertFalse(yellow0.canPlayOn(wild));
        assertFalse(green9.canPlayOn(bluePlus2));
        assertTrue(red4.canPlayOn(redSkip));
        assertTrue(greenReverse.canPlayOn(green9));
    }

    @Test
    public void testCanPlayOnWild() {
        assertTrue(wild.canPlayOn(bluePlus2));
        assertTrue(wild.canPlayOn(yellow0));
        assertTrue(wild.canPlayOn(plus4));

        assertFalse(bluePlus2.canPlayOn(wild));
        wild.setColor(Color.BLUE);
        assertTrue(bluePlus2.canPlayOn(wild));
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