package org.improving;

public class Card {
    private Color color;
    private final Face face;

    public Card(Color color, Face face) {
        this.color = color;
        this.face = face;
    }

    public void setColor(Color color) {
        this.color = color;
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
