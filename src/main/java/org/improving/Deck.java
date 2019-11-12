package org.improving;

import java.util.*;

public class Deck {
    private LinkedList<Card> deck;
    private LinkedList<Card> discard;

    public Deck() {
        deck = new LinkedList<>();
        discard = new LinkedList<>();
        for (var face : Face.values()) {
            for (var color : Color.values()) {
                if (face.getValue() != 50) {
                    deck.add(new Card(color, face));
                }
                deck.add(new Card(color, face));
            }
        }
        Collections.shuffle(deck);
    }

    public Card draw() {
        var card = deck.getLast();
        deck.remove(card);
        return card;
    }

    public LinkedList<Card> getDeck() {
        return deck;
    }

    public LinkedList<Card> getDiscard() {
        return discard;
    }
}
