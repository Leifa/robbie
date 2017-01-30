package de.leifaktor.robbie.data;

public class Position {

    public RoomLayer layer;
    public int x;
    public int y;
    
    private Position() {} // no-arg constructor for json
    
    public Position(RoomLayer layer, int x, int y) {
        this.layer = layer;
        this.x = x;
        this.y = y;
    }
}
