package model;

import model.cards.Card;
import model.cards.Color;
import model.log.Event;
import model.log.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// The state of a game of Uno. Handles player turns and power card effects
public class Game implements Writable {
    List<Player> players;
    Card discard;
    int currentPlayer; // this is an ID (starting at 1), not a array index
    boolean reversed;
    EventLog eventLog;

    // Requires: numPlayers > 1, numStartingCards > 0
    // Effects: Constructs a game with number of players, deals them all numStartingCards cards,
    //          and sets the current player to player 1
    public Game(int numPlayers, int numStartingCards) {
        eventLog = EventLog.getInstance();
        String logString = String.format("Starting new game with %d players and %d starting cards...",
                numPlayers, numStartingCards);
        eventLog.logEvent(new Event(logString));

        players = new ArrayList<>();
        discard = null;
        currentPlayer = 1;
        reversed = false;

        for (int i = 1; i <= numPlayers; i++) {
            Player p = new Player(i);
            p.drawCards(numStartingCards);
            players.add(p);
        }

        eventLog.logEvent(new Event("Starting game!\n"));
    }

    // Requires: players is not empty, currentPlayer's ID is in players
    // Effects: Constructs a game in progress, with a list of players, discard card, current player, and
    //          whether the turn order is reversed
    public Game(List<Player> players, Card discard, int currentPlayer, boolean reversed) {
        eventLog = EventLog.getInstance();
        this.players = players;
        this.discard = discard;
        this.currentPlayer = currentPlayer;
        this.reversed = reversed;
        eventLog.logEvent(new Event("Loading game from file"));
    }

    // Effects: returns the card on top of the discard pile
    public Card getDiscard() {
        return discard;
    }

    // Requires: 0 < id <= numPlayers
    // Effects: gets the player with the given id
    public Player getPlayer(int id) {
        for (Player p : players) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Effects: returns the current player
    public Player getCurrentPlayer() {
        return getPlayer(currentPlayer);
    }

    // Modifies: this
    // Effects: passes the turn to the next player in order and returns them
    public Player passTurn() {
        currentPlayer = getNextPlayer().getId();
        return getCurrentPlayer();
    }

    // Effects: returns the next player in the turn order
    private Player getNextPlayer() {
        int nextPlayer;
        if (reversed) {
            nextPlayer = currentPlayer - 1;
            if (nextPlayer == 0) {
                nextPlayer = players.size();
            }
        } else {
            nextPlayer = currentPlayer + 1;
            if (nextPlayer > players.size()) {
                nextPlayer = 1;
            }
        }
        return getPlayer(nextPlayer);
    }

    // Effects: returns the current player's hand in a readable format
    public String getHand() {
        return getCurrentPlayer().handToString();
    }

    // Modifies: this
    // Effects: the current player draws a card and passes the turn
    //          returns the card drawn in readable format
    public String drawCard() {
        Player p = getCurrentPlayer();
        p.drawCards(1);
        Card card = p.getCard(p.handSize() - 1);

        passTurn();
        return card.toString();
    }

    // Effects: return true if index is inside the player's hand and the card at
    //          index can be played onto the current discard pile
    public boolean canPlayCard(int index) {
        Player p = getCurrentPlayer();
        if (index >= p.handSize() || index < 0) {
            return false;
        } else if (discard == null) {
            return true;
        }
        Card card = p.getCard(index);
        return card.canPlayOn(discard);
    }

    // Requires: canPlayCard(index) is true
    // Modifies: this
    // Effects: plays and returns the card at index from the player's hand,
    //          executes any special card effects, checks if the game is over,
    //          and passes the turn if it is not
    public String playCard(int index) {
        Card card = getCurrentPlayer().removeCard(index);
        discard = card;

        String log = "Player " + getCurrentPlayer().getId() + " played a " + card;
        eventLog.logEvent(new Event(log));

        doPower(card);

        if (!isOver()) {
            passTurn();
        }
        return card.toString();
    }

    // Requires: canPlayCard(index) is true
    // Modifies: this
    // Effects: plays the card and changes the color to given, returns the card at index
    public String playCard(int index, Color color) {
        Card card = getCurrentPlayer().removeCard(index);
        card.setColor(color);
        discard = card;

        String log = "Player " + getCurrentPlayer().getId() + " played a " + card;
        eventLog.logEvent(new Event(log));

        doPower(card);
        passTurn();

        return card.toString();
    }

    // Requires: card is a power card
    // Modifies: this
    // Effects: activates the ability of the power card
    private void doPower(Card card) {
        switch (card.getFace()) {
            case PLUS2:
                getNextPlayer().drawCards(2);
                passTurn();
                break;
            case PLUS4:
                getNextPlayer().drawCards(4);
                passTurn();
                break;
            case SKIP:
                passTurn();
                break;
            case REVERSE:
                reversed = !reversed;
                break;
        }
    }

    // Effects: returns true if the current player has won the game, returns false if
    //          the game is not over
    public boolean isOver() {
        return getCurrentPlayer().handSize() == 0;
    }

    // Requires: canPlayCard(index) is true
    // Effects: returns whether the card at index is wild
    public boolean isWild(int index) {
        return getCurrentPlayer().getCard(index).getColor() == Color.WILD;
    }

    // Effects: returns game as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("currentPlayer", getCurrentPlayer().getId());
        json.put("reversed", reversed);
        json.put("players", playersToJson());

        if (discard == null) {
            json.put("discard", JSONObject.NULL);
        } else {
            json.put("discard", discard.toJson());
        }

        return json;
    }

    // Effects: returns list of players as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Player player : players) {
            jsonArray.put(player.toJson());
        }
        return jsonArray;
    }
}
