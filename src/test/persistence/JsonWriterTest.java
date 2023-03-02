package persistence;

import model.Game;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterIllegalFilename() {
        try {
            Game game = new Game(2, 7);
            JsonWriter writer = new JsonWriter("./data/bad\0 filename:json");
            writer.write(game);
            fail("Expected IOException");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterNoCards() {
        String readFile = "./data/no_cards_test.json";
        String writeFile = "./data/write_test_no_cards.json";
        Game game = readThenWriteThenRead(readFile, writeFile);
        checkNoCards(game);
    }

    @Test
    public void testWriterWildDiscard() {
        String readFile = "./data/wild_discard_test.json";
        String writeFile = "./data/write_test_discard.json";
        Game game = readThenWriteThenRead(readFile, writeFile);
        checkWildDiscard(game);
    }

    @Test
    public void testWriterFullGame() {
        String readFile = "./data/full_game_test.json";
        String writeFile = "./data/write_test_full_game.json";
        Game game = readThenWriteThenRead(readFile, writeFile);
        checkFullGame(game);
    }

    // Effects: loads the game from readFile and attempts to write it to writeFile,
    //          then reads it back from writeFile and returns it
    private Game readThenWriteThenRead(String readFile, String writeFile) {
        JsonLoader loader = new JsonLoader(readFile);
        Game game = null;
        try {
            game = loader.load();
        } catch (IOException e) {
            fail("Read error before writing");
        }

        JsonWriter writer = new JsonWriter(writeFile);
        try {
            writer.write(game);
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        }

        JsonLoader check_loader = new JsonLoader(writeFile);
        Game result = null;
        try {
            result = check_loader.load();
        } catch (IOException e) {
            fail("Read error after writing");
        }
        return result;
    }

}
