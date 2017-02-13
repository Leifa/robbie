package de.leifaktor.robbie.editor;

import java.util.ArrayList;
import java.util.List;

import de.leifaktor.robbie.data.RoomLayer;
import de.leifaktor.robbie.data.entities.Entity;
import de.leifaktor.robbie.data.tiles.Tile;

public class Area {
    
    private int width;
    private int height;
    private Tile[] tiles;
    private List<Entity> entities;

    public Area(RoomLayer layer, int x1, int y1, int x2, int y2) {
        // make x1/y1 the upper left corner
        if (x1 > x2) {
            int t = x2;
            x2 = x1;
            x1 = t;
        }
        if (y1 > y2) {
            int t = y2;
            y2 = y1;
            y1 = t;
        }
        width = x2 - x1 + 1;
        height = y2 - y1 + 1;
        tiles = new Tile[width*height];
        entities = new ArrayList<Entity>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[width*j + i] = layer.getTiles()[layer.getWidth()*(y1+j) + x1+i];
            }
        }
        for (Entity e: layer.getEntities()) {
            if (e.getX() >= x1 && e.getX() <= x2 && e.getY() >= y1 && e.getY() <= y2) {
                Entity copy = e.clone();
                copy.setPosition(e.getX() - x1, e.getY() - y1);
                entities.add(copy);
            }
        }
    }
    
    public void paste(RoomLayer layer, int x, int y) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i+x < layer.getWidth() && j+y < layer.getHeight()) {
                    layer.setTile(x+i, y+j, tiles[j*width+i]);
                    layer.clearEntitiesAt(x+i, y+j);                    
                }
            }
        }
        for (Entity e: entities) {
            int i = e.getX() + x;
            int j = e.getY() + y;
            if (i < layer.getWidth() && j < layer.getHeight()) {
                Entity copy = e.clone();
                copy.setPosition(i, j);
                layer.getEntities().add(copy);
            }
        }        
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
}
