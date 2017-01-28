package de.leifaktor.robbie.gfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tileset {
    
    public TextureRegion empty;

    Texture texture;
    int tilesize;
    
    public Tileset(String filename, int tilesize) {
        texture = new Texture(filename);
        this.tilesize = tilesize;
    }
    
    public TextureRegion getTextureRegion(int x, int y) {
        return new TextureRegion(texture, x*tilesize, y*tilesize, tilesize, tilesize);
    }
    
}
