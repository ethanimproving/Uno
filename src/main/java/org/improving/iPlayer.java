package org.improving;

import java.util.LinkedList;

public interface iPlayer {

    int handSize();
    Card draw(Game game);
    Card takeTurn(Game game);

    LinkedList<Card> getHand();

    String getName();
}
