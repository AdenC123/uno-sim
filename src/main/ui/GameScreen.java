package ui;

import model.Game;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

// The main game screen
public class GameScreen extends JPanel {
    private final Game game;
    private final UnoUI mainFrame;

    private JMenuItem save;
    private JMenuItem load;
    private JMenuItem restart;

    private JLabel turnLabel;
    private CardPanel discardPanel;

    private static final Dimension TURN_LABEL_SIZE = new Dimension(100, 50);
    private static final ImageIcon CARD_BACK_IMAGE = new ImageIcon("./images/uno_card_back.png");

    // Effects: constructs the game screen to display the given game
    public GameScreen(Game game, UnoUI mainFrame) {
        this.game = game;
        this.mainFrame = mainFrame;

        addMenuBar();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(createTurnPanel());
        add(createDeckPanel());
//        add(Box.createVerticalStrut(50));
//        add(createCardsPanel());
    }

    // Modifies: this, mainFrame
    // Effects: adds the menu bar to the main frame
    private void addMenuBar() {
        MenuListener menuListener = new MenuListener();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        restart = new JMenuItem("Restart");
        save.addActionListener(menuListener);
        load.addActionListener(menuListener);
        restart.addActionListener(menuListener);
        menu.add(save);
        menu.add(load);
        menu.add(restart);
        menuBar.add(menu);

        mainFrame.setJMenuBar(menuBar);
        mainFrame.revalidate();
    }

    // Effects: creates the panel that stores the turn label
    private JPanel createTurnPanel() {
        JPanel turnPanel = new JPanel();
        turnLabel = new JLabel();
        turnLabel.setPreferredSize(TURN_LABEL_SIZE);
        turnPanel.add(turnLabel);
        updateTurnLabel();
        return turnPanel;
    }

    // Effects: sets the turnLabel to the current player
    private void updateTurnLabel() {
        String currentPlayer = String.valueOf(game.getCurrentPlayer().getId());
        turnLabel.setText("Player " + currentPlayer + "'s turn");
    }

    // Effects: creates the panel with the draw deck and discard pile
    private JPanel createDeckPanel() {
        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(new BoxLayout(deckPanel, BoxLayout.X_AXIS));
        JLabel deckLabel = new JLabel(CARD_BACK_IMAGE);
        deckLabel.addMouseListener(new DrawListener());
        deckPanel.add(deckLabel);

        discardPanel = new CardPanel(game.getDiscard());
        deckPanel.add(discardPanel);
        return deckPanel;
    }

    // Effects: set the discard panel to the current discard
    private void updateDiscardPanel() {
        discardPanel.setCard(game.getDiscard());
    }

    private JPanel createCardsPanel() {
        return null; // TODO
    }

    // Effects: load the game from file and reset the view
    private void loadGame() {
        System.out.println("Loading game!"); // TODO
    }

    // Effects: save the game to file
    private void saveGame() {
        JsonWriter writer = new JsonWriter(UnoUI.SAVE_FILE);
        try {
            writer.write(game);
            System.out.println("Saved to file " + UnoUI.SAVE_FILE);
        } catch (IOException e) {
            System.out.println("Error saving to file");
        }
    }

    // Listener for menu items
    private class MenuListener implements ActionListener {
        // Effects: save, load or reset the game according to menu item
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source.equals(save)) {
                saveGame();
            } else if (source.equals(load)) {
                loadGame();
            } else if (source.equals(restart)) {
                mainFrame.restart();
            }
        }
    }

    // Listener for deck
    private class DrawListener implements MouseListener {
        // Effects: draws a card for the current player
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Deck clicked"); // TODO
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
