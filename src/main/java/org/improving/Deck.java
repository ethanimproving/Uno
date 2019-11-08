package org.improving;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck;
    private List<Card> discard;

    public Deck() {
        deck = new ArrayList<>();
        discard = new ArrayList<>();
        for (var face : Face.values()) {
            for (var color : Color.values()) {
                if (face.getValue() != 50) {
                    deck.add(new Card(color, face));
                }
                deck.add(new Card(color, face));
            }
        }
    }

    public Card draw() {
        var random = new Random().nextInt(deck.size());
        var card = deck.get(random);
        deck.remove(card);
        discard.add(card);
        return card;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getDiscard() {
        return discard;
    }
}
