package org.improving;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Game {
    private Deck deck;
    private List<iPlayer> players;
    private int turnDirection = 1;
    private int turnIndex = 0;

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(SpringContext.class);
        var game = context.getBean(Game.class);
        game.startGame();
    }

    public Game() {
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.players.addAll(Arrays.asList(
                new Player("David O\'Hera", this.deck),
                new Player("Tim Rayburn", this.deck),
                new Player("Ethan Miller", this.deck)
        ));
        initializeDiscardPile();
    }

    public void initializeDiscardPile() {
        deck.getDiscard().add(deck.draw());
    }

    public void startGame() {
        int turns = 0;
        int currentPlayer;

        while (true) {

            // Determine current player.
            currentPlayer = turnEngine(turnIndex);

            // Take turn and if card was played, check card behavior.
            var cardPlayed = players.get(currentPlayer).takeTurn(this);
            if (cardPlayed != null) excuteSpecialCard(cardPlayed);

            // Increment turn count to keep track of turns played.
            turns++;

            // Check if player has won.
            if (players.get(currentPlayer).getHand().size() <= 0) {
                System.out.println("\n" + players.get(currentPlayer).getName() + " has won the game! It lasted " + turns + " " +
                        "turns.");
                return;
            }

            // Move player index to the next player
            incrementTurn();

            // Print Player End Turn Information
            System.out.println("Hand: " + players.get(currentPlayer).getHand().toString() + "\n");
        }
    }

    private void incrementTurn() {
        turnIndex = turnIndex + turnDirection;
    }

    public static boolean isPlayable(Deck deck, Card card) {
        return deck.getDiscard().getLast().getColor().equals(card.getColor()) ||
                deck.getDiscard().getLast().getFace().equals(card.getFace()) ||
                card.getFace().getValue() == 50;
    }

    public int turnEngine(int turnIndex) {

        var playerIndex = turnIndex % (players.size());

        return Math.abs(playerIndex);

    }


    public void excuteSpecialCard(Card card) {
        int nextTurnIndex = turnIndex + turnDirection;
        int nextPlayer = turnEngine(nextTurnIndex);

        if(card.getFace() == Face.DrawTwo) {
            players.get(nextPlayer).getHand().add(draw());
            players.get(nextPlayer).getHand().add(draw());
            System.out.println(players.get(nextPlayer).getName() + " has drawn two cards.");
            incrementTurn();
        } else if(card.getFace() == Face.WildDrawFour) {
            players.get(nextPlayer).getHand().add(draw());
            players.get(nextPlayer).getHand().add(draw());
            players.get(nextPlayer).getHand().add(draw());
            players.get(nextPlayer).getHand().add(draw());
            System.out.println(players.get(nextPlayer).getName() + " has drawn FOUR cards. Sorry bud!");
            incrementTurn();
        } else if(card.getFace() == Face.Reverse) {
            turnDirection = turnDirection * -1;
            System.out.println("Player direction has been REVERSED.");
        } else if(card.getFace() == Face.Skip) {
            System.out.println(players.get(nextPlayer).getName() + " has been SKIPPED.");
            incrementTurn();
        }
    }

    public Card draw() {
        return deck.draw();
    }

    public void playCard(Card card, Color color) {
        if (card.getColor() == null) card.setColor(color);
        deck.getDiscard().add(card);
    }

    public List<Card> getDeckPile() {
        return deck.getDeck();
    }

    public Deck getDeck() {
        return deck;
    }

    public LinkedList<Card> getDiscard() {
        return deck.getDiscard();
    }

    public List<iPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(String name) {
        this.players.add(new Player(name, deck));
    }
}
