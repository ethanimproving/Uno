package org.improving;

import java.util.Collections;
import java.util.LinkedList;

public class Player {
    private LinkedList<Card> hand;

    public Player(Deck deck) {
        initializeHand(deck, 7);
        Collections.shuffle(hand);
    }

    private void initializeHand (Deck deck, int size) {
        hand = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            this.hand.add(deck.draw());
        }
    }

    public boolean isPlayable(Deck deck, Card card) {
        var deckTopCard = deck.getDiscard().get(0);

        if (deckTopCard.getColor() == card.getColor() ||
            deckTopCard.getFace() == card.getFace() ||
            card.getFace().getValue() == 50) {
            return true;
        };
        return false;
    }

    public Card play(Deck deck, Card card) {
        hand.remove(card);
        deck.getDiscard().add(card);
        return card;
    }

    public void takeTurn(Deck deck) {
        for (var card : hand) {
            if (isPlayable(deck, card)) {
                hand.remove(card);
                deck.getDiscard().add(card);
                return;
            }
        }
        hand.add(deck.draw());
    }

    public LinkedList<Card> getHand() {
        return hand;
    }
}
