package org.improving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}