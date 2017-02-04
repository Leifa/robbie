package de.leifaktor.robbie.screens;

import de.leifaktor.robbie.data.Episode;
import de.leifaktor.robbie.data.Room;
import de.leifaktor.robbie.data.RoomLayer;

public class EditorSelectionData {

    public Episode episode;
    public int floor;
    public Room room;
    public RoomLayer layer;
    
    // for EditorFloorsScreen
    int floorSelector;
    
    // for EditorFloorScreen
    int roomSelectorX;
    int roomSelectorY;    
    int roomRectHeight;
    int roomRectWidth;
    
    Area area;
    
}
