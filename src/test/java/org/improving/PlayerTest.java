package org.improving;

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
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Red, Face.Five)
        ));
        game.getDiscard().add(new Card(Color.Blue, Face.Five));

        // Act
        player.takeTurn(game);
        var result = player.getHand().size();

        // Assert
        assertEquals(6, result);
    }

    @Test
    void takeTurn_Should_Remove_One_Card_From_Hand() {
        //Arrange
        game.getDeck().getDiscard().add(new Card(Color.Blue, Face.Two));
        player.getHand().addAll(Arrays.asList(
                new Card(Color.Blue, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Green, Face.Five),
                new Card(Color.Yellow, Face.Five),
                new Card(Color.Red, Face.Five),
                new Card(Color.Blue, Face.Five),
                new Card(Color.Red, Face.Five)
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
        game.getDeck().getDiscard().add(new Card(Color.Blue, Face.Two));
        player.getHand().addAll(Arrays.asList(
                new Card(Color.Blue, Face.Five)
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
        game.getDeck().getDiscard().add(new Card(Color.Blue, Face.Two));
        hand.clear();
        hand.addAll(Arrays.asList(
                new Card(Color.Red, Face.Seven),
                new Card(Color.Red, Face.Five),
                new Card(Color.Green, Face.Six),
                new Card(Color.Yellow, Face.Zero),
                new Card(Color.Blue, Face.Two),
                new Card(Color.Red, Face.Three),
                new Card(Color.Green, Face.One)
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
        game.getDeck().getDiscard().add(new Card(Color.Blue, Face.Two));
        hand.clear();
        hand.addAll(Arrays.asList(
                new Card(Color.Red, Face.Seven),
                new Card(Color.Red, Face.Five)

        ));
        //Act
        player.takeTurn(game);
        var result = hand.size();

        //Assert
        assertEquals(3, result);
    }

    @Test
    void takeTurn_Should_Choose_A_Color_For_Wild_Card() {
        //Arrange
        game.getDeck().getDiscard().add(new Card(Color.Blue, Face.Two));
        hand.clear();
        hand.addAll(Arrays.asList(
                new Card(null, Face.Wild)
        ));

        //Act
        player.takeTurn(game);
        var result = game.getDeck().getDeck().getLast().getColor() != null;

        //Assert
        assertTrue(result);
    }

    @Test
    void chooseColor_should_return_a_color() {
        // Arrange
        var newGame = new Game();

        newGame.getPlayers().get(0).getHand().clear();
        newGame.getPlayers().get(0).getHand().add(new Card(null, Face.Wild));
        newGame.getDiscard().add(new Card(Color.Blue, Face.Seven));

        // Act
        newGame.getPlayers().get(0).takeTurn(newGame);
        var result = newGame.getDiscard().getLast().getColor().getClass();

        // Assert
        assertEquals(Color.class, result);
    }
}