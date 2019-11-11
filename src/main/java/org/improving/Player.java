package org.improving;

public class Player {
    private Hand hand;

    public Player(Deck deck) {
        this.hand = new Hand(deck, 7);
    }

    public Card takeTurn(Deck deck) {
        var topCard = hand.getHand().get(0);
        return hand.play(deck, topCard);
    }

    public boolean isPlayable(Deck deck, Card card) {
        var deckTopCard = deck.getDiscard().get(0);

        if (deckTopCard.getColor() == card.getColor() ||
            deckTopCard.getFace() == card.getFace() ||
            card.getFace().getValue() == 50) {
            return true;
        };
        return false;
    }

    public Hand getHand() {
        return hand;
    }
}
