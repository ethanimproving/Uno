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
        int i = 0;
        while (true) {

            // Skip
            if(1==2){
                continue;
            }

            players.get(i).takeTurn(this);
            turns++;
            i = turnEngine(i);
            if (players.get(i).getHand().size() <= 0) {
                System.out.println("\n" + players.get(i).getName() + " has won the game! It lasted " + turns + " turns.");
                return;
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

    public int turnEngine(int i) {
        if (i == players.size()-1) i = 0;
        else i++;
        return i;
    }

    public int turnDirection(int index) {
        throw new RuntimeException();
    }

    public int convertNegativeIndex(int index) {
        throw new RuntimeException();
    }

    public List<iPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(String name) {
        this.players.add(new Player(name, deck));
    }
}
