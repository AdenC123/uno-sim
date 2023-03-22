package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

// The main UI class, which handles the main frame and transitions between screens
public class UnoUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // Effects: starts the UI at the start screen
    public UnoUI() {
        setupFrame();
        setScreen(new StartScreen(this));
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
        setScreen(new NewGameScreen(this));
    }

    // Effects: transition to the main game screen with the given game
    public void startGame(Game game) {
        setScreen(new GameScreen(game, this));
    }

    // Effects: removes the current screen and changes it to the given one
    private void setScreen(JPanel screen) {
        getContentPane().removeAll();
        add(screen);
        revalidate();
    }

    // Effects: resets back to the start screen
    public void restart() {
        setJMenuBar(null);
        setScreen(new StartScreen(this));
    }
}
