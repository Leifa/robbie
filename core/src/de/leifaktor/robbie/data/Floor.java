package de.leifaktor.robbie.data;

public class Floor {

    /**
     * Die Referenzen auf die Räume. Sie stehen zeilenweise von links nach rechts
     * und von oben nach unten geordnet in dem Array.
     */

    private Room[] rooms;
    
    /**
     * Die Breite des Floors
     */
    
    private int width;
    
    /**
     * Die Höhe des Floors
     */
    
    private int height;
    
    public Floor() {}
    
    /**
     * Erzeugt einen Floor
     * @param width Anzahl der Räume in der Breite
     * @param height Anzahl der Räume in der Höhe
     */
    
    public Floor(int width, int height) {
        this.height = height;
        this.width = width;
        rooms = new Room[width*height];
    }
    
    public void setRoom(int x, int y, Room room) {
        if (0 <= x && x < width && 0 <= y && y < height) {
            rooms[y*width + x] = room;
        }
    }
    
    /**
     * Expands the floor by one row to the south.
     */
    
    public void expandSouth() {
        Room[] newrooms = new Room[width*(height+1)];
        for (int i = 0; i < rooms.length; i++) {
            newrooms[i] = rooms[i];
        }
        rooms = newrooms;
        height++;
    }
    
    /**
     * Expands the floor by one row to the north
     */
    
    public void expandNorth() {
        Room[] newrooms = new Room[width*(height+1)];
        for (int i = 0; i < rooms.length; i++) {
            newrooms[i+width] = rooms[i];
        }
        rooms = newrooms;
        height++;
    }
    
    /**
     * Expands the floor by one column to the west
     */
    
    public void expandWest() {
        Room[] newrooms = new Room[(width+1)*height];
        for (int i = 0; i < rooms.length; i++) {
            newrooms[(width+1)*(i/width) + i%width + 1] = rooms[i];
        }
        rooms = newrooms;
        width++;
    }
    
    /**
     * Expands the floor by one column to the east
     */
    
    public void expandEast() {
        Room[] newrooms = new Room[(width+1)*height];
        for (int i = 0; i < rooms.length; i++) {
            newrooms[(width+1)*(i/width) + i%width] = rooms[i];
        }
        rooms = newrooms;
        width++;
    }
    
    /**
     * Returns the width of the floor.
     * @return the width of the floor.
     */
    
    public int getWidth() {
        return width;
    }
    
    /**
     * Returns the height of the floor.
     * @return the height of the floor.
     */
    
    public int getHeight() {
        return height;
    }
    
    /**
     * Returns the Room at the specified x/y-position.
     * @param x
     * @param y
     * @return
     */

    public Room getRoom(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return null;
        return rooms[x+y*getWidth()];
    }
    
}