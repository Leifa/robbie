package de.leifaktor.robbie.gfx;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tileset {

    public static TextureRegion missing;

    String filename;
    Texture texture;
    int tilesize;

    HashMap<String, TextureRegion> map;

    public Tileset(String filename) {
        this.filename = filename;
        texture = new Texture(filename + ".png");
        if (missing == null) missing = new TextureRegion(new Texture("missing.png"));
        initMap();
    }

    private void initMap() {
        map = new HashMap<String, TextureRegion>();
        try {
            FileHandle fh = Gdx.files.internal(filename + ".txt");
            String[] lines = fh.readString().split("\\r?\\n");
            tilesize = Integer.parseInt(lines[0].substring(lines[0].indexOf("=")+1));
            for (int i = 1; i < lines.length; i++) {
                String[] line = lines[i].split(":");
                String key = line[0];
                int x = Integer.parseInt(line[1]);
                int y = Integer.parseInt(line[2]);
                TextureRegion tr = new TextureRegion(texture, x*tilesize, y*tilesize, tilesize, tilesize);
                if (map.put(key, tr) != null) {
                    Gdx.app.log("Tileset", "Texture " + key + " defined multiple times.");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public TextureRegion getTextureRegion(String key) {
        TextureRegion tr = map.get(key);
        if (tr != null) return tr;        
        else {
            Gdx.app.log("Tileset", "Texture " + key + " not found.");
            return missing;
        }
    }

}
