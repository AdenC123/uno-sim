package ui;

import model.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JPanel {
    private final Game game;
    private final UnoUI mainFrame;

    private JMenuItem save;
    private JMenuItem load;
    private JMenuItem restart;

    private JLabel turnLabel;

    // Effects: constructs the game screen to display the given game
    public GameScreen(Game game, UnoUI mainFrame) {
        this.game = game;
        this.mainFrame = mainFrame;

        addMenuBar();
        add(createTurnPanel());
//        add(createDeckPanel());
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
        // TODO resize turn label
        turnPanel.add(turnLabel);
        updateTurnLabel();
        return turnPanel;
    }

    // Effects: sets the turnLabel to the current player
    private void updateTurnLabel() {
        String currentPlayer = String.valueOf(game.getCurrentPlayer().getId());
        turnLabel.setText("Player " + currentPlayer + "'s turn");
    }

    private JPanel createDeckPanel() {
        return null; //TODO
    }

    private JPanel createCardsPanel() {
        return null; //TODO
    }

    private class MenuListener implements ActionListener {
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

    private void loadGame() {
        System.out.println("Loading game!"); //TODO
    }

    private void saveGame() {
        System.out.println("Saving game!"); //TODO
    }
}
