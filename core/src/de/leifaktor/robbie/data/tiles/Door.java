package de.leifaktor.robbie.data.tiles;

public class Door extends Tile {
    
    int doorNumber;

    public Door(int id, int doorNumber) {
        super(id);
        this.doorNumber = doorNumber;
    }

}
