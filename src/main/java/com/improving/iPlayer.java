package com.improving;

import java.util.LinkedList;

public interface iPlayer {

    int handSize();
    Card draw(iGame game);
    Card takeTurn(iGame game);

    LinkedList<Card> getHand();
    String getName();
}
