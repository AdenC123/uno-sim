package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class UnoUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private final StartScreen startScreen;
    private NewGameScreen newGameScreen;

    // Effects: starts the UI at the start screen
    public UnoUI() {
        setupFrame();
        startScreen = new StartScreen(this);
        add(startScreen);
        setVisible(true);
    }

    // Effects: sets up the main frame
    private void setupFrame() {
        setTitle("Uno");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerOnScreen();
    }

    // Effects: centers the main application window in the desktop
    // from AlarmSystem project
    private void centerOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // Effects: remove the start screen and put up the new game screen
    public void newGame() {
        remove(startScreen);
        newGameScreen = new NewGameScreen(this);
        add(newGameScreen);
        revalidate();
    }

    // Effects: transition to the main game screen with the given game
    public void startGame(Game game) {
        System.out.println("Starting game!");
    }
}
