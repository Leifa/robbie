package de.leifaktor.robbie.data;

import de.leifaktor.robbie.data.tiles.Tile;

public class RoomLayerFactory {

    public static RoomLayer emptyLayer(int width, int height) {
        RoomLayer l = new RoomLayer(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                l.tiles[i*height+j] = Tile.empty;
            }
        }
        return l;
    }
    
    public static RoomLayer transparentLayer(int width, int height) {
        RoomLayer l = new RoomLayer(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                l.tiles[i*height+j] = Tile.transparent;
            }
        }
        return l;
    }
    
    public static RoomLayer wallLayer(int width, int height) {
        RoomLayer l = new RoomLayer(width, height);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    l.tiles[j*width+i] = Tile.dark_wall;
                } else {
                    l.tiles[j*width+i] = Tile.empty;
                }
            }
        }
        return l;
    }
    
    public static RoomLayer sandLayer(int width, int height) {
        RoomLayer l = new RoomLayer(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                l.tiles[i*height+j] = Tile.sand;
            }
        }
        return l;
    }
    

    
}
