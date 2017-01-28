package de.leifaktor.robbie.data;

import java.util.ArrayList;

public class Episode {
    
    String name;
    int roomwidth;
    int roomheight;
    ArrayList<Floor> floors;
    Position startingPosition;
    
    public Episode() {
        this.name = "untitled";
        this.roomwidth = 40;
        this.roomheight = 28;
        this.floors = new ArrayList<Floor>();
    }
    
    public Episode(int width, int height) {
        this.roomheight = height;
        this.roomwidth = width;
        this.name = "untitled";
        this.floors = new ArrayList<Floor>();
    }

    public Floor addFloor() {
        Floor floor = new Floor(1,1);
        floors.add(floor);
        return floor;
    }
    
    public String getName() {
        return name;
    }
    
    public int getRoomWidth() {
        return roomwidth;
    }
    
    public int getRoomHeight() {
        return roomheight;
    }
    
    public ArrayList<Floor> getFloors() {
        return floors;
    }
    
    public Position getStartingPosition() {
        return startingPosition;
    }

}
