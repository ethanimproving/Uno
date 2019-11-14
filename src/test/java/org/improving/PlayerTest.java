package org.improving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Deck deck;
    Player player;
    List<Card> hand;
    Game game;

    @BeforeEach
    void init() {
        // Arrange
        game = new Game();
        player = new Player(game.getDeck());
        hand = player.getHand();
        hand.clear();
        deck.getDiscard().clear();
    }

    @Test
    void initializeHand_should_contain_x_cards() {
        // Act
        var result = new Player(new Deck()).getHand().size();

        // Assert
        assertEquals(7, result);
    }

    @Test
    void Hand_of_7_cards_should_leave_deck_with_105_cards() {
        // Act
        var result = deck.getDeck().size();

        // Assert
        assertEquals(112-7, result);
    }

    @Test
    void play_should_remove_a_card_from_hand() {
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

        // Act
        player.play(deck, player.getHand().getLast());
        var result = player.getHand().size();

        // Assert
        assertEquals(6, result);
    }

    @Test
    void takeTurn_Should_Remove_One_Card_From_Hand() {
        //Arrange
        deck.getDiscard().add(new Card(Color.Blue, Face.Two));
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
        deck.getDiscard().add(new Card(Color.Blue, Face.Two));
        player.getHand().addAll(Arrays.asList(
                new Card(Color.Blue, Face.Five)
        ));

        //Act
        player.takeTurn(game);
        var result = deck.getDiscard().size();

        //Assert
        assertEquals(2, result);
    }
    @Test
    void takeTurn_Should_Play_First_Playable_Card() {
        //Arrange
        deck.getDiscard().add(new Card(Color.Blue, Face.Two));
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
        var result = deck.getDiscard().size();

        //Assert
        assertEquals(2, result);

    }
    @Test
    void takeTurn_Should_Draw_Card_If_Not_Playable() {
        //Arrange
        deck.getDiscard().add(new Card(Color.Blue, Face.Two));
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
        deck.getDiscard().add(new Card(Color.Blue, Face.Two));
        hand.clear();
        hand.addAll(Arrays.asList(
                new Card(null, Face.Wild)
        ));

        //Act
        player.takeTurn(game);
        var result = deck.getDeck().getLast().getColor() != null;

        //Assert
        assertTrue(result);
    }

    @Test
    void takeTurn_Should_Recognize_Discard_Card_And_Make_Player_Draw_Two_If_Card_Is_Draw_Two() {
        // Take turn should take in a player (the next player up) and make him draw to if card is draw two
        // Recognize at start of turn, because if player has a draw 2, they can avoid drawing two, and if they don't,
        // their turn ends.
        //Arrange
        deck.getDiscard().add(new Card(Color.Blue, Face.Two));
        hand.clear();
        hand.addAll(Arrays.asList(
                new Card(null, Face.Wild)
        ));

        //Act
        player.takeTurn(game);
        var result = deck.getDeck().getLast().getColor() != null;

        //Assert
        assertTrue(result);
    }
}