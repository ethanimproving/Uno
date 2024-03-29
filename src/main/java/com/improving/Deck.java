package com.improving;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Deck implements IDeck {
    private LinkedList<Card> deck;
    private LinkedList<Card> discard;

    public Deck() {
        deck = new LinkedList<>();
        discard = new LinkedList<>();

        var realColors = Arrays.stream(Colors.values()).filter(c -> c.getNumber() == 1)
                .collect(Collectors.toList());

        for (var face : Faces.values()) {
            for (var color : realColors) {
                if (face.getValue() == 50) {
                    deck.add(new Card(Colors.Wild, face));
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
            System.out.println("The deck has been replenished with cards from the discard pile.");
            replenishDeck();
            var card = deck.getLast();
            deck.remove(card);
            return card;
        }
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
