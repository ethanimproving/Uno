package org.improving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void init() {
        deck = new Deck();
    }

    @Test
    void Deck_should_contain_112_cards() {
        // Act
        var result = deck.getDeck().size();

        // Assert
        assertEquals(112, result);
    }

    @Test
    void draw_should_return_a_card() {
        // Act
        var result = deck.draw().getClass();

        // Assert
        assertEquals(Card.class, result);
    }

    @Test
    void draw_should_remove_a_card_from_deck() {
        // Act
        deck.draw();
        var result = deck.getDeck().size();

        // Assert
        assertEquals(111, result);
    }

    @Test
    void draw_should_replenish_deck_when_all_cards_have_been_drawn() {
        // Arrange
        deck.getDeck().clear();
        deck.getDiscard().addAll(Arrays.asList(
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Blue, Face.Five)
        ));

        // Act
        deck.draw();
        var result = deck.getDeck().size();

        // Assert
        assertEquals(5, result);

    }

    @Test
    void replenishDeck_should_keep_top_card_in_discard_pile() {
        // Arrange
        deck.getDeck().clear();
        deck.getDiscard().addAll(Arrays.asList(
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Blue, Face.Five)
        ));

        // Act
        deck.draw();
        var result = deck.getDiscard().getLast();

        // Assert
        assertEquals(new Card(Color.Blue, Face.Five).toString(), result.toString());
    }

    @Test
    void replenishDeck_should_remove_all_cards_except_top_card_from_discard() {
        // Arrange
        deck.getDeck().clear();
        deck.getDiscard().addAll(Arrays.asList(
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Blue, Face.Five)
        ));

        // Act
        deck.draw();
        var result = deck.getDiscard().size();

        // Assert
        assertEquals(1, result);
    }

    @Test
    void replenishDeck_should_add_all_cards_except_top_card_to_deck() {
        // Arrange
        deck.getDeck().clear();
        deck.getDiscard().addAll(Arrays.asList(
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Blue, Face.Five)
        ));

        // Act
        deck.draw();
        var result = deck.getDeck().size();

        // Assert
        assertEquals(2, result);
    }

    @Test
    void draw_should_return_card_after_replenishing_deck() {
        // Arrange
        deck.getDeck().clear();
        deck.getDiscard().addAll(Arrays.asList(
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Blue, Face.Five)
        ));

        // Act
        var result = deck.draw().getClass();

        // Assert
        assertEquals(Card.class, result);

    }

    @Test
    void Deck_should_construct_with_8_Reverse_cards() {
        // Arrange
        List<Card> reserveCards = new ArrayList<>();
        for (var card : deck.getDeck()) {
            if (card.getFace() == Face.Reverse) reserveCards.add(card);
        }

        // Act
        var result = reserveCards.size();

        // Assert
        assertEquals(8, result);
    }
}