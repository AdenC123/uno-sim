package ui;

import model.Game;
import model.Player;
import model.cards.Card;
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
    public static final int DECK_DISCARD_SPACING = 30;
    public static final boolean SHOW_BORDERS = false;
    public static final Dimension COLOR_DIALOG_SIZE = new Dimension(500, 100);
    public static final model.cards.Color DEFAULT_COLOR = model.cards.Color.BLUE;

    private final Game game;
    private final UnoUI mainFrame;

    private JMenuItem save;
    private JMenuItem load;
    private JMenuItem restart;

    private JLabel turnLabel;
    private CardPanel discardPanel;

    private List<CardPanel> cardPanels;
    private JScrollPane cardsPane;

    private JDialog colorDialog;
    private JButton blueButton;
    private JButton greenButton;
    private JButton redButton;
    private JButton yellowButton;

    // Effects: constructs the game screen to display the given game
    public GameScreen(Game game, UnoUI mainFrame) {
        this.game = game;
        this.mainFrame = mainFrame;

        addMenuBar();
        createColorDialog();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(15));
        add(createTurnPanel());
        add(Box.createVerticalStrut(40));
        add(createDeckPanel());
        add(Box.createVerticalStrut(15));
        add(createCardsPane());
    }

    // Modifies: this
    // Effects: sets up the color dialog box for wild cards
    private void createColorDialog() {
        colorDialog = new JDialog(mainFrame, "Choose Color", true);
        colorDialog.setLayout(new FlowLayout());
        colorDialog.setSize(COLOR_DIALOG_SIZE);
        centerColorDialog();

        ColorListener colorListener = new ColorListener();
        blueButton = new JButton("Blue");
        redButton = new JButton("Red");
        yellowButton = new JButton("Yellow");
        greenButton = new JButton("Green");
        blueButton.addActionListener(colorListener);
        redButton.addActionListener(colorListener);
        yellowButton.addActionListener(colorListener);
        greenButton.addActionListener(colorListener);
        colorDialog.add(blueButton);
        colorDialog.add(redButton);
        colorDialog.add(yellowButton);
        colorDialog.add(greenButton);
    }

    // Modifies: this
    // Effects: centers the color dialog window in the desktop
    private void centerColorDialog() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        colorDialog.setLocation((width - mainFrame.getWidth()) / 2, (height - mainFrame.getHeight()) / 2);
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
        updateTurnLabel();
        turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.X_AXIS));
        turnPanel.add(turnLabel);

        if (SHOW_BORDERS) {
            turnPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
            turnLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

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

        JLabel deckLabel = new JLabel(CARD_BACK_IMAGE);
        deckLabel.addMouseListener(new DrawListener());

        discardPanel = new CardPanel(game.getDiscard());

        deckPanel.add(deckLabel);
        deckPanel.add(discardPanel);

        if (SHOW_BORDERS) {
            deckPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        }

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

        cardsPane = new JScrollPane(cardsPanel);
        cardsPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cardsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        Player currentPlayer = game.getCurrentPlayer();
        cardPanels = new ArrayList<>();
        PlayListener playListener = new PlayListener();

        for (int i = 0; i < currentPlayer.handSize(); i++) {
            CardPanel cardPanel = new CardPanel(currentPlayer.getCard(i));
            cardPanel.addMouseListener(playListener);
            cardPanels.add(cardPanel);
            cardsPanel.add(cardPanel);
        }

        if (SHOW_BORDERS) {
            cardsPane.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            cardsPanel.setBorder(BorderFactory.createLineBorder(Color.pink));
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
        // Effects: play the card clicked on, check for wins
        @Override
        public void mousePressed(MouseEvent e) {
            CardPanel source = (CardPanel) e.getSource();
            int index = cardPanels.indexOf(source);

            if (game.canPlayCard(index)) {
                if (game.isWild(index)) {
                    game.playCard(index);
                    centerColorDialog();
                    colorDialog.setVisible(true);
                } else {
                    game.playCard(index);
                }
                updateAll();
            }

            if (game.isOver()) {
                int winner = game.getCurrentPlayer().getId();
                JOptionPane.showMessageDialog(mainFrame, "Player " + winner + " wins!");
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

    // Listener for choices in the color dialog
    private class ColorListener implements ActionListener {

        // Set the color of the discard wild card to the current color
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            Card discard = game.getDiscard();
            if (source == blueButton) {
                discard.setColor(model.cards.Color.BLUE);
            } else if (source == yellowButton) {
                discard.setColor(model.cards.Color.YELLOW);
            } else if (source == redButton) {
                discard.setColor(model.cards.Color.RED);
            } else if (source == greenButton) {
                discard.setColor(model.cards.Color.GREEN);
            } else {
                discard.setColor(DEFAULT_COLOR);
            }
            colorDialog.setVisible(false);
        }
    }
}
