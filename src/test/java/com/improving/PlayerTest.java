package com.improving;

import com.improving.players.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    List<Card> hand;
    Game game;

    @BeforeEach
    void init() {
        // Arrange
        game = new Game();
        player = new Player("Ethan", game);
        hand = player.getHand();
        hand.clear();
        game.getDeck().getDiscard().clear();
    }

    @Test
    void initializeHand_should_contain_x_cards() {
        // Act
        var result = new Player("Ethan", game).getHand().size();

        // Assert
        assertEquals(7, result);
    }

    @Test
    void Hand_of_7_cards_should_leave_deck_with_105_cards() {
        // Arrange
        var newGame = new Game();
        newGame.getDeckPile().clear();
        newGame.getDeckPile().addAll(new Deck().getDeck());

        // Act
        new Player("Ethan", newGame);
        var result = newGame.getDeckPile().size();

        // Assert
        assertEquals(112-7, result);
    }

    @Test
    void playCard_should_remove_a_card_from_hand() {
        // Arrange
        player.getHand().addAll(Arrays.asList(
                new Card(Colors.Red, Faces.Five),
                new Card(Colors.Red, Faces.Five),
                new Card(Colors.Red, Faces.Five),
                new Card(Colors.Red, Faces.Five),
                new Card(Colors.Red, Faces.Five),
                new Card(Colors.Red, Faces.Five),
                new Card(Colors.Red, Faces.Five)
        ));
        game.getDiscard().add(new Card(Colors.Blue, Faces.Five));

        // Act
        player.takeTurn(game);
        var result = player.getHand().size();

        // Assert
        assertEquals(6, result);
    }

    @Test
    void takeTurn_Should_Remove_One_Card_From_Hand() {
        //Arrange
        game.getDeck().getDiscard().add(new Card(Colors.Blue, Faces.Two));
        player.getHand().addAll(Arrays.asList(
                new Card(Colors.Blue, Faces.Five),
                new Card(Colors.Red, Faces.Five),
                new Card(Colors.Green, Faces.Five),
                new Card(Colors.Yellow, Faces.Five),
                new Card(Colors.Red, Faces.Five),
                new Card(Colors.Blue, Faces.Five),
                new Card(Colors.Red, Faces.Five)
        ));
        //Act
        player.takeTurn(game);
        var result = player.getHand().size();

        //Assert
        assertEquals(6, result);
    }
    @Test
    void takeTurn_Should_Add_One_Card_To_DiscardPile() {
        //Arrange
        game.getDeck().getDiscard().add(new Card(Colors.Blue, Faces.Two));
        player.getHand().addAll(Arrays.asList(
                new Card(Colors.Blue, Faces.Five)
        ));

        //Act
        player.takeTurn(game);
        var result = game.getDeck().getDiscard().size();

        //Assert
        assertEquals(2, result);
    }
    @Test
    void takeTurn_Should_Play_First_Playable_Card() {
        //Arrange
        game.getDeck().getDiscard().add(new Card(Colors.Blue, Faces.Two));
        hand.clear();
        hand.addAll(Arrays.asList(
                new Card(Colors.Red, Faces.Seven),
                new Card(Colors.Red, Faces.Five),
                new Card(Colors.Green, Faces.Six),
                new Card(Colors.Yellow, Faces.Zero),
                new Card(Colors.Blue, Faces.Two),
                new Card(Colors.Red, Faces.Three),
                new Card(Colors.Green, Faces.One)
        ));
        //Act
        player.takeTurn(game);
        var result = game.getDeck().getDiscard().size();

        //Assert
        assertEquals(2, result);

    }
    @Test
    void takeTurn_Should_Draw_Card_If_Not_Playable() {
        //Arrange
        game.getDeck().getDiscard().add(new Card(Colors.Blue, Faces.Two));
        hand.clear();
        hand.addAll(Arrays.asList(
                new Card(Colors.Red, Faces.Seven),
                new Card(Colors.Red, Faces.Five)

        ));
        //Act
        player.takeTurn(game);
        var result = hand.size();

        //Assert
        assertEquals(3, result);
    }

    @Test
    void takeTurn_Should_Choose_A_Color_For_Game_When_Wild_Card_Is_Played() {
        //Arrange
        game.getDeck().getDiscard().add(new Card(Colors.Blue, Faces.Two));
        hand.clear();
        hand.addAll(Arrays.asList(
                new Card(null, Faces.Wild)
        ));

        //Act
        player.takeTurn(game);
        var result = game.getChosenColor().isPresent();

        //Assert
        assertTrue(result);
    }

    @Test
    void chooseColor_should_return_a_color() {
        // Arrange
        var player = new Player("Ethan", game);

        // Act
        var result = player.chooseColor(game).getClass();

        // Assert
        assertEquals(Colors.class, result);
    }

    @Test
    void countCardsByColor_should_return_number_of_blues_in_player_hand() {
        // Arrange
        player.getHand().addAll(Arrays.asList(
                new Card(Colors.Blue, Faces.Seven),
                new Card(Colors.Blue, Faces.Five),
                new Card(Colors.Blue, Faces.Six),
                new Card(Colors.Yellow, Faces.Zero),
                new Card(Colors.Blue, Faces.Two),
                new Card(Colors.Blue, Faces.Three),
                new Card(Colors.Blue, Faces.One)
        ));

        // Act
        var result = player.countCardsByColor(Colors.Blue);

        // Assert
        assertEquals(6, result);
    }
}