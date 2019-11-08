package org.improving;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;

    public Hand(Deck deck, int size) {
        hand = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.hand.add(deck.draw());
        }
    }

    public void pick(Deck deck) {
        this.hand.add(deck.draw());
    }

    public Card play(Deck deck, Card card) {
        hand.remove(card);
        deck.getDiscard().add(card);
        return card;
    }

    public List<Card> getHand() {
        return hand;
    }


}
