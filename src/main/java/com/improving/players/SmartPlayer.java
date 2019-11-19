package com.improving.players;

import com.improving.Card;
import com.improving.Colors;
import com.improving.IGame;

import java.util.*;

public class SmartPlayer extends BasePlayerClass {
    public SmartPlayer(String name, IGame game) {
        super(name, game);
    }

    @Override
    protected Card pickCard(IGame game) {
         Colors topPlayableColor = getBestColor();
        var playableCards = hand.stream().filter(game::isPlayable);
        return playableCards.filter(c -> c.getColors().equals(topPlayableColor))
                .max(Comparator.comparing(c -> c.getFaces().getValue())).orElse(null);
    }

    private Colors getBestColor() {
        Map<Colors, Integer> colorRank = new HashMap<>();
        for (Colors color : Colors.values()) {
            colorRank.put(color, countCardsByColor(color));
        }
        return Collections.max(colorRank.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

    @Override
    public int countCardsByColor(Colors color) {
        return super.countCardsByColor(color);
    }
}
