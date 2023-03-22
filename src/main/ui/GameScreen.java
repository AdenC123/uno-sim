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

    // Effects: constructs the game screen to display the given game
    public GameScreen(Game game, UnoUI mainFrame) {
        this.game = game;
        this.mainFrame = mainFrame;

        JMenuBar menuBar = createMenuBar();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        mainFrame.setJMenuBar(menuBar);
        mainFrame.revalidate();
//        add(createTurnLabel());
//        add(createDeckPanel());
//        add(Box.createVerticalStrut(50));
//        add(createCardsPanel());
    }

    private JMenuBar createMenuBar() {
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

        return menuBar;
    }

    private JLabel createTurnLabel() {
        return null; //TODO
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
