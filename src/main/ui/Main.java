package ui;

import model.Player;

public class Main {
    public static void main(String[] args) {
        Player testPlayer = new Player(1);
        testPlayer.drawCards(30);
        System.out.println(testPlayer.handToString());
    }
}
