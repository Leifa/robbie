package de.leifaktor.robbie.data.tiles;

public class DarkWall extends Tile {
    
    int walltype;

    public DarkWall(int walltype) {
        this.walltype = walltype;
    }
    
    public int getWalltype() {
        return walltype;
    }

}
