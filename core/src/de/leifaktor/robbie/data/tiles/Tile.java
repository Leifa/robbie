package de.leifaktor.robbie.data.tiles;

import de.leifaktor.robbie.data.entities.Entity;

public abstract class Tile {
    
    public static Tile[] tiles = new Tile[256];   
    
    protected int id;
    protected String description;
    
    public static void registerTile(Tile tile) {
        if (tiles[tile.id] != null) throw new RuntimeException("Tile id already in use: " + tile.id);
        tiles[tile.id] = tile;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public int getID() {
        return id;
    }
    
    public static Tile get(int id) {
        return tiles[id];
    }
    
    public String toString() {
        return "[" + this.id + "] " + description;
    }

    public void setDescription(String description) {
        this.description = description;        
    }
    
    public boolean isWalkable(Entity e) {
        return true;
    };

}
