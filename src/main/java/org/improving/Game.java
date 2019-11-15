package org.improving;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Game {
    private Deck deck;
    private List<iPlayer> players;
    private int turnDirection = 1;

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
        int turnIndex = 0;
        int currentPlayer;
        while (true) {

            currentPlayer = turnEngine(turnIndex);
            System.out.println("\nCurrent Player (" + turnIndex + " % " + players.size() + ") = " + currentPlayer);
            players.get(currentPlayer).takeTurn(this);
            // if player played
            // check card rules in turn engine

            var cardPlayed = deck.getDiscard().getLast();
            turns++;

            if (players.get(currentPlayer).getHand().size() <= 0) {
                System.out.println("\n" + players.get(currentPlayer).getName() + " has won the game! It lasted " + turns + " " +
                        "turns.");
                return;
            }
            checkTurnDirection(cardPlayed);
            turnIndex = turnIndex + turnDirection;
            System.out.println("Turn Index = " + turnIndex);
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

        return deckTopCard.getColor() == card.getColor() ||
                deckTopCard.getFace() == card.getFace() ||
                card.getFace().getValue() == 50;
    }

    public void cardRules(Player player, Card card) {
        if (card.getFace() == Face.Draw2) {
            player.getHand().add(this.deck.draw());
            player.getHand().add(this.deck.draw());
            // TODO: skip turn
        }
    }

    public int turnEngine(int currentPlayer) {

        if(currentPlayer<=0) currentPlayer = currentPlayer + players.size();
        currentPlayer = currentPlayer % (players.size());

        // -3 % 3 = 0
        // -2 % 3 = -2
        // -1 % 3 = -1
        // 0 % 3 = 0
        // 1 % 3 = 1
        // 2 % 3 = 2
        // 3 % 3 = 0

        //do this after every turn
        //turn direction will go back and forth
        return currentPlayer;

    }

    public void checkTurnDirection(Card card) {
        if (card.getFace() == Face.Reverse) {
            System.out.println("Turn direction REVERSED >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            turnDirection = -1;
        } else turnDirection = 1;
    }

    public int convertNegativeIndex(int index) {
        // add to modulo
        throw new RuntimeException();
    }

    public boolean isSpecialCard(Card card) {
        return card.getFace().getValue() == 20 ||
                card.getFace().equals(Face.WildDrawFour);
    }

    public void excuteSpecialCard() {

    }

    public List<iPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(String name) {
        this.players.add(new Player(name, deck));
    }
}
