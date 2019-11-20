package com.improving.players;

import com.improving.Card;
import com.improving.IGame;

import java.util.LinkedList;

public interface IPlayer extends IPlayerInfo {

    Card draw(IGame game);
    Card takeTurn(IGame game);

    LinkedList<Card> getHand();
    String getName();

}
