package de.leifaktor.robbie.data;

import java.util.ArrayList;

public class Room {
    
    int width;
    int height;
    ArrayList<RoomLayer> layers;

    public Room() {}
    
    public Room(int width, int height) {
        this.width = width;
        this.height = height;
        this.layers = new ArrayList<RoomLayer>();
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public ArrayList<RoomLayer> getLayers() {
        return layers;
    }

}
