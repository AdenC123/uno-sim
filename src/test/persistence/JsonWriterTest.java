package persistence;

import model.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

// Modeled after JsonSerializationDemo project
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

//    @Test
//    public void testWriterNoCards() {
//        try {
//            JsonLoader loader = new JsonLoader("./data/no_cards_test.json");
//        }
//    }

}
