package com.improving;

public class Card {
    private Colors colors;
    private final Faces faces;

    public Card(Colors colors, Faces faces) {
        this.colors = colors;
        this.faces = faces;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    public Colors getColors() {
        return colors;
    }

    public Faces getFaces() {
        return faces;
    }

    @Override
    public String toString() {
        return (colors == Colors.Wild ? "" : colors.toString())+ faces.toString();
    }
}
