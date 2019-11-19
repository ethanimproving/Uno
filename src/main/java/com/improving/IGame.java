package com.improving;

import com.improving.players.IPlayer;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface IGame {

    boolean isPlayable(Card card);
    void playCard(Card card, Optional<Colors> optionalColor);
    Card draw();

    int getTurnIndex();
    int getTurnDirection();

    List<IPlayer> getPlayers();
    Deck getDeck();
    LinkedList<Card> getDiscard();
}