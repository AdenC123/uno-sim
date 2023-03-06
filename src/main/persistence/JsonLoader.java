package persistence;

import exceptions.InvalidCardException;
import model.Game;
import model.Player;
import model.cards.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Loads games stored as JSON data from a file
// Modeled on JsonSerializationDemo project
public class JsonLoader {
    private final String filename;

    // Effects: constructs loader to load from given filename
    public JsonLoader(String filename) {
        this.filename = filename;
    }

    // Effects: reads and returns the game stored in the file,
    //          throws IOException if a file reading error occurs
    public Game load() throws IOException {
        String jsonData = readFile(filename);
        JSONObject jsonObject = new JSONObject(jsonData);
        Game game;
        try {
            game = parseGame(jsonObject);
        } catch (InvalidCardException e) {
            throw new IOException("Error reading card");
        }
        return game;
    }

    // Effects: reads source file as string and returns it
    // from JsonSerializationDemo project
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // Effects: parses Game object from JSON and returns it
    private Game parseGame(JSONObject jsonObject) throws InvalidCardException {
        int currentPlayer = jsonObject.getInt("currentPlayer");
        boolean reversed = jsonObject.getBoolean("reversed");

        // get discard card- might be null
        Card discard = null;
        if (!jsonObject.isNull("discard")) {
            discard = parseCard(jsonObject.getJSONObject("discard"));
        }

        List<Player> players = parsePlayers(jsonObject.getJSONArray("players"));
        return new Game(players, discard, currentPlayer, reversed);
    }

    // Effects: parses a card from a json object
    private Card parseCard(JSONObject jsonObject) throws InvalidCardException {
        Face face = Face.fromString(jsonObject.getString("face"));
        Color color = Color.fromString(jsonObject.getString("color"));

        Card card;
        if (face == Face.NUMBER) {
            int number = jsonObject.getInt("number");
            card = new NumberCard(color, number);
        } else {
            card = new PowerCard(color, face);
        }
        return card;
    }

    // Effects: parses a list of players from a json array
    private List<Player> parsePlayers(JSONArray jsonArray) throws InvalidCardException {
        List<Player> players = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            int id = jsonObject.getInt("id");
            List<Card> hand = parseHand(jsonObject.getJSONArray("hand"));
            players.add(new Player(id, hand));
        }
        return players;
    }

    // Effects: parse a list of cards from a json array
    private List<Card> parseHand(JSONArray jsonArray) throws InvalidCardException {
        List<Card> hand = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            hand.add(parseCard(jsonObject));
        }
        return hand;
    }

}
