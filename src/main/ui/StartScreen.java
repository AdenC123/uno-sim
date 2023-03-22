package ui;

import model.Game;
import persistence.JsonLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// The first screen shown to the user, with start and load game buttons
public class StartScreen extends JPanel {
    private final UnoUI mainUI;
    private static final String SAVE_FILE = "./data/save.json";
    private static final String UNO_IMAGE_FILE = "./images/uno_logo.png";

    // Effects: constructs the start screen panel with logo and buttons
    public StartScreen(UnoUI mainUI) {
        this.mainUI = mainUI;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(70));
        add(createUnoLogo());
        add(Box.createVerticalStrut(70));
        add(createStartButtons());
    }

    // Effects: Returns a JLabel with the UNO logo (don't sue me)
    private JLabel createUnoLogo() {
        ImageIcon unoLogo = new ImageIcon(UNO_IMAGE_FILE);
        return new JLabel(unoLogo);
    }

    // Effects: Returns a JPanel with the Start Game and Load Game buttons
    private JPanel createStartButtons() {
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new StartGameAction());
        JButton loadButton = new JButton("Load Game");
        loadButton.addActionListener(new LoadGameAction());
        buttonPanel.add(startButton);
        buttonPanel.add(loadButton);
        return buttonPanel;
    }

    private class StartGameAction implements ActionListener {
        // Effects: tell the main UI to start the game
        @Override
        public void actionPerformed(ActionEvent e) {
            mainUI.newGame();
        }
    }

    private class LoadGameAction implements ActionListener {
        // Effects: tell the main UI to load the existing game
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                mainUI.startGame(loadGame());
            } catch (IOException ex) {
                // uh oh
                System.out.println("Invalid game file");
            }
        }

        // Effects: load and return the game from file
        private Game loadGame() throws IOException {
            JsonLoader loader = new JsonLoader(SAVE_FILE);
            return loader.load();
        }
    }
}
