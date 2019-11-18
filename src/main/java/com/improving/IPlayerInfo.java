package com.improving;

public interface IPlayerInfo {
    int getPlayerHandSize();
    IPlayer getPrevPlayer(IGame game);
    IPlayer getNextPlayer(IGame game);
    IPlayer getNextNextPlayer(IGame game);

}
