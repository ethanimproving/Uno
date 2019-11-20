package com.improving.players;

import com.improving.Card;
import com.improving.IGame;

public class RandomPlayer extends BasePlayerClass {

    public RandomPlayer(String name, IGame game) {
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
