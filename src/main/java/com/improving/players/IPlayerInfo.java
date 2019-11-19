package com.improving.players;

import com.improving.IGame;

public interface IPlayerInfo {
    int getPlayerHandSize();
    IPlayer getPrevPlayer(IGame game);
    IPlayer getNextPlayer(IGame game);
    IPlayer getNextNextPlayer(IGame game);

}
