package model;

import model.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game game;
    Player player1;
    Player player2;

    Game threePlayers;
    Player threePlayers1;
    Player threePlayers2;
    Player threePlayers3;

    @BeforeEach
    public void setup() {
        game = new Game(2, 7);
        player1 = game.getPlayer(1);
        player2 = game.getPlayer(2);

        threePlayers = new Game(3, 5);
        threePlayers1 = threePlayers.getPlayer(1);
        threePlayers2 = threePlayers.getPlayer(2);
        threePlayers3 = threePlayers.getPlayer(3);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, player1.getId());
        assertEquals(2, player2.getId());

        assertEquals(1, game.getCurrentPlayer().getId());

        assertEquals(7, player1.handSize());
        assertEquals(7, player2.handSize());

        assertEquals(5, threePlayers1.handSize());
        assertEquals(5, threePlayers2.handSize());
    }

    @Test
    public void testPassTurn() {
        Player secondPlayer = game.passTurn();
        assertEquals(2, secondPlayer.getId());

        Player thirdPlayer = game.passTurn();
        assertEquals(1, thirdPlayer.getId());
    }

    @Test
    public void testGetHand() {
        assertEquals(player1.handToString(), game.getHand());
        game.passTurn();
        assertEquals(player2.handToString(), game.getHand());
    }

    @Test
    public void testDrawCard() {
        String card1 = game.drawCard();
        assertEquals(8, player1.handSize());
        assertEquals(card1, player1.getCard(7).toString());
        assertEquals(2, game.getCurrentPlayer().getId());

        String card2 = game.drawCard();
        assertEquals(8, player2.handSize());
        assertEquals(card2, player2.getCard(7).toString());
        assertEquals(1, game.getCurrentPlayer().getId());

        String card3 = game.drawCard();
        assertEquals(9, player1.handSize());
        assertEquals(card3, player1.getCard(8).toString());
        assertEquals(2, game.getCurrentPlayer().getId());
    }

    @Test
    public void testCanPlayCardNoDiscard() {
        assertFalse(game.canPlayCard(7));
        assertTrue(game.canPlayCard(6));
        game.drawCard();
        game.drawCard();
        assertTrue(game.canPlayCard(7));
    }

    @Test
    public void testPlayCard() {
        Card blue4 = new NumberCard(Color.BLUE, 4);
        Card blue7 = new NumberCard(Color.BLUE, 7);
        Card yellow0 = new NumberCard(Color.YELLOW, 0);
        player1.drawGivenCard(yellow0);
        player2.drawGivenCard(blue7);
        player1.drawGivenCard(blue4);

        // player 1 turn: play the blue 4
        assertEquals(1, game.getCurrentPlayer().getId());
        assertEquals(9, player1.handSize());

        assertTrue(game.canPlayCard(8));
        String card1 = game.playCard(8);
        assertEquals(card1, blue4.toString());
        assertEquals(card1, game.getDiscard().toString());
        assertEquals(8, player1.handSize());

        // player 2 turn: play the blue 7
        assertEquals(2, game.getCurrentPlayer().getId());
        assertEquals(8, player2.handSize());

        assertTrue(game.canPlayCard(7));
        String card2 = game.playCard(7);
        assertEquals(card2, blue7.toString());
        assertEquals(card2, game.getDiscard().toString());
        assertEquals(7, player2.handSize());

        // player 1 turn: can't play the yellow 0
        assertEquals(1, game.getCurrentPlayer().getId());
        assertEquals(8, player1.handSize());

        assertFalse(game.canPlayCard(7));
    }

    @Test
    public void testPlus2() {
        Card plus2 = new PowerCard(Color.BLUE, Face.PLUS2);
        threePlayers1.drawGivenCard(plus2);

        assertTrue(threePlayers.canPlayCard(5));
        String card1 = threePlayers.playCard(5);

        assertEquals(card1, plus2.toString());
        assertEquals(7, threePlayers2.handSize());
        assertEquals(3, threePlayers.getCurrentPlayer().getId());
    }

    @Test
    public void testWild() {
        Card wild = new PowerCard(Color.WILD, Face.WILD);
        player1.drawGivenCard(wild);

        assertTrue(game.canPlayCard(7));
        String card = game.playCard(7, Color.BLUE);
        assertEquals(Color.BLUE, wild.getColor());
        assertEquals(Color.BLUE, game.getDiscard().getColor());
    }

    @Test
    public void testPlus4() {
        Card plus4 = new PowerCard(Color.WILD, Face.PLUS4);
        threePlayers1.drawGivenCard(plus4);
        threePlayers.playCard(5, Color.RED);

        assertEquals(Color.RED, plus4.getColor());
        assertEquals(9, threePlayers2.handSize());
        assertEquals(3, threePlayers.getCurrentPlayer().getId());
    }

    @Test
    public void testSkip() {
        Card skip = new PowerCard(Color.YELLOW, Face.SKIP);
        threePlayers1.drawGivenCard(skip);
        threePlayers.playCard(5);

        assertEquals(3, threePlayers.getCurrentPlayer().getId());
    }

    @Test
    public void testReverseThreePlayers() {
        Card reverse1 = new PowerCard(Color.YELLOW, Face.REVERSE);
        Card reverse2 = new PowerCard(Color.YELLOW, Face.REVERSE);
        threePlayers1.drawGivenCard(reverse1);
        threePlayers1.drawGivenCard(reverse2);
        threePlayers.playCard(6);

        assertEquals(3, threePlayers.getCurrentPlayer().getId());
        threePlayers.passTurn();
        assertEquals(2, threePlayers.getCurrentPlayer().getId());
        threePlayers.passTurn();
        assertEquals(1, threePlayers.getCurrentPlayer().getId());

        threePlayers.playCard(5);
        assertEquals(2, threePlayers.getCurrentPlayer().getId());
    }

    @Test
    public void testGameOver() {
        Game gameOverGame = new Game(2, 0);
        Player gameOver1 = gameOverGame.getPlayer(1);

        Card blue4 = new NumberCard(Color.BLUE, 4);
        gameOver1.drawGivenCard(blue4);

        assertFalse(gameOverGame.isOver());
        gameOverGame.playCard(0);

        assertEquals(1, gameOverGame.getCurrentPlayer().getId());
        assertTrue(gameOverGame.isOver());
    }

    @Test
    public void testIsWild() {
        Card blue4 = new NumberCard(Color.BLUE, 4);
        Card wild = new PowerCard(Color.WILD, Face.WILD);
        Card plus4 = new PowerCard(Color.WILD, Face.PLUS4);

        player1.drawGivenCard(blue4);
        player1.drawGivenCard(wild);
        player1.drawGivenCard(plus4);

        assertFalse(game.isWild(7));
        assertTrue(game.isWild(8));
        assertTrue(game.isWild(9));
    }

    @Test
    public void testCanPlayInvalid() {
        assertFalse(game.canPlayCard(-1));
        assertFalse(game.canPlayCard(-5));
        assertFalse(game.canPlayCard(8));
        assertFalse(game.canPlayCard(10));
    }

    @Test
    // this should never happen according to requires
    public void testGetPlayerNoPlayers() {
        Game noPlayers = new Game(0, 0);
        assertNull(noPlayers.getPlayer(1));
    }

}
