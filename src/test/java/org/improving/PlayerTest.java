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

    @BeforeEach
    void init() {
        // Arrange
        deck = new Deck();
        player = new Player(deck);
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
    void isPlayable_should_return_true_when_hand_topcard_color_is_equal_to_deck_discard_topcard() {
        // Arrange
        hand.add(new Card(Color.Red, Face.Nine));
        deck.getDiscard().add(new Card(Color.Red, Face.Five));

        // Act
        var result = player.isPlayable(deck, hand.get(0));

        // Assert
        assertTrue(result);
    }

    @Test
    void isPlayable_should_return_true_when_hand_topcard_face_is_equal_to_deck_discard_topcard() {
        // Arrange
        hand.add(new Card(Color.Blue, Face.Five));
        deck.getDiscard().add(new Card(Color.Red, Face.Five));

        // Act
        var result = player.isPlayable(deck, hand.get(0));

        // Assert
        assertTrue(result);
    }

    @Test
    void isPlayable_should_return_false_when_neither_faces_nor_colors_match() {
        // Arrange
        hand.add(new Card(Color.Blue, Face.Six));
        deck.getDiscard().add(new Card(Color.Red, Face.Five));

        // Act
        var result = player.isPlayable(deck, hand.get(0));

        // Assert
        assertFalse(result);
    }

    @Test
    void isPlayable_should_return_true_when_wild_card() {
        // Arrange
        hand.add(new Card(null, Face.Wild));
        deck.getDiscard().add(new Card(Color.Red, Face.Five));

        // Act
        var result = player.isPlayable(deck, hand.get(0));

        // Assert
        assertTrue(result);
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
        player.takeTurn(deck);
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
        player.takeTurn(deck);
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
        player.takeTurn(deck);
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
        player.takeTurn(deck);
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
        player.takeTurn(deck);
        var result = deck.getDeck().getLast().getColor() != null;

        //Assert
        assertTrue(result);
    }
}