package persistence;

import model.Game;
import model.Player;
import model.cards.Card;
import model.cards.Color;
import model.cards.Face;

import static org.junit.jupiter.api.Assertions.*;

// Contains methods to check that Game objects are equal to their JSON test files
public abstract class JsonTest {

    // tests that game is equal to no_cards_test.json
    protected void checkNoCards(Game game) {
        assertNull(game.getDiscard());

        Player player1 = game.getCurrentPlayer();
        assertEquals(1, player1.getId());
        assertEquals(0, player1.handSize());

        game.passTurn();
        Player player2 = game.getCurrentPlayer();
        assertEquals(2, player2.getId());
    }

    // tests that game is equal to wild_discard_test.json
    protected void checkWildDiscard(Game game) {
        Card discard = game.getDiscard();
        checkPowerCard(discard, Color.BLUE, Face.WILD);
    }

    // tests that game is equal to full_game_test.json
    protected void checkFullGame(Game game) {
        // check discard
        Card discard = game.getDiscard();
        checkNumberCard(discard, Color.BLUE, 5);

        // check player 2
        Player player2 = game.getCurrentPlayer();
        assertEquals(2, player2.getId());
        assertEquals(5, player2.handSize());
        checkPowerCard(player2.getCard(0), Color.RED, Face.PLUS2);
        checkPowerCard(player2.getCard(1), Color.WILD, Face.PLUS4);
        checkPowerCard(player2.getCard(2), Color.WILD, Face.WILD);
        checkPowerCard(player2.getCard(3), Color.YELLOW, Face.SKIP);
        checkPowerCard(player2.getCard(4), Color.GREEN, Face.REVERSE);

        // check player 1
        game.passTurn();
        Player player1 = game.getCurrentPlayer();
        assertEquals(1, player1.getId());
        assertEquals(0, player1.handSize());

        // check player 3
        game.passTurn();
        Player player3 = game.getCurrentPlayer();
        assertEquals(3, player3.getId());
        assertEquals(4, player3.handSize());
        checkNumberCard(player3.getCard(0), Color.YELLOW, 0);
        checkNumberCard(player3.getCard(1), Color.RED, 3);
        checkNumberCard(player3.getCard(2), Color.GREEN, 9);
        checkNumberCard(player3.getCard(3), Color.BLUE, 0);
    }

    private void checkNumberCard(Card numberCard, Color color, Number number) {
        assertEquals(Face.NUMBER, numberCard.getFace());
        assertEquals(color, numberCard.getColor());
        assertEquals(number, numberCard.getNumber());
    }

    private void checkPowerCard(Card powerCard, Color color, Face face) {
        assertEquals(face, powerCard.getFace());
        assertEquals(color, powerCard.getColor());
    }
}
