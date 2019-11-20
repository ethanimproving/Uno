package com.improving.players;

import com.improving.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SmartPlayerTest {

    IGame game;
    SmartPlayer smartPlayer;

    @BeforeEach
    void arrange() {
        game = new Game();
        smartPlayer = new SmartPlayer("Ethan", game);
        game.getDiscard().add(new Card(Colors.Red, Faces.Seven));

        smartPlayer.getHand().clear();
        smartPlayer.getHand().addAll(Arrays.asList(
                new Card(Colors.Yellow, Faces.Seven),
                new Card(Colors.Blue, Faces.Eight),
                new Card(Colors.Green, Faces.Seven),
                new Card(Colors.Green, Faces.Eight),
                new Card(Colors.Blue, Faces.Seven),
                new Card(Colors.Blue, Faces.DrawTwo)
        ));

    }

    @Test
    void pickCard_should_pick_card_with_most_colors_in_hand() {
        // Act
        var result = smartPlayer.pickCard(game).getColors();

        // Assert
        assertEquals(Colors.Blue, result);
    }

    @Test
    void pickCard_should_pick_card_with_second_most_colors_if_most_colors_is_not_playable() {
        // Arrange
        game.getDiscard().add(new Card(Colors.Green, Faces.Two));

        // Act
        var result = smartPlayer.pickCard(game).getColors();

        // Assert
        assertEquals(Colors.Green, result);
    }

    @Test
    void pickCard_should_pick_card_with_highest_value() {
        // Arrange
        game.getDiscard().add(new Card(Colors.Blue, Faces.Seven));

        // Act
        var result = smartPlayer.pickCard(game).toString();

        // Assert
        assertEquals(new Card(Colors.Blue, Faces.DrawTwo).toString(), result);
    }

    @Test
    void pickCard_should_choose_color_with_most_colors_in_hand() {
        // Act
        var result = smartPlayer.chooseColor(game);

        // Assert
        assertEquals(Colors.Blue, result);
    }

    @Test
    void getBestColor_should_return_null_if_no_cards_are_playable() {
        // Arrange
        game.getDiscard().add(new Card(Colors.Green, Faces.Three));
        smartPlayer.getHand().clear();
        smartPlayer.getHand().addAll(Arrays.asList(
                new Card(Colors.Yellow, Faces.Five),
                new Card(Colors.Yellow, Faces.Zero),
                new Card(Colors.Blue, Faces.Eight),
                new Card(Colors.Red, Faces.Seven)
        ));

        // Act
        var result = smartPlayer.chooseColor(game);

        // Assert
        assertEquals(null, result);

    }

    @Test
    void getBestColor_should_not_return_null_for_wild_color() {

    }
}