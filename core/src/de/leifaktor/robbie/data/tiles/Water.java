package de.leifaktor.robbie.data.tiles;

public class Water extends Tile {
    
    int type;
    
    public Water(int id, int type) {
        super(id);
        this.type = type;
    }
    
    public int getType() {
        return type;
    }

}
