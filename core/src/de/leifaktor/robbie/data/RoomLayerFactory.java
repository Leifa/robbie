package de.leifaktor.robbie.data;

import de.leifaktor.robbie.data.tiles.Tile;

public class RoomLayerFactory {

    public static RoomLayer emptyLayer(int width, int height) {
        RoomLayer l = new RoomLayer(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                l.tiles[i*height+j] = Tile.get(0);
            }
        }
        return l;
    }
    
    public static RoomLayer transparentLayer(int width, int height) {
        RoomLayer l = new RoomLayer(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                l.tiles[i*height+j] = Tile.get(1);
            }
        }
        return l;
    }

    
}
