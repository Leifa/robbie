package de.leifaktor.robbie.editor;

import de.leifaktor.robbie.data.Episode;
import de.leifaktor.robbie.data.Room;

public class EditorData {

    // ModelData
    public Episode episode;
    public int floor;
    public Room room;
    public int layer;
   
    // for EditorFloorScreen
    int roomSelectorX;
    int roomSelectorY;    
    int roomRectHeight;
    int roomRectWidth;
    
    // Clipboard
    Area area;
    
    // Reference to the AutoBackup
    AutoBackup autoBackup;

    public void reset() {
       episode = null;
       floor = 0;
       room = null;
       layer = 0;
       roomSelectorX = 0;
       roomSelectorY = 0;
    }
    
}
