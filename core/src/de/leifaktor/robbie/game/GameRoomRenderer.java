package de.leifaktor.robbie.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.leifaktor.robbie.data.Room;
import de.leifaktor.robbie.data.RoomLayer;
import de.leifaktor.robbie.gfx.Tileset;

public class GameRoomRenderer {

    private Room room;
    int layerIndex; 

    public GameRoomRenderer(Room room) {
        this.room = room;
    }

    public void render(SpriteBatch batch) {
        TextureRegion t = null;
        for (int k = 0; k <= layerIndex; k++) {
            RoomLayer l = room.getLayers().get(k);
            // Tiles
            for (int i = 0; i < l.getWidth(); i++) {
                for (int j = 0; j < l.getHeight(); j++) {
                    t = Tileset.getTexture(l.getTiles()[j*l.getWidth() + i]);
                    if (t != null) {
                        batch.draw(t,
                                i,
                                j,
                                1,
                                1);   
                    }
                }
            }           
        }
    }    
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public void setLayer(int layer) {
        this.layerIndex = layer;
    }
    
}
