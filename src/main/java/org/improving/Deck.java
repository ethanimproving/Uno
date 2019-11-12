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
                if (face.getValue() == 50) {
                    deck.add(new Card(null, face));
                } else {
                    deck.add(new Card(color, face));
                    deck.add(new Card(color, face));
                }
            }
        }

        Collections.shuffle(deck);
    }

    public Card draw() {
        try {
            var card = deck.getLast();
            deck.remove(card);
            return card;
        } catch (NoSuchElementException e) {
            System.out.println("GAME OVER. The deck has been replenished with cards from the discard pile.");
            replenishDeck();
        }
        return null;
    }

    private void replenishDeck() {
        var topCard = discard.getLast();
        discard.remove(topCard);
        deck.addAll(discard);
        discard.clear();
        discard.add(topCard);
        Collections.shuffle(deck);
    }

    public LinkedList<Card> getDeck() {
        return deck;
    }

    public LinkedList<Card> getDiscard() {
        return discard;
    }
}
