package com.improving;

import java.util.LinkedList;
import java.util.Random;

public class Player implements iPlayer {
    private String name;
    private LinkedList<Card> hand;

    public Player(String name, iGame game) {
        this.name = name;
        initializeHand(game,7);
    }

    private void initializeHand (iGame game, int size) {
        hand = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            this.hand.add(draw(game));
        }
    }

    @Override
    public Card takeTurn(iGame game) {
        for (var card : hand) {
            if (game.isPlayable(card)) {
                playCard(game, card);
                System.out.println(name + " has played " + card + " and finished turn.");
                return card;
            }
        }
        var card = game.getDeck().draw();
        hand.add(card);
        System.out.println(name + " has drawn a " + card + " and finished turn.");
        return null;
    }

    private void playCard(iGame game, Card card) {
        if (card.getColors() == null) game.playCard(card, chooseWildColor());
        // TODO: make color parameter optional.
        else game.playCard(card, null);
        hand.remove(card);
    }

    private Colors chooseWildColor() {
        // TODO: Prompt user to choose color. Hint: Choose different picking strategies for bots.
        var random = new Random().nextInt(4);
        return Colors.values()[random];
    }

    @Override
    public int handSize() {
        return hand.size();
    }

    @Override
    public Card draw(iGame game) {
        return game.draw();
    }

    @Override
    public LinkedList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Card> countCardsByColor(Colors blue) {
        var cards = new LinkedList<Card>();
        return cards;
    }
}
