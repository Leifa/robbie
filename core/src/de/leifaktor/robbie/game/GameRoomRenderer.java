package de.leifaktor.robbie.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.leifaktor.robbie.data.Room;
import de.leifaktor.robbie.data.RoomLayer;
import de.leifaktor.robbie.gfx.Tileset;

public class GameRoomRenderer {

    private RuntimeRoom room;

    public GameRoomRenderer(RuntimeRoom room) {
        this.room = room;
    }

    public void render(SpriteBatch batch) {
        TextureRegion t = null;
        for (int k = 0; k < room.layers.length; k++) {
            RuntimeLayer l = room.layers[k];
            // Tiles
            for (int i = 0; i < l.layer.getWidth(); i++) {
                for (int j = 0; j < l.layer.getHeight(); j++) {
                    t = Tileset.getTexture(l.layer.getTiles()[j*l.layer.getWidth() + i]);
                    if (t != null) {
                        batch.draw(t, i, j, 1, 1);
                    }
                }
            }  
            // ENTITIES
            for (RuntimeEntity e: room.layers[k].entities) {
                t = Tileset.getTexture(e.entity);
                if (t != null) batch.draw(t, e.x, e.y, 1, 1);
            }
            // PLAYER
            t = Tileset.getPlayerTexture();
            if (t != null && room.player != null) batch.draw(t, room.player.x, room.player.y, 1, 1);
        }        
    }    
    
    public void setRoom(RuntimeRoom room) {
        this.room = room;
    }
    
}
