package org.improving;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Player implements iPlayer {
    private String name;
    private LinkedList<Card> hand;

    public Player(Deck deck) {
        this.name = "Player";
        initializeHand(deck, 7);
        Collections.shuffle(hand);
    }

    public Player(String name, Deck deck) {
        this.name = name;
        initializeHand(deck, 7);
        Collections.shuffle(hand);
    }

    private void initializeHand (Deck deck, int size) {
        hand = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            this.hand.add(deck.draw());
        }
    }

    public Card play(Deck deck, Card card) {
        hand.remove(card);
        deck.getDiscard().add(card);
        return card;
    }

    @Override
    public Card takeTurn(Game game) {
        if (game.getDeck().getDiscard().getLast().getFace() == Face.DrawTwo) {

        }
        for (var card : hand) {
            if (Game.isPlayable(game.getDeck(), card)) {
                if (card.getColor() == null) chooseWildColor(card);

                hand.remove(card);
                game.getDeck().getDiscard().add(card);
                System.out.println(name + " has played " + card + " and finished turn.");
                if (card.getFace() == Face.DrawTwo) { }
                return card;
            }
        }
        var card = game.getDeck().draw();
        hand.add(card);
        System.out.println(name + " has drawn a " + card + " and finished turn.");
        return null;
    }

    private void chooseWildColor(Card card) {
        // TODO: Prompt user to choose color. Hint: Choose different picking strategies for bots.
        var random = new Random().nextInt(4);
        card.setColor(Color.values()[random]);
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
        return 0;
    }

    @Override
    public Card draw(Game game) {
        return game.getDeck().draw();
    }
}
