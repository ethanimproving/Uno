package com.improving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game;

    @BeforeEach
    void init() {
        // Arrange
        game = new Game();
        game.getPlayers().clear();
        game.addPlayer("Ethan");
        game.addPlayer("Jennifer");
        for (var player : game.getPlayers()) player.getHand().clear();
    }

    @Test
    void playGame_should_call_initializeDiscardPile_which_should_add_one_card_to_discard_pile() {
        // Arrange
        game.getPlayers().get(0).getHand().add(new Card(Colors.Yellow, Faces.Five));
        game.getDeckPile().add(new Card(Colors.Yellow, Faces.Five));
        game.getDiscard().add(new Card(Colors.Yellow, Faces.Five));

        // Act
        game.playGame();
        var result = game.getDiscard().size();

        // Assert
        assertEquals(3, result);
    }

    @Test
    void initializeDiscardPile_should_execute_action_card_on_player_one() {
        // Act
        game.getDeckPile().add(new Card(Colors.Blue, Faces.WildDrawFour));
        game.initializeDiscardPile();
        var result = game.getPlayers().get(0).getHand().size();

        // Assert
        assertEquals(4, result);
    }

    @Test
    void playGame_should_declare_first_player_to_run_out_of_cards_as_winner() {
        // Arrange
        game.getDiscard().add(new Card(Colors.Blue, Faces.Five));
        game.getDeckPile().addAll(Arrays.asList(
                new Card(Colors.Blue, Faces.Five),
                new Card(Colors.Blue, Faces.Five),
                new Card(Colors.Blue, Faces.Five)
        ));
        game.getPlayers().get(0).getHand().addAll(Arrays.asList(
                new Card(Colors.Blue, Faces.Five),
                new Card(Colors.Blue, Faces.Five),
                new Card(Colors.Blue, Faces.Five)
        ));
        game.getPlayers().get(1).getHand().addAll(Arrays.asList(
                new Card(Colors.Blue, Faces.Five)
        ));

        // Act
        game.playGame();
        var result = game.getPlayers().get(1).getHand().size();
        var ethanscards = game.getPlayers().get(0).getHand().size();

        // Assert
        assertEquals(0, result);
        assertEquals(2, ethanscards);

    }

    @Test
    void isPlayable_should_return_true_when_player_hand_topcard_color_is_equal_to_deck_discard_topcard() {
        // Arrange
        game.getPlayers().get(0).getHand().add(new Card(Colors.Red, Faces.Nine));
        game.getDiscard().add(new Card(Colors.Red, Faces.Five));

        // Act
        var result = game.isPlayable(game.getPlayers().get(0).getHand().getLast());

        // Assert
        assertTrue(result);
    }

    @Test
    void isPlayable_should_return_true_when_player_hand_topcard_face_is_equal_to_deck_discard_topcard() {
        // Arrange
        game.getPlayers().get(0).getHand().add(new Card(Colors.Blue, Faces.Five));
        game.getDiscard().add(new Card(Colors.Red, Faces.Five));

        // Act
        var result = game.isPlayable(game.getPlayers().get(0).getHand().getLast());

        // Assert
        assertTrue(result);
    }

    @Test
    void isPlayable_should_return_false_when_neither_faces_nor_colors_match() {
        // Arrange
        game.getPlayers().get(0).getHand().add(new Card(Colors.Blue, Faces.Six));
        game.getDiscard().add(new Card(Colors.Red, Faces.Five));

        // Act
        var result = game.isPlayable(game.getPlayers().get(0).getHand().getLast());

        // Assert
        assertFalse(result);
    }

    @Test
    void isPlayable_should_return_true_when_wild_card() {
        // Arrange
        game.getPlayers().get(0).getHand().add(new Card(null, Faces.Wild));
        game.getDiscard().add(new Card(Colors.Red, Faces.Five));

        // Act
        var result = game.isPlayable(game.getPlayers().get(0).getHand().getLast());

        // Assert
        assertTrue(result);
    }

    @Test
    void isPlayable_should_return_true_when_wild_card_is_top_of_discard_with_color_set() {
        // Arrange
        game.getDiscard().add(new Card(null, Faces.Wild));
        game.getDeckPile().add(new Card(Colors.Red, Faces.Seven));
        game.playCard(new Card(null, Faces.Wild), Optional.of(Colors.Red));

        game.getPlayers().get(0).getHand().add(new Card(Colors.Red, Faces.Seven));

        // Act
        var result = game.isPlayable(game.getPlayers().get(0).getHand().getLast());

        // Assert
        assertEquals(game.getChosenColor(), Optional.of(game.getPlayers().get(0).getHand().getLast().getColors()));
        assertTrue(result);
    }

    @Test
    void turnEngine_should_convert_turn_index_into_player_index() {
        // Arrange TODO: get game's turnIndex to 2
        var turnIndex = 2;

        // Act
        var result = game.turnEngine();

        // Assert
        assertEquals(0, result);

        // TODO: get game's turnIndex to 18
        assertEquals(0, game.turnEngine());
    }

    @Test
    void turnEngine_should_convert_negative_turn_index_into_player_index() {
        // Arrange
        game.getDiscard().add(new Card(Colors.Blue, Faces.Reverse));
        game.getPlayers().get(0).getHand().clear();
        game.getPlayers().get(1).getHand().clear();
        game.getPlayers().get(0).getHand().add(new Card(Colors.Blue, Faces.Reverse));
        game.getPlayers().get(0).getHand().add(new Card(Colors.Blue, Faces.Five));
        game.getPlayers().get(1).getHand().add(new Card(Colors.Blue, Faces.Five));

        game.getDeckPile().add(new Card(Colors.Blue, Faces.Five));

        game.playGame();

        // Act
        var result = game.turnEngine();

        // Assert
        assertEquals(1, result);
    }

    @Test
    void turnEngine_should_return_absolute_value_of_conversion() {
        // Arrange
        var newGame = new Game();
        newGame.getDeckPile().clear();
        for (var player : newGame.getPlayers()) player.getHand().clear();
        newGame.getDiscard().add(new Card(Colors.Blue, Faces.Five));
        newGame.getPlayers().get(0).getHand().addAll(Arrays.asList(
                new Card(Colors.Blue, Faces.Reverse)
        ));

        for (var player : newGame.getPlayers()) {
            player.getHand().addAll(Arrays.asList(
                    new Card(Colors.Blue, Faces.Five),
                    new Card(Colors.Blue, Faces.Six),
                    new Card(Colors.Blue, Faces.Seven)
            ));
        }
        newGame.getDeckPile().add(new Card(Colors.Blue, Faces.Seven));

        // Act
        newGame.playGame();

        // Assert: if no exception is raised, test passes.
        assertTrue(true);
    }

    @Test
    void executeSpecialCard_should_make_next_player_draw_four_when_current_player_plays_draw_four() {
        // Arrange
        game.getDiscard().add(new Card(Colors.Blue, Faces.Five));
        game.getPlayers().get(1).getHand().addAll(Arrays.asList(
                new Card(Colors.Blue, Faces.Five),
                new Card(Colors.Blue, Faces.Five)
        ));

        // Act
        game.executeSpecialCard(new Card(Colors.Blue, Faces.WildDrawFour));
        var result = game.getPlayers().get(1).getHand().size();

        // Assert
        assertEquals(2+4, result);
    }

    @Test
    void getPrevPlayer_should_return_player_whose_turn_was_last() {
        // Arrange
        game.addPlayer("Brian");
        game.addPlayer("Caleb");

        // Act
        var result = game.getPreviousPlayer();

        // Assert
        assertEquals(game.getPlayers().get(3).getName(), result.getName());
    }

    @Test
    void getNextPlayer_should_return_player_whose_turn_is_next() {
        // Arrange
        game.addPlayer("Brian");
        game.addPlayer("Caleb");

        // Act
        var result = game.getNextPlayer();

        // Assert
        assertEquals(game.getPlayers().get(1), result);
    }

    @Test
    void getNextNextPlayer_should_return_player_whose_turn_is_two_from_current() {
        // Arrange
        game.addPlayer("Brian");
        game.addPlayer("Caleb");

        // Act
        var result = game.getNextNextPlayer();

        // Assert
        assertEquals(game.getPlayers().get(2), result);
    }
}