package de.leifaktor.robbie.data.tiles;

public class DarkWall extends Tile {
    
    int walltype;

    public DarkWall(int id, int walltype) {
        super(id);
        this.walltype = walltype;
    }
    
    public int getWalltype() {
        return walltype;
    }

}
