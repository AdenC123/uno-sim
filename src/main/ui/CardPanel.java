package ui;

import model.cards.Card;

import javax.swing.*;
import java.awt.*;

// A JPanel that represents a face-up Uno card
public class CardPanel extends JPanel {
    private static final Dimension CARD_SIZE = new Dimension(100, 153);

    private final JLabel cardLabel;

    // Effects: add labels to the panel to describe the card
    public CardPanel(Card card) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(CARD_SIZE);

        cardLabel = new JLabel();
        add(cardLabel, BorderLayout.CENTER);
        cardLabel.setHorizontalAlignment(SwingConstants.CENTER);

        setCard(card);
    }

    // Effects: change the CardPanel to the current card
    public void setCard(Card card) {
        if (card == null) {
            cardLabel.setText("Discard");
        } else {
            cardLabel.setText(card.toString());
        }
    }
}
