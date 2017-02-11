package de.leifaktor.robbie.data.tiles;

public class Door extends Tile {
    
    int doorNumber;
    
    public Door() {}

    public Door(int doorNumber) {     
        this.doorNumber = doorNumber;
    }
    
    public int getDoorNumber() {
        return doorNumber;
    }

}
