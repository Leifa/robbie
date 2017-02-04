package de.leifaktor.robbie.gfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.leifaktor.robbie.data.Room;
import de.leifaktor.robbie.data.RoomLayer;
import de.leifaktor.robbie.data.XYPos;
import de.leifaktor.robbie.data.entities.Entity;

public class RoomRenderer {
    
    private int tilesize;
    private Room room;
    int layerIndex;
    private float scale = 1.5f;
    private int x, y; // lower left corner
    
    private boolean grayLayers;
    
    public RoomRenderer(int tilesize) {
        this.tilesize = tilesize;
    }

    public void render(SpriteBatch batch) {
        TextureRegion t = null;
        for (int k = 0; k <= layerIndex; k++) {
            RoomLayer l = room.getLayers().get(k);
            // Render 
            // * all layers in normal color or
            // * only the current one normal and others gray
            if (grayLayers) {
                if (k == layerIndex) batch.setColor(1, 1, 1, 1);
                else batch.setColor(0.2f, 0.2f, 0.2f, 1f);
            } else {
                batch.setColor(1, 1, 1, 1);
            }
            // Tiles
            for (int i = 0; i < l.getWidth(); i++) {
                for (int j = 0; j < l.getHeight(); j++) {
                    t = TileGraphics.getTexture(l.getTiles()[j*l.getWidth() + i]);
                    if (t != null) {
                        batch.draw(t,
                                x + i*tilesize*scale,
                                y + (tilesize*room.getHeight()-(j+1)*tilesize)*scale,
                                tilesize*scale,
                                tilesize*scale);   
                    }
                }
            }
            // Entities
            for (Entity e: l.getEntities()) {
                t = TileGraphics.getTexture(e);
                if (t != null) {
                    batch.draw(t,
                            x + e.getX()*tilesize*scale,
                            y + (tilesize*room.getHeight() - (e.getY()+1)*tilesize)*scale,
                            tilesize*scale,
                            tilesize*scale);
                }
            }
        }
    }
    
    public void setGrayLayers(boolean b) {
        this.grayLayers = b;
    }
    
    public boolean getGrayLayers() {
        return this.grayLayers;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public void setLayer(int layer) {
        this.layerIndex = layer;
    }
    
    public void setScale(float scale) {
        this.scale = scale;
    }
    
    public void setWidth(int pixels) {
        this.scale = pixels / (float) (tilesize*room.getWidth());
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public XYPos getClickPosition(int mousex, int mousey) {
        if (room == null) return null;
        mousex -= x;
        mousex /= tilesize*scale;
        mousey -= y;
        mousey /= tilesize*scale;
        mousey = room.getHeight() - mousey - 1;
        if (mousex < 0 || mousex >= room.getWidth() || mousey < 0 || mousey >= room.getHeight()) return null;
        else return new XYPos(mousex, mousey);
    }

    public float getTilesize() {
        return tilesize*scale;
    }
    
}
