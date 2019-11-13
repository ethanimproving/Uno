package org.improving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Deck deck;
    Player player;

    @BeforeEach
    void init() {
        // Arrange
        deck = new Deck();
        player = new Player(deck);
        player.getHand().clear();
        deck.getDiscard().clear();
    }

    @Test
    void initializeDiscardPile() {
    }

    @Test
    void startGame() {
    }

    @Test
    void isPlayable_should_return_true_when_player_hand_topcard_color_is_equal_to_deck_discard_topcard() {
        // Arrange
        player.getHand().add(new Card(Color.Red, Face.Nine));
        deck.getDiscard().add(new Card(Color.Red, Face.Five));

        // Act
        var result = Game.isPlayable(deck, player.getHand().get(0));

        // Assert
        assertTrue(result);
    }

    @Test
    void isPlayable_should_return_true_when_player_hand_topcard_face_is_equal_to_deck_discard_topcard() {
        // Arrange
        player.getHand().add(new Card(Color.Blue, Face.Five));
        deck.getDiscard().add(new Card(Color.Red, Face.Five));

        // Act
        var result = Game.isPlayable(deck, player.getHand().get(0));

        // Assert
        assertTrue(result);
    }

    @Test
    void isPlayable_should_return_false_when_neither_faces_nor_colors_match() {
        // Arrange
        player.getHand().add(new Card(Color.Blue, Face.Six));
        deck.getDiscard().add(new Card(Color.Red, Face.Five));

        // Act
        var result = Game.isPlayable(deck, player.getHand().get(0));

        // Assert
        assertFalse(result);
    }

    @Test
    void isPlayable_should_return_true_when_wild_card() {
        // Arrange
        player.getHand().add(new Card(null, Face.Wild));
        deck.getDiscard().add(new Card(Color.Red, Face.Five));

        // Act
        var result = Game.isPlayable(deck, player.getHand().get(0));

        // Assert
        assertTrue(result);
    }
}