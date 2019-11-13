package org.improving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Deck deck;
    Player player;
    Player player2;
    Game game;

    @BeforeEach
    void init() {
        // Arrange
        deck = new Deck();
        game = new Game(deck);
        game.getPlayers().clear();
        game.addPlayer("Ethan");
        game.addPlayer("Jennifer");

        player = game.getPlayers().get(0);
        player2 = game.getPlayers().get(1);
        player.getHand().clear();
        player2.getHand().clear();
        deck.getDiscard().clear();
    }

    @Test
    void initializeDiscardPile() {
    }

    @Test
    void startGame_should_have_declare_first_player_to_run_out_of_cards_as_winner() {
        // Arrange
        game.getDiscard().add(new Card(Color.Blue, Face.Five));
        game.getDeckPile().addAll(Arrays.asList(
                new Card(Color.Blue, Face.Five),
                new Card(Color.Blue, Face.Five),
                new Card(Color.Blue, Face.Five)
        ));
        game.getPlayers().get(0).getHand().addAll(Arrays.asList(
                new Card(Color.Blue, Face.Five),
                new Card(Color.Blue, Face.Five),
                new Card(Color.Blue, Face.Five)
        ));
        game.getPlayers().get(1).getHand().addAll(Arrays.asList(
                new Card(Color.Blue, Face.Five)
        ));

        // Act
        game.startGame();
        var result = game.getPlayers().get(1).getHand().size();
        var ethanscards = game.getPlayers().get(0).getHand().size();

        // Assert
        assertEquals(0, result);
        assertEquals(2, ethanscards);

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

    @Test
    void isPlayable_should_return_false_when_draw_2() {
        // Arrange
        game.getDiscard().add(new Card(Color.Yellow, Face.Draw2));
        var card = new Card(Color.Yellow, Face.Eight);

        // Act
        var result = game.isPlayable(game.getDeck(), card);

        // Assert
        assertFalse(result);
    }

    @Test
    void isPlayable_should_return_true_when_two_draw_2s() {
        // Arrange
        game.getDiscard().add(new Card(Color.Yellow, Face.Draw2));
        var card = new Card(Color.Yellow, Face.Draw2);

        // Act
        var result = game.isPlayable(game.getDeck(), card);

        // Assert
        assertTrue(result);
    }

}