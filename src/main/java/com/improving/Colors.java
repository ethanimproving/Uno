package com.improving;

public enum Colors {
    Red(1),
    Green(1),
    Yellow(1),
    Blue(1),
    Wild(0);

    int number;

    Colors(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
