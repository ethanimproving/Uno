package com.improving;

import java.util.LinkedList;

public interface IDeck {
    Card draw();
    LinkedList<Card> getDiscard();
}