package com.improving;

public interface iGame {

    boolean isPlayable(Card card);
    void playCard(Card card, Colors colors);
    Card draw();

    Deck getDeck();
}