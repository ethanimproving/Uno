package com.improving;

public interface IPlayerInfo {
    int getPlayerHandSize();
    IPlayer getPrevPlayer();
    IPlayer getNextPlayer();
    IPlayer getNextNextPlayer();

}
