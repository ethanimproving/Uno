package org.improving;

import java.util.BitSet;
import java.util.LinkedList;

public interface iPlayer {

    int handSize();
    Card draw();
    void takeTurn(Game game);

    LinkedList<Card> getHand();

    String getName();
}
