package ui;

import model.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The screen shown after pressing New Game, which prompts number of players and cards
public class NewGameScreen extends JPanel {
    private static final String PLAYERS_LABEL_TEXT = "Choose number of players: ";
    private static final String CARDS_LABEL_TEXT = "Choose number of starting cards: ";
    private static final int BUTTON_SPACING = 15;

    private static final int INITIAL_PLAYERS = 2;
    private static final int MIN_PLAYERS = 2;
    private static final int INITIAL_CARDS = 7;
    private static final int MIN_CARDS = 1;

    private final UnoUI mainUI;
    private JSpinner playersSpinner;
    private JSpinner cardsSpinner;

    // Effects: constructs the new game screen with inputs
    public NewGameScreen(UnoUI mainUI) {
        this.mainUI = mainUI;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(100));
        add(createPlayersChooser());
        add(Box.createVerticalStrut(50));
        add(createCardsChooser());
        add(Box.createVerticalStrut(50));
        add(createStartButton());
    }

    // Effects: creates the panel with the players chooser
    private JPanel createPlayersChooser() {
        JPanel playersChooser = new JPanel();
        JLabel playersLabel = new JLabel(PLAYERS_LABEL_TEXT);
        playersChooser.add(playersLabel);
        playersChooser.add(Box.createHorizontalStrut(BUTTON_SPACING));

        SpinnerNumberModel model = new SpinnerNumberModel();
        model.setValue(INITIAL_PLAYERS);
        model.setMinimum(MIN_PLAYERS);
        playersSpinner = new JSpinner(model);
        playersChooser.add(playersSpinner);

        return playersChooser;
    }

    // Effects: creates the panel with the cards chooser
    private JPanel createCardsChooser() {
        JPanel cardsChooser = new JPanel();
        JLabel cardsLabel = new JLabel(CARDS_LABEL_TEXT);
        cardsChooser.add(cardsLabel);
        cardsChooser.add(Box.createHorizontalStrut(BUTTON_SPACING));

        SpinnerNumberModel model = new SpinnerNumberModel();
        model.setValue(INITIAL_CARDS);
        model.setMinimum(MIN_CARDS);
        cardsSpinner = new JSpinner(model);
        cardsChooser.add(cardsSpinner);

        return cardsChooser;
    }

    private JButton createStartButton() {
        JButton startButton = new JButton("Start!");
        startButton.addActionListener(new StartAction());
        return startButton;
    }

    // Effects: constructs a new Game object from the current spinner values
    private Game makeGame() {
        int numPlayers = (int) playersSpinner.getValue();
        int numCards = (int) cardsSpinner.getValue();
        return new Game(numPlayers, numCards);
    }

    private class StartAction implements ActionListener {

        // Effects: give the main UI a new game to start
        @Override
        public void actionPerformed(ActionEvent e) {
            mainUI.startGame(makeGame());
        }
    }
}
