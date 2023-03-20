package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // Effects: constructs the initial start screen window
    public StartScreen() {
        setTitle("Welcome to Uno");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerOnScreen();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(70));
        add(createUnoLogo());
        add(Box.createVerticalStrut(70));
        add(createStartButtons());

        setVisible(true);
    }

    // Effects: Returns a JLabel with the UNO logo (don't sue me)
    private JLabel createUnoLogo() {
        ImageIcon unoLogo = new ImageIcon("./images/uno_logo.png");
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

    // Effects: centers the main application window in the desktop
    // from AlarmSystem project
    private void centerOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    private class StartGameAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("start game");
        }
    }

    private class LoadGameAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("load game");
        }
    }


}
