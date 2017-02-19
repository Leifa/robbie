package de.leifaktor.robbie.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.leifaktor.robbie.data.Room;
import de.leifaktor.robbie.data.RoomLayer;
import de.leifaktor.robbie.gfx.Tileset;

public class GameRoomRenderer {

    private RuntimeRoom runtimeRoom;
    private int maxRenderLayer;

    public GameRoomRenderer(RuntimeRoom room) {
        this.runtimeRoom = room;
        maxRenderLayer = room.layers.length - 1;
    }

    public void render(SpriteBatch batch) {
        TextureRegion t = null;
        batch.setColor(1, 1, 1, 1);
        for (int k = 0; k < runtimeRoom.layers.length; k++) {
            if (k > maxRenderLayer) batch.setColor(1, 1, 1, 0.3f);
            RuntimeLayer runtimeLayer = runtimeRoom.layers[k];
            // Tiles
            for (int i = 0; i < runtimeLayer.layer.getWidth(); i++) {
                for (int j = 0; j < runtimeLayer.layer.getHeight(); j++) {
                    t = Tileset.getTexture(runtimeLayer.layer.getTiles()[j*runtimeLayer.layer.getWidth() + i]);
                    if (t != null) {
                        batch.draw(t, i, j, 1, 1);
                    }
                }
            }  
            // ENTITIES
            for (RuntimeEntity e: runtimeRoom.layers[k].entities) {
                t = Tileset.getTexture(e.entity);
                if (t != null) batch.draw(t, e.x, e.y, 1, 1);
            }
            // PLAYER
            t = Tileset.getPlayerTexture();
            if (t != null && runtimeLayer.player != null) batch.draw(t, runtimeLayer.player.x, runtimeLayer.player.y, 1, 1);
        }        
    }    
    
    public void setRoom(RuntimeRoom room) {
        this.runtimeRoom = room;
    }

    public void setMaxRenderLayer(int maxRenderLayer) {
        this.maxRenderLayer = maxRenderLayer;
    }
    
}
