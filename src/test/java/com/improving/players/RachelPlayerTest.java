package com.improving.players;

import com.improving.Card;
import com.improving.Colors;
import com.improving.Faces;
import com.improving.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RachelPlayerTest {

    @Test
    void playCard() {
    }

    @Test
    void declareColor_should_return_color_of_wild() {
        // Arrange
        var game = new Game();
        var rachel = new RachelPlayer("Rachel", game);

        // Act
        var result = rachel.declareColor(new Card(null, Faces.Wild), game);

        // Assert
        assertEquals(null, result);
    }

    @Test
    void getDiscardColorCount() {
    }

    @Test
    void getRankedColors() {
    }

    @Test
    void getDiscardFaceCount() {
    }

    @Test
    void getRankedFaces() {
    }

    @Test
    void getDiscardCardCount() {
    }

    @Test
    void getRankedCards() {
    }

    @Test
    void getPlayableCards() {
    }

    @Test
    void useActionCard() {
    }

    @Test
    void getOptimalActionCard() {
    }

    @Test
    void getOptimalColor() {
    }

    @Test
    void getOptimalFace() {
    }

    @Test
    void equalCards() {
    }

    @Test
    void yellUno() {
    }
}