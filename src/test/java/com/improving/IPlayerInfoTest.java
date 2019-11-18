package com.improving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPlayerInfoTest {

    Game game;
    IPlayerInfo playerInfo;

    @BeforeEach
    void arrange() {
        game = new Game();
        playerInfo = new Player("Ethan", game);
    }

    @Test
    void getPlayerHandSize() {
    }

    @Test
    void getPrevPlayer() {
    }

    @Test
    void getNextPlayer() {
        // Act
        var result = game.getPlayers().get(0).getNextPlayer(game);

        // Assert
        assertEquals(game.getPlayers().get(1), result);
    }

    @Test
    void getNextNextPlayer() {
        // Act
        var result = game.getPlayers().get(0).getNextNextPlayer(game);

        // Assert
        assertEquals(game.getPlayers().get(2), result);
    }
}