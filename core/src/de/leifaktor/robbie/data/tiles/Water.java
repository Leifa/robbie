package de.leifaktor.robbie.data.tiles;

public class Water extends Tile {
    
    int type;
    
    public Water() {}
    
    public Water(int type) {
        this.type = type;
    }
    
    public int getType() {
        return type;
    }

}
