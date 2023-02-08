package model;

import model.cards.Card;
import model.cards.Color;

import java.util.ArrayList;
import java.util.List;

// The state of a game of Uno. Handles player turns and power card effects
public class Game {
    List<Player> players;
    Card discard;
    int currentPlayer; // this is an ID, not a array index
    boolean reversed;

    // Requires: numPlayers > 1, numStartingCards > 0
    // Effects: Constructs a game with number of players, deals them all numStartingCards cards,
    //          and sets the current player to player 1
    public Game(int numPlayers, int numStartingCards) {
        players = new ArrayList<>();
        discard = null;
        currentPlayer = 1;
        reversed = false;

        for (int i = 1; i <= numPlayers; i++) {
            Player p = new Player(i);
            p.drawCards(numStartingCards);
            players.add(p);
        }
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
        if (index >= p.handSize()) {
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

        doPower(card);

        if (!isOver()) {
            passTurn();
        }
        return card.toString();
    }

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
}
