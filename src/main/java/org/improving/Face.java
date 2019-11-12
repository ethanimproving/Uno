package org.improving;

public enum Face {
    Zero(0),
    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Draw2(20),
    Reverse(20),
    Skip(20),
    Wild(50),
    WildDrawFour(50);

    private int value;

    Face(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
