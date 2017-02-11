package de.leifaktor.robbie.gfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.leifaktor.robbie.data.entities.Entity;

public class EntityPaletteRenderer {
    
    int tilesPerRow;
    int tileSize;
    
    int x,y; // Upper left corner

    public void render(SpriteBatch batch) {
        int row = 0;
        int col = 0;
        TextureRegion t = null;
        for (int i = 0; i < Entity.entities.length; i++) {
            t = Tileset.getTexture(Entity.entities[i]);
            if (t != null) {
                batch.draw(t, x + col*tileSize, y-tileSize - row*tileSize, tileSize, tileSize);
            }
            col++;
            if (col >= tilesPerRow) {
                col = 0;
                row++;
            }
        }
    }
    
    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }
    
    public void setTilesPerRow(int tilesPerRow) {
        this.tilesPerRow = tilesPerRow;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Returns the clicked Tile, given the coordinates where the mouse clicked.
     * @param mousex
     * @param mousey
     * @return
     */
    
    public Entity getEntity(int mousex, int mousey) {
        if (mousex < x || mousex > x+tileSize*tilesPerRow || mousey > y) {
            return null;
        }
        int palettex = (mousex - x) / tileSize;
        int palettey = (y - mousey) / tileSize;
        int index = palettey*tilesPerRow + palettex;
        if (index >= 0 && index < Entity.entities.length) return Entity.entities[index];
        else return null;
    }
}
