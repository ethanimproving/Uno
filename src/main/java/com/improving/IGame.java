package com.improving;

public interface IGame {

    boolean isPlayable(Card card);
    void playCard(Card card, Colors colors);
    Card draw();

    Deck getDeck();
}