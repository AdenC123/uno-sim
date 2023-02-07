package model;

import model.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player player1;
    Player player2;
    Card blue5;
    Card wild;

    @BeforeEach
    public void setup() {
        player1 = new Player(1);
        player2 = new Player(2);
        blue5 = new NumberCard(Color.BLUE, 5);
        wild = new PowerCard(Color.WILD, Face.WILD);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, player1.getId());
        assertEquals(2, player2.getId());
        assertEquals(0, player1.handSize());
        assertEquals(0, player2.handSize());
    }

    @Test
    public void testDrawCards() {
        player1.drawCards(0);
        assertEquals(0, player1.handSize());
        player1.drawCards(1);
        assertEquals(1, player1.handSize());
        player1.drawCards(5);
        assertEquals(6, player1.handSize());
    }

    @Test
    public void testDrawGivenCard() {
        player1.drawGivenCard(blue5);
        assertEquals(1, player1.handSize());
        player1.drawGivenCard(wild);
        assertEquals(2, player1.handSize());

        Card at0 = player1.getCard(0);
        Card at1 = player1.getCard(1);

        assertEquals(Color.BLUE, at0.getColor());
        assertEquals(Color.WILD, at1.getColor());
    }

    @Test
    public void testRemoveCardFromStart() {
        player1.drawGivenCard(blue5);
        player1.drawGivenCard(wild);

        Card firstCard = player1.removeCard(0);
        assertEquals(1, player1.handSize());
        assertEquals(Color.BLUE, firstCard.getColor());

        Card secondCard = player1.removeCard(0);
        assertEquals(0, player1.handSize());
        assertEquals(Color.WILD, secondCard.getColor());
    }

    @Test
    public void testRemoveCardFromEnd() {
        player1.drawGivenCard(blue5);
        player1.drawGivenCard(wild);

        Card atEnd = player1.removeCard(1);
        assertEquals(Color.WILD, atEnd.getColor());
        assertEquals(1, player1.handSize());

        Card atStart = player1.removeCard(0);
        assertEquals(Color.BLUE, atStart.getColor());
        assertEquals(0, player1.handSize());
    }

    @Test
    public void testHandToString() {
        player1.drawGivenCard(blue5);
        player1.drawGivenCard(wild);
        player1.drawGivenCard(blue5);

        assertEquals("1:Blue 5, 2:Wild, 3:Blue 5", player1.handToString());
    }
}
