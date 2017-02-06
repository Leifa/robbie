package de.leifaktor.robbie.data;

public class Episode {
    
    String name;
    int roomwidth;
    int roomheight;
    RoomManager rooms;
    Position startingPosition;
    
    public Episode() {
        this.name = "untitled";
        this.roomwidth = 40;
        this.roomheight = 28;
        this.rooms = new RoomManager();
    }
    
    public Episode(int width, int height) {
        this.roomheight = height;
        this.roomwidth = width;
        this.name = "untitled";
        this.rooms = new RoomManager();
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
    
    public RoomManager getRooms() {
        return rooms;
    }
    
    public void setStartingPosition(Position startingPosition) {
        this.startingPosition = startingPosition;
    }
    
    public Position getStartingPosition() {
        return startingPosition;
    }

}
