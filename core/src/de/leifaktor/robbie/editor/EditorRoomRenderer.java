package de.leifaktor.robbie.editor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.leifaktor.robbie.data.Episode;
import de.leifaktor.robbie.data.Room;
import de.leifaktor.robbie.data.RoomLayer;
import de.leifaktor.robbie.data.XYPos;
import de.leifaktor.robbie.data.entities.Entity;
import de.leifaktor.robbie.gfx.Tileset;

public class EditorRoomRenderer {
    
    private int tilesize;
    private Episode episode;
    private Room room;
    int layerIndex;
    private float scale = 1.5f;
    private int x, y; // lower left corner
    
    private boolean grayLayers;
    
    public EditorRoomRenderer(int tilesize) {
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
                    t = Tileset.getTexture(l.getTiles()[j*l.getWidth() + i]);
                    if (t != null) {
                        batch.draw(t,
                                x + i*tilesize*scale,
                                y + j*tilesize*scale,
                                tilesize*scale,
                                tilesize*scale);   
                    }
                }
            }
            // Entities
            for (Entity e: l.getEntities()) {
                t = Tileset.getTexture(e);
                if (t != null) {
                    batch.draw(t,
                            x + e.getX()*tilesize*scale,
                            y + e.getY()*tilesize*scale,
                            tilesize*scale,
                            tilesize*scale);
                }
            }
            // Starting Position
            if (episode != null) {
                if (episode.getStartingPosition() != null) {
                    if (episode.getRooms().getRoom(episode.getStartingPosition().roomPosition) == room) {
                        if (episode.getStartingPosition().layer == k) {
                            t = Tileset.getPlayerTexture();
                            if (t != null) {
                                batch.draw(t,
                                        x + episode.getStartingPosition().x*tilesize*scale,
                                        y + episode.getStartingPosition().y*tilesize*scale,
                                        tilesize*scale,
                                        tilesize*scale);
                            }
                        }
                    }
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
    
    public void setEpisode(Episode episode) {
        this.episode = episode;
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
        if (mousex < 0 || mousex >= room.getWidth() || mousey < 0 || mousey >= room.getHeight()) return null;
        else return new XYPos(mousex, mousey);
    }

    public float getTilesize() {
        return tilesize*scale;
    }
    
}
