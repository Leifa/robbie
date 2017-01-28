package de.leifaktor.robbie.data.tiles;

public class Forest extends Tile {
    
    int forestType;

    public Forest(int id, int forestType) {
        super(id);
        this.forestType = forestType;
    }

}
