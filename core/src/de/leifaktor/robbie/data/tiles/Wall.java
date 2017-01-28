package de.leifaktor.robbie.data.tiles;

public class Wall extends Tile {
    
    int walltype;
    
    public Wall(int id, int walltype) {
        super(id);
        this.walltype = walltype;
    }
    
    public int getWalltype() {
        return walltype;
    }
    
}
