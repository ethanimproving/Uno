package org.improving;

public interface iGame {

    boolean isPlayable(Card card);
    void playCard(Card card, Color color);
    Card draw();
}