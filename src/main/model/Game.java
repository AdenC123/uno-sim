package model;

// The state of a game of Uno. Handles player turns and power card effects
public class Game {

    // Requires: numPlayers > 1, numStartingCards > 0
    // Effects: Constructs a game with number of players, deals them all numStartingCards cards,
    //          and sets the current player to player 1
    public Game(int numPlayers, int numStartingCards) {
        //stub
    }

    // Effects: returns the current player's id number
    public int getPlayer() {
        return -1; //stub
    }

    // Effects: returns the current player's hand in a readable format
    public String getHand() {
        return ""; //stub
    }

    // Modifies: this
    // Effects: the current player draws a card and passes the turn
    //          returns the card drawn in readable format
    public String drawCard() {
        return "";
    }

    // Effects: return true if index is inside the player's hand and the card at
    //          index can be played onto the current discard pile
    public boolean canPlayCard(int index) {
        return false; //stub
    }

    // Requires: canPlayCard(index) is true
    // Modifies: this
    // Effects: plays and returns the card at index from the player's hand,
    //          executes any special card effects, checks if the game is over,
    //          and passes the turn if it is not
    public String playCard(int index) {
        return "";
    }

    // Effects: returns true if the current player has won the game, returns false if
    //          the game is not over
    public boolean isOver() {
        return false; //stub
    }
}
