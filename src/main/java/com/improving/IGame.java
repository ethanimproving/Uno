package com.improving;

import java.util.List;

public interface IGame {

    boolean isPlayable(Card card);
    void playCard(Card card, Colors colors);
    Card draw();

    int getTurnIndex();
    int getTurnDirection();

    List<IPlayer> getPlayers();
    Deck getDeck();
}