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
}