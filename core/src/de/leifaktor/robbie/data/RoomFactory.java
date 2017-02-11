package de.leifaktor.robbie.data;

public class RoomFactory {

    public static Room emptyRoom(int width, int height) {
        Room r = new Room(width, height);
        RoomLayer l = RoomLayerFactory.emptyLayer(width, height);
        r.layers.add(l);
        return r;
    }
    
}
