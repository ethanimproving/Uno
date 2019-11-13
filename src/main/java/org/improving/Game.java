package org.improving;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Game {
    private Deck deck;
    private List<Player> players;

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(SpringContext.class);
        var game = context.getBean(Game.class);
        game.startGame();
        System.out.println("Count: " + game.getDeck());
    }

    public Game(Deck deck) {
        this.deck = deck;
        this.players = new ArrayList<>();
        this.players.add(new Player(deck));
        initializeDiscardPile();
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
        // TODO: Ethan has drawn a null and finished turn -> NullPointerException
        if (deckTopCard.getColor() == card.getColor() ||
                deckTopCard.getFace() == card.getFace() ||
                card.getFace().getValue() == 50) {
            return true;
        };
        return false;
    }

    public void cardBehavior(Player player, Card card) {
        if (card.getFace() == Face.Draw2) {
            player.getHand().add(this.deck.draw());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
