package de.leifaktor.robbie.data;

public class Position {

    public XYZPos roomPosition; // The position of the room in the world
    public int layer, x, y; // The position inside the room
    
    public Position() {} // no-arg constructor for JSON
    
    public Position(XYZPos roomPosition, int layer, int x, int y) {
        this.roomPosition = roomPosition;
        this.layer = layer;
        this.x = x;
        this.y = y;
    }

    
}
