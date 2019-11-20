package com.improving;

import com.improving.players.IPlayerInfo;
import com.improving.players.RandomPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPlayerInfoTest {

    Game game;
    IPlayerInfo playerInfo;

    @BeforeEach
    void arrange() {
        game = new Game();
        game.addPlayer("Brian");
        playerInfo = new RandomPlayer("Ethan", game);
    }

    @Test
    void getPlayerHandSize() {
    }

    @Test
    void getPrevPlayer_should_return_player_whose_turn_was_last() {
        // Act
        var result = game.getPlayers().get(0).getPrevPlayer(game);

        // Assert
        assertEquals(game.getPlayers().get(3).getName(), result.getName());
    }

    @Test
    void getNextPlayer_should_return_player_whose_turn_is_next() {
        // Act
        var result = game.getPlayers().get(0).getNextPlayer(game);

        // Assert
        assertEquals(game.getPlayers().get(1), result);
    }

    @Test
    void getNextNextPlayer_should_return_player_whose_turn_is_two_from_current() {
        // Act
        var result = game.getPlayers().get(0).getNextNextPlayer(game);

        // Assert
        assertEquals(game.getPlayers().get(2), result);
    }
}