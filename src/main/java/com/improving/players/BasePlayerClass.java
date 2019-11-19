package com.improving.players;

import com.improving.Card;
import com.improving.Colors;
import com.improving.IGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;

public abstract class BasePlayerClass implements IPlayer {
    private String name;
    protected LinkedList<Card> hand;

    public BasePlayerClass(String name, IGame game) {
        this.name = name;
        initializeHand(game,7);
    }

    private void initializeHand (IGame game, int size) {
        hand = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            this.hand.add(draw(game));
        }
    }

    @Override
    public Card takeTurn(IGame game) {
        var card = pickCard(game);

        // PickCard returns null if no card was playable
        if (card != null) {
            playCard(game, card);
            System.out.println(name + " has played " + card + " and finished turn.");
            return card;
        }

        card = game.getDeck().draw();
        hand.add(card);
        System.out.println(name + " has drawn a " + card + " and finished turn.");
        return null;
    }

    protected Card pickCard(IGame game) {
        for (var card : hand) {
            if (game.isPlayable(card)) {
                return card;
            }
        }
        return null;
    }

    private void playCard(IGame game, Card card) {
        if (card.getColors() == null) game.playCard(card, Optional.of(chooseColor()));
            // TODO: make color parameter optional.
        else game.playCard(card, null);
        hand.remove(card);
    }

    public Colors chooseColor() {
        // TODO: Prompt user to choose color. Hint: Choose different picking strategies for bots.
        var random = new Random().nextInt(4);
        return Colors.values()[random];
    }

    @Override
    public int handSize() {
        return hand.size();
    }

    @Override
    public Card draw(IGame game) {
        return game.draw();
    }

    @Override
    public LinkedList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int countCardsByColor(Colors color) {
        var cards = new ArrayList<Card>();
        for (var card : hand) if (card.getColors().equals(color)) cards.add(card);
        return cards.size();
    }

    @Override
    public int getPlayerHandSize() {
        return 0;
    }

    private IPlayer getPlayer(IGame game, int turnIndex) {
        if (turnIndex <= 0) turnIndex = turnIndex + game.getPlayers().size();
        int nextPlayer = turnIndex % game.getPlayers().size();
        return game.getPlayers().get(nextPlayer);
    }

    @Override
    public IPlayer getPrevPlayer(IGame game) {
        var i = game.getTurnIndex() - game.getTurnDirection();
        return getPlayer(game, i);
    }

    @Override
    public IPlayer getNextPlayer(IGame game) {
        return getPlayer(game, game.getTurnIndex() + game.getTurnDirection());
    }

    @Override
    public IPlayer getNextNextPlayer(IGame game) {
        return getPlayer(game, game.getTurnIndex() + game.getTurnDirection() * 2);
    }
}
