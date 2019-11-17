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
        game = new Game();
        game.getPlayers().clear();
        game.addPlayer("Ethan");
        game.addPlayer("Jennifer");

        player = (Player) game.getPlayers().get(0);
        player2 = (Player) game.getPlayers().get(1);
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
    void turnEngine_should_convert_turn_index_into_player_index() {
        // Arrange
        var turnIndex = 2;

        // Act
        var result = game.turnEngine(turnIndex);

        // Assert
        assertEquals(0, result);
        assertEquals(0, game.turnEngine(18));
    }

    @Test
    void turnEngine_should_convert_negative_turn_index_into_player_index() {
        // Arrange
        var turnIndex = -1;

        // Act
        var result = game.turnEngine(turnIndex);

        // Assert
        assertEquals(1, result);
    }

    @Test
    void turnEngine_should_return_absolute_value_of_conversion() {
        // Arrange
        var newGame = new Game();
        newGame.getDeckPile().clear();
        for (var player : newGame.getPlayers()) player.getHand().clear();
        newGame.getDiscard().add(new Card(Color.Blue, Face.Five));
        newGame.getPlayers().get(0).getHand().addAll(Arrays.asList(
                new Card(Color.Blue, Face.Reverse)
        ));

        for (var player : newGame.getPlayers()) {
            player.getHand().addAll(Arrays.asList(
                    new Card(Color.Blue, Face.Five),
                    new Card(Color.Blue, Face.Six),
                    new Card(Color.Blue, Face.Seven)
            ));
        }


        // Act
        newGame.startGame();

        // Assert: if no exception is raised, test passes.
        assertTrue(true);
    }

    @Test
    void executeSpecialCard_should_make_next_player_draw_four_when_current_player_plays_draw_four() {
        // Arrange
        game.getDiscard().add(new Card(Color.Blue, Face.Five));
        game.getPlayers().get(1).getHand().addAll(Arrays.asList(
                new Card(Color.Blue, Face.Five),
                new Card(Color.Blue, Face.Five)
        ));

        // Act
        game.excuteSpecialCard(new Card(Color.Blue, Face.WildDrawFour));
        var result = game.getPlayers().get(1).getHand().size();

        // Assert
        assertEquals(2+4, result);
    }

}