package org.improving;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Player {
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

    public void takeTurn(Deck deck) {
        if (deck.getDiscard().getLast().getFace() == Face.Draw2) {

        }
        for (var card : hand) {
            if (Game.isPlayable(deck, card)) {
                if (card.getColor() == null) chooseWildColor(card);

                hand.remove(card);
                deck.getDiscard().add(card);
                System.out.println(name + " has played " + card + " and finished turn.");
                if (card.getFace() == Face.Draw2) { }
                return;
            }
        }
        var card = deck.draw();
        hand.add(card);
        System.out.println(name + " has drawn a " + card + " and finished turn.");
    }

    private void chooseWildColor(Card card) {
        // TODO: Prompt user to choose color. Hint: Choose different picking strategies for bots.
        var random = new Random().nextInt(4);
        card.setColor(Color.values()[random]);
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
