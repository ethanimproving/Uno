package org.improving;

import java.util.LinkedList;
import java.util.Random;

public class Player implements iPlayer {
    private String name;
    private LinkedList<Card> hand;

    public Player(String name, Deck deck) {
        this.name = name;
        initializeHand(deck, 7);
    }

    private void initializeHand (Deck deck, int size) {
        hand = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            this.hand.add(deck.draw());
        }
    }

    @Override
    public Card takeTurn(Game game) {
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

    private void playCard(Game game, Card card) {
        if (card.getColor() == null) game.playCard(card, chooseWildColor());
        // TODO: make color parameter optional.
        else game.playCard(card, null);
        hand.remove(card);
    }

    private Color chooseWildColor() {
        // TODO: Prompt user to choose color. Hint: Choose different picking strategies for bots.
        var random = new Random().nextInt(4);
        return Color.values()[random];
    }

    @Override
    public LinkedList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    @Override
    public int handSize() {
        return hand.size();
    }

    @Override
    public Card draw(Game game) {
        return game.getDeck().draw();
    }
}
