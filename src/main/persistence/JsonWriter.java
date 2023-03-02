package persistence;

import model.Game;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Writes JSON representations of a game to file.
// Modeled on JsonSerializationDemo project
public class JsonWriter {
    private static final int TAB = 4;
    private final String filename;

    // Effects: constructs writer to write to filename
    public JsonWriter(String filename) {
        this.filename = filename;
    }

    // Effects: writes JSON representation of game to file
    public void write(Game game) throws FileNotFoundException {
        JSONObject json = game.toJson();
        String jsonString = json.toString(TAB);
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.write(jsonString);
        }
    }
}
