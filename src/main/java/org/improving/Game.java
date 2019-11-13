package org.improving;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Deck deck;
    private List<Player> players;

    public static void main(String[] args) {
        var game = new Game();
        game.initializeDiscardPile();
        game.startGame();
        System.out.println("Count: " + game.getDeck());
    }

    public Game() {
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.players.add(new Player(deck));
    }

    public void initializeDiscardPile() {
        deck.getDiscard().add(deck.draw());
    }

    public void startGame() {
        var player = this.players.get(0);
        int turns = 0;
        while (player.getHand().size() > 0) {
            player.takeTurn(deck);
            turns++;
        }
        System.out.println(player.getName() + " took " + turns + " turns to win the game.");
    }

    public List<Card> getDeck() {
        return deck.getDeck();
    }

    public static boolean isPlayable(Deck deck, Card card) {
        var deckTopCard = deck.getDiscard().getLast();

        if (deckTopCard.getColor() == card.getColor() ||
                deckTopCard.getFace() == card.getFace() ||
                card.getFace().getValue() == 50) {
            return true;
        };
        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
