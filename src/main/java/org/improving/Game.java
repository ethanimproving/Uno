package org.improving;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Game {
    private Deck deck;
    private List<Player> players;

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(SpringContext.class);
        var game = context.getBean(Game.class);
        game.startGame();
        System.out.println("Count: " + game.getDeckPile());
    }

    public Game(Deck deck) {
        this.deck = deck;
        this.players = new ArrayList<>();
        this.players.addAll(Arrays.asList(
                new Player("David O\'Hera", this.deck),
                new Player("Tim Rayburn", this.deck)
        ));
        initializeDiscardPile();
    }

    public void initializeDiscardPile() {
        deck.getDiscard().add(deck.draw());
    }

    public void startGame() {
        int turns = 0;
        while (true) {
            for (var player : players) {

                // Take turn.
                player.takeTurn(deck);
                turns++;

                // Check if player has won.
                if (player.getHand().size() <= 0) {
                    System.out.println("\n" + player.getName() + " has won the game! It lasted " + turns + " turns.");
                    return;
                }
            }
        }
    }

    public List<Card> getDeckPile() {
        return deck.getDeck();
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Card> getDiscard() {
        return deck.getDiscard();
    }

    public static boolean isPlayable(Deck deck, Card card) {
        var deckTopCard = deck.getDiscard().getLast();
        // TODO: Ethan has drawn a null and finished turn -> NullPointerException
        if (deckTopCard.getFace() == Face.Draw2) {
            if (card.getFace() == Face.Draw2) return true;
            return false;
        }

        return deckTopCard.getColor() == card.getColor() ||
                deckTopCard.getFace() == card.getFace() ||
                card.getFace().getValue() == 50;
    }

    public void cardBehavior(Player player, Card card) {
        if (card.getFace() == Face.Draw2) {
            player.getHand().add(this.deck.draw());
            player.getHand().add(this.deck.draw());
            // TODO: skip turn
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(String name) {
        this.players.add(new Player(name, deck));
    }
}
