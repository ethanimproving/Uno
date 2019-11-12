package org.improving;

public class Card {
    private final Color color;
    private final Face face;

    public Card(Color color, Face face) {
        this.color = color;
        this.face = face;
    }

    public Color getColor() {
        return color;
    }

    public Face getFace() {
        return face;
    }

    @Override
    public String toString() {
        return (color == null ? "" : color.toString())+face.toString();
    }
}
