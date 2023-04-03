package ui;

import model.Game;
import model.log.Event;
import model.log.EventLog;
import persistence.JsonLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

// The main UI class, which handles the main frame and transitions between screens
public class UnoUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    public static final String SAVE_FILE = "./data/save.json";

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
        addWindowListener(new CloseListener());
    }

    // Modifies: this
    // Effects: centers the main application window in the desktop
    // from AlarmSystem project
    private void centerOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // Modifies: this
    // Effects: remove the start screen and put up the new game screen
    public void newGame() {
        setScreen(new NewGameScreen(this));
    }

    // Modifies: this
    // Effects: transition to the main game screen with the given game
    public void startGame(Game game) {
        setScreen(new GameScreen(game, this));
    }

    // Modifies: this
    // Effects: load the game screen with the game from file,
    //          or do nothing if game is invalid
    public void loadGame() {
        JsonLoader loader = new JsonLoader(SAVE_FILE);
        Game game = null;
        try {
            game = loader.load();
        } catch (IOException e) {
            System.out.println("Invalid game file");
        }

        if (game != null) {
            startGame(game);
        }
    }

    // Modifies: this
    // Effects: removes the current screen and changes it to the given one
    private void setScreen(JPanel screen) {
        getContentPane().removeAll();
        add(screen);
        revalidate();
    }

    // Modifies: this
    // Effects: resets back to the start screen
    public void restart() {
        setJMenuBar(null);
        setScreen(new StartScreen(this));
    }

    // Watch for window closing to log the events
    private static class CloseListener implements WindowListener {

        // Effects: log all events to console
        @Override
        public void windowClosing(WindowEvent e) {
            EventLog eventLog = EventLog.getInstance();
            for (Event event : eventLog) {
                System.out.println(event);
            }
        }

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}
