package org.improving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    Deck deck;
    Hand hand;

    @BeforeEach
    void init() {
        deck = new Deck();
        hand = new Hand(deck, 7);
    }

    @Test
    void Hand_should_contain_x_cards() {
        // Act
        var result = hand.getHand().size();

        // Assert
        assertEquals(7, result);
    }

    @Test
    void Hand_of_7_cards_should_leave_deck_with_105_cards() {
        // Act
        var result = deck.getDeck().size();

        // Assert
        assertEquals(105, result);
    }

    @Test
    void pick_should_add_1_card_to_hand() {
        // Act
        hand.pick(deck);
        var result = hand.getHand().size();

                // Assert
        assertEquals(8, result);
    }

    @Test
    void pick_should_remove_1_card_from_deck() {
        // Act
        hand.pick(deck);
        var result = deck.getDeck().size();

        // Assert
        assertEquals(104, result);
    }

    @Test
    void play_should_remove_a_card_from_hand() {
        // Act
        hand.play(deck, hand.getHand().get(0));
        var result = hand.getHand().size();

        // Assert
        assertEquals(6, result);
    }
}