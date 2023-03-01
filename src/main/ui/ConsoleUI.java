package ui;

import exceptions.InvalidColorException;
import model.Game;
import model.cards.Color;

import java.util.Scanner;

public class ConsoleUI {
    private static final int NUM_LINES_TO_CLEAR = 10;

    private final Scanner scan;
    private Game game;

    public ConsoleUI() {
        scan = new Scanner(System.in);
    }

    // Modifies: this
    // Effects: Runs the console UI in a loop
    public void run() {
        game = setupGame();
        boolean gameOver = false;
        while (!gameOver) {
            clearConsole();
            startTurn();
            takeTurn();
            gameOver = checkGameOver();
        }
    }

    // Effects: Creates a game with user-input players and starting cards.
    private Game setupGame() {
        System.out.println("Welcome to UNO!");
        System.out.print("Choose number of players: ");
        int numPlayers = Integer.parseInt(scan.nextLine());
        System.out.print("Choose number of starting cards: ");
        int numStartingCards = Integer.parseInt(scan.nextLine());
        return new Game(numPlayers, numStartingCards);
    }

    // Effects: introduces player and prints their hand
    private void startTurn() {
        System.out.println("Player " + game.getCurrentPlayer().getId() + "'s turn");
        if (game.getDiscard() != null) {
            System.out.println("The current card is " + game.getDiscard());
        }
        System.out.println(game.getHand());
    }

    // Effects: gives player a choice of card to play, or draw
    private void takeTurn() {
        int cardNum = -1;
        // prompt until player draws or chooses a correct card TODO: input validation
        do {
            System.out.print("Choose a card to play by typing its number, "
                    + "type \"draw\" to draw a card, or type \"save\" to save the game: ");
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("draw")) {
                String output = game.drawCard();
                System.out.println("Drew a " + output);
                return;
            } else if (input.equalsIgnoreCase("save")) {
                saveGame();
                System.out.println("Saved!");
            } else {
                cardNum = Integer.parseInt(input) - 1; // convert to 0 based
            }
        } while (!game.canPlayCard(cardNum));

        String output;
        if (game.isWild(cardNum)) {
            output = handleWild(cardNum);
        } else {
            output = game.playCard(cardNum);
        }

        System.out.println("Playing " + output);
    }

    // Effects: Allow the user to set the color of a wild card
    private String handleWild(int cardNum) {
        Color color = null;
        do {
            System.out.print("What color would you like to set this card? (blue, yellow, red, or green): ");
            try {
                color = Color.fromString(scan.nextLine());
            } catch (InvalidColorException e) {
                System.out.println("Invalid color!");
            }
        } while (color == null);
        return game.playCard(cardNum, color);
    }

    // Effects: Save the game to a file //TODO
    private void saveGame() {
    }

    // Effects: returns whether game is over, prints a congratulation message if it iss
    private boolean checkGameOver() {
        if (game.isOver()) {
            System.out.println("Player " + game.getCurrentPlayer().getId() + " wins!");
            return true;
        } else {
            return false;
        }
    }

    // Effects: clears the console by printing a lot of newlines
    private void clearConsole() {
        for (int i = 0; i < NUM_LINES_TO_CLEAR; i++) {
            System.out.println();
        }
    }
}
