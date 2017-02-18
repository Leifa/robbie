package de.leifaktor.robbie.data;

import java.util.ArrayList;

import de.leifaktor.robbie.data.entities.Entity;
import de.leifaktor.robbie.data.tiles.Tile;

public class RoomLayer {

    int width;
    int height;

    Tile[] tiles;
    ArrayList<Entity> entities;
    
    public RoomLayer() {}
    
    public RoomLayer(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width*height];
        this.entities = new ArrayList<Entity>();
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setTile(int x, int y, Tile tile) {
        tiles[width*y + x] = tile;        
    }
    
    public ArrayList<Entity> getEntitiesAt(int x, int y) {
        ArrayList<Entity> list = new ArrayList<Entity>();
        for (Entity e: entities) {
            if (e.getX() == x && e.getY() == y) {
                list.add(e);
            }
        }
        return list;
    }

    public void clearEntitiesAt(int x, int y) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getX() == x && entities.get(i).getY() == y) {
                entities.remove(i);
                i--;
            }
        }
    }

    public Tile getTile(int x, int y) {        
        return tiles[width*y + x];
    }
    
    
}
