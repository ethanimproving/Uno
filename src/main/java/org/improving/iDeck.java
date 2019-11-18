package org.improving;

import java.util.LinkedList;

public interface iDeck {
    Card draw();
    LinkedList<Card> getDiscard();
}