package com.improving;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Game implements IGame {
    private Deck deck;
    private List<IPlayer> players;
    private int turnDirection = 1;
    private int turnIndex = 0;

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(SpringContext.class);
        var game = context.getBean(Game.class);

        game.playGame();
    }

    public Game() {
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.players.addAll(Arrays.asList(
                new Player("David O\'Hera", this),
                new Player("Tim Rayburn", this),
                new Player("Ethan Miller", this)
        ));
    }

    public void initializeDiscardPile() {
        deck.getDiscard().add(deck.draw());
        turnIndex--; // Decrement turn index to offset nextPlayer in executeSpecialCard to player 1
        executeSpecialCard(deck.getDiscard().getLast());
        turnIndex++; // Reset turns back to player 2
    }

    public void playGame() {
        int turns = 0;
        int currentPlayer;
        initializeDiscardPile();

        while (true) {
            // Determine current player.
            currentPlayer = turnEngine();

            // Take turn and if card was played, check card behavior.
            var cardPlayed = players.get(currentPlayer).takeTurn(this);
            if (cardPlayed != null) executeSpecialCard(cardPlayed);

            // Increment turn count to keep track of turns played.
            turns++;

            // Check if player has won.
            if (players.get(currentPlayer).handSize() <= 0) {
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

    public int turnEngine() {
        if (this.turnIndex <= 0) this.turnIndex = this.turnIndex + players.size();
        return this.turnIndex % players.size();
    }

    @Override
    public boolean isPlayable(Card card) {
        return deck.getDiscard().getLast().getColors().equals(card.getColors()) ||
                deck.getDiscard().getLast().getFaces().equals(card.getFaces()) ||
                card.getFaces().getValue() == 50;
    }

    public void executeSpecialCard(Card card) {
        var nextPlayer = getPlayers().get(0).getNextPlayer(this);

        switch (card.getFaces()) {
            case DrawTwo:
                nextPlayer.getHand().add(draw());
                nextPlayer.getHand().add(draw());
                System.out.println(nextPlayer.getName() + " has drawn two cards.");
                incrementTurn();
                break;
            case WildDrawFour:
                nextPlayer.getHand().add(draw());
                nextPlayer.getHand().add(draw());
                nextPlayer.getHand().add(draw());
                nextPlayer.getHand().add(draw());
                System.out.println(nextPlayer.getName() + " has drawn FOUR cards. Sorry bud!");
                incrementTurn();
                break;
            case Reverse:
                turnDirection = turnDirection * -1;
                System.out.println("Player direction has been REVERSED.");
                break;
            case Skip:
                System.out.println(nextPlayer.getName() + " has been SKIPPED.");
                incrementTurn();
                break;
        }
    }

    @Override
    public Card draw() {
        return deck.draw();
    }

    @Override
    public void playCard(Card card, Colors colors) {
        if (card.getColors() == null) card.setColors(colors);
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

    public List<IPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(String name) {
        this.players.add(new Player(name, this));
    }

    public int getTurnDirection() {
        return turnDirection;
    }

    public int getTurnIndex() {
        return turnIndex;
    }
}
