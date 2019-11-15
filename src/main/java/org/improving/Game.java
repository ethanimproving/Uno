package org.improving;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        System.out.println("Count: " + game.getDeckPile());
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

            currentPlayer = turnEngine(turnIndex);
            System.out.println("\nCurrent Player (" + turnIndex + " % " + players.size() + ") = " + currentPlayer);
            // TODO: index -1 out of bounds

            var cardPlayed = players.get(currentPlayer).takeTurn(this);
            if (cardPlayed != null) excuteSpecialCard(cardPlayed);

            turns++;

            if (players.get(currentPlayer).getHand().size() <= 0) {
                System.out.println("\n" + players.get(currentPlayer).getName() + " has won the game! It lasted " + turns + " " +
                        "turns.");
                return;
            }
            incrementTurn();
            System.out.println("Turn Index = " + turnIndex);
        }
    }

    private void incrementTurn() {
        turnIndex = turnIndex + turnDirection;
    }

    public static boolean isPlayable(Deck deck, Card card) {
        var deckTopCard = deck.getDiscard().getLast();
        // TODO: Ethan has drawn a null and finished turn -> NullPointerException

        return deckTopCard.getColor() == card.getColor() ||
                deckTopCard.getFace() == card.getFace() ||
                card.getFace().getValue() == 50;
    }

    public int turnEngine(int turnIndex) {

        if(turnIndex<=0) this.turnIndex = turnIndex + players.size();
        turnIndex = turnIndex % (players.size());

        return turnIndex;

    }

    public void excuteSpecialCard(Card card) {
        int nextTurnIndex = turnIndex + turnDirection;
        int nextPlayer = turnEngine(nextTurnIndex);

        if(card.getFace() == Face.DrawTwo) {
            players.get(nextPlayer).draw(this);
            players.get(nextPlayer).draw(this);
            System.out.println(players.get(nextPlayer).getName() + " has drawn two cards.");
            incrementTurn();
        } else if(card.getFace() == Face.WildDrawFour) {
            players.get(nextPlayer).draw(this);
            players.get(nextPlayer).draw(this);
            players.get(nextPlayer).draw(this);
            players.get(nextPlayer).draw(this);
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

    public List<Card> getDiscard() {
        return deck.getDiscard();
    }

    public List<iPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(String name) {
        this.players.add(new Player(name, deck));
    }
}
