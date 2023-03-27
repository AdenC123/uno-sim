package ui;

import model.Game;
import model.Player;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// The main game screen
public class GameScreen extends JPanel {
    private static final ImageIcon CARD_BACK_IMAGE = new ImageIcon("./images/uno_card_back.png");
    public static final int DECK_DISCARD_SPACING = 20;

    private final Game game;
    private final UnoUI mainFrame;

    private JMenuItem save;
    private JMenuItem load;
    private JMenuItem restart;

    private JLabel turnLabel;
    private CardPanel discardPanel;

    private List<CardPanel> cardPanels;
    private JScrollPane cardsPane;

    // Effects: constructs the game screen to display the given game
    public GameScreen(Game game, UnoUI mainFrame) {
        this.game = game;
        this.mainFrame = mainFrame;

        addMenuBar();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(10));
        add(createTurnPanel());
        add(Box.createVerticalStrut(10));
        add(createDeckPanel());
        add(Box.createVerticalStrut(50));
        add(createCardsPane());
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
    }

    // Modifies: this
    // Effects: creates the panel that stores the turn label
    private JPanel createTurnPanel() {
        JPanel turnPanel = new JPanel();

        turnLabel = new JLabel();
        turnLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        updateTurnLabel();

        turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.X_AXIS));
        turnPanel.add(turnLabel);
        turnPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        return turnPanel;
    }

    // Modifies: this
    // Effects: sets the turnLabel to the current player
    private void updateTurnLabel() {
        String currentPlayer = String.valueOf(game.getCurrentPlayer().getId());
        turnLabel.setText("Player " + currentPlayer + "'s turn");
    }

    // Modifies: this
    // Effects: creates the panel with the draw deck and discard pile
    private JPanel createDeckPanel() {
        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(new FlowLayout(FlowLayout.CENTER, DECK_DISCARD_SPACING, 0));
        deckPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));

        JLabel deckLabel = new JLabel(CARD_BACK_IMAGE);
        deckLabel.addMouseListener(new DrawListener());

        discardPanel = new CardPanel(game.getDiscard());

        deckPanel.add(deckLabel);
        deckPanel.add(discardPanel);
        return deckPanel;
    }

    // Modifies: this
    // Effects: set the discard panel to the current discard
    private void updateDiscardPanel() {
        discardPanel.setCard(game.getDiscard());
        discardPanel.revalidate();
    }

    // Modifies: this
    // Effects: creates the panel to display the cards in the current player's hand
    private JScrollPane createCardsPane() {
        JPanel cardsPanel = new JPanel();
        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.pink));

        cardsPane = new JScrollPane(cardsPanel);
        cardsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cardsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        cardsPane.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        Player currentPlayer = game.getCurrentPlayer();
        cardPanels = new ArrayList<>();
        PlayListener playListener = new PlayListener();

        for (int i = 0; i < currentPlayer.handSize(); i++) {
            CardPanel cardPanel = new CardPanel(currentPlayer.getCard(i));
            cardPanel.addMouseListener(playListener);
            cardPanels.add(cardPanel);
            cardsPanel.add(cardPanel);
        }
        return cardsPane;
    }

    // Modifies: this
    // Effects: refreshes the cards pane with a new one created for the current player
    private void updateCardsPane() {
        remove(cardsPane);
        cardsPane = createCardsPane();
        add(cardsPane);
    }

    // Modifies: mainFrame
    // Effects: load the game from file and reset the view
    private void loadGame() {
        mainFrame.loadGame();
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

    // Modifies: this
    // Effects: updates every element of the game screen
    private void updateAll() {
        updateTurnLabel();
        updateCardsPane();
        updateDiscardPanel();
        revalidate();
        repaint();
    }

    // Listener for menu items
    private class MenuListener implements ActionListener {
        // Modifies: this, mainFrame
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
        // Modifies: this, game
        // Effects: draws a card for the current player
        @Override
        public void mousePressed(MouseEvent e) {
            game.drawCard();
            updateAll();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

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

    // Listener for cards in hand
    private class PlayListener implements MouseListener {

        // Modifies: this, game
        // Effects: play the card clicked on
        @Override
        public void mousePressed(MouseEvent e) {
            CardPanel source = (CardPanel) e.getSource();
            int index = cardPanels.indexOf(source);

            if (game.canPlayCard(index)) {
                game.playCard(index);
                updateAll();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

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
