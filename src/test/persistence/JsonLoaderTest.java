package persistence;

import model.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Modeled on JsonSerializationDemo example project
public class JsonLoaderTest extends JsonTest {

    @Test
    public void testLoaderNoFile() {
        JsonLoader loader = new JsonLoader("./data/nope.json");
        try {
            Game game = loader.load();
            fail("Expected IOException");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testLoaderBadFile() {
        JsonLoader loader = new JsonLoader("./data/invalid_game_test.json");
        try {
            Game game = loader.load();
            fail("Expected IOException");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testLoaderNoCards() {
        JsonLoader loader = new JsonLoader("./data/no_cards_test.json");
        try {
            Game game = loader.load();
            checkNoCards(game);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testWildDiscard() {
        JsonLoader loader = new JsonLoader("./data/wild_discard_test.json");
        try {
            Game game = loader.load();
            checkWildDiscard(game);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testLoaderFullGame() {
        JsonLoader loader = new JsonLoader("./data/full_game_test.json");
        try {
            Game game = loader.load();
            checkFullGame(game);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }
}
