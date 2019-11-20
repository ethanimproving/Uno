package com.improving;

import com.improving.players.IPlayer;
import com.improving.players.IPlayerInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface IGame {

    boolean isPlayable(Card card);
    void playCard(Card card, Optional<Colors> optionalColor);
    Card draw();

    public List<IPlayerInfo> getPlayerInfo();
    public IPlayerInfo getNextPlayer();
    public IPlayerInfo getPreviousPlayer();
    public IPlayerInfo getNextNextPlayer();
    public IDeck getDeckInfo();

    // Confusion

    List<IPlayer> getPlayers();
    Deck getDeck();
    LinkedList<Card> getDiscard();



}