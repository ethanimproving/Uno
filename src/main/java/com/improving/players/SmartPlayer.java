package com.improving.players;

import com.improving.Card;
import com.improving.Colors;
import com.improving.IGame;

import java.util.*;
import java.util.stream.Collectors;

public class SmartPlayer extends BasePlayerClass {
    public SmartPlayer(String name, IGame game) {
        super(name, game);
    }

    @Override
    protected Card pickCard(IGame game) {
        Colors topPlayableColor = getBestColor(game);
        var playableCards = hand.stream().filter(game::isPlayable);
        return playableCards.filter(c -> c.getColors() == topPlayableColor)
                .max(Comparator.comparing(c -> c.getFaces().getValue())).orElse(null);
    }

    private Colors getBestColor(IGame game) {
        Map<Colors, Integer> colorRank = new HashMap<>();
        var anyWild = hand.stream().anyMatch(c -> c.getFaces().getValue() == 50);
        var playableColors = anyWild ? Arrays.asList(Colors.values()) :
                 hand.stream().filter(game::isPlayable)
                .map(Card::getColors).distinct().collect(Collectors.toList());
        for (Colors color : playableColors) {
            colorRank.put(color, countCardsByColor(color));
        }

        // If NoSuchElement, return null
        if (colorRank.isEmpty()) return null;
        return Collections.max(colorRank.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

    @Override
    public int countCardsByColor(Colors color) {
        return super.countCardsByColor(color);
    }

    @Override
    public Colors chooseColor(IGame game) {
        return getBestColor(game);
    }
}
