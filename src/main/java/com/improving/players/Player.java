package com.improving.players;

import com.improving.Card;
import com.improving.IGame;

public class Player extends BasePlayerClass {

    public Player(String name, IGame game) {
        super(name, game);
    }

    @Override
    protected Card pickCard(IGame game) {
        for (var card : hand) {
            if (game.isPlayable(card)) {
                return card;
            }
        }
        return null;
    }


}
