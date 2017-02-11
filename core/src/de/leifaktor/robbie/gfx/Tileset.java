package de.leifaktor.robbie.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.leifaktor.robbie.data.entities.Entity;
import de.leifaktor.robbie.data.entities.ItemEntity;
import de.leifaktor.robbie.data.items.Item;
import de.leifaktor.robbie.data.tiles.Tile;

public class Tileset {

    public static TextureRegion missing;

    String filename;
    Texture texture;
    int tilesize;

    private static TextureRegion[] tileTextures;
    private static TextureRegion[] entityTextures;
    private static TextureRegion playerTexture;

    public Tileset(String filename) {
        this.filename = filename;
        texture = new Texture(filename + ".png");
        if (missing == null) missing = new TextureRegion(new Texture("missing.png"));
        if (tileTextures == null) tileTextures = new TextureRegion[256];
        if (entityTextures == null) entityTextures = new TextureRegion[256];        
        initMap();
    }

    private void initMap() {
        FileHandle fh = Gdx.files.internal(filename + ".txt");
        String[] lines = fh.readString().split("\\r?\\n");
        tilesize = Integer.parseInt(lines[0].substring(lines[0].indexOf("=")+1));
        for (int i = 1; i < lines.length; i++) {
            String[] line = lines[i].split(":");
            String type = line[0];                
            if (type.equals("T")) { // TILE
                parseTile(line);
            } else if (type.equals("E")) { // ENTITY
                parseEntity(line);
            } else if (type.equals("I")) { // ITEM
                parseItem(line);
            } else if (type.equals("P")) { // PLAYER
                parsePlayer(line);
            }
        }
    }

    private void parseTile(String[] line) {
        System.out.println(line[2]);
        int id = Integer.parseInt(line[1]);
        String description = line[2];
        int x = Integer.parseInt(line[3]);
        int y = Integer.parseInt(line[4]);
        String classname = line[5];
        TextureRegion tr = new TextureRegion(texture, x*tilesize, y*tilesize, tilesize, tilesize);
        Tile tile;
        try {
            tile = (Tile) Class.forName("de.leifaktor.robbie.data.tiles." + classname).newInstance();
            tile.setID(id);
            tile.setDescription(description);                    
            if (tileTextures[id] != null) {
                Gdx.app.log("Tileset", "Tile Texture id " + id + " defined multiple times.");
            } else {
                tileTextures[id] = tr;
                Tile.registerTile(tile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void parseEntity(String[] line) {
        int id = Integer.parseInt(line[1]);
        String description = line[2];
        int x = Integer.parseInt(line[3]);
        int y = Integer.parseInt(line[4]);
        String classname = line[5];
        TextureRegion tr = new TextureRegion(texture, x*tilesize, y*tilesize, tilesize, tilesize);
        Entity entity;
        try {
            entity = (Entity) Class.forName("de.leifaktor.robbie.data.entities." + classname).newInstance();
            entity.setID(id);
            entity.setDescription(description);                    
            if (entityTextures[id] != null) {
                Gdx.app.log("Tileset", "Entity texture id " + id + " defined multiple times.");
            } else {
                entityTextures[id] = tr;
                Entity.registerEntity(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void parseItem(String[] line) {
        int id = Integer.parseInt(line[1]);
        String description = line[2];
        int x = Integer.parseInt(line[3]);
        int y = Integer.parseInt(line[4]);
        String classname = line[5];
        TextureRegion tr = new TextureRegion(texture, x*tilesize, y*tilesize, tilesize, tilesize);
        Item item;
        try {
            item = (Item) Class.forName("de.leifaktor.robbie.data.items." + classname).newInstance();
            Entity entity = new ItemEntity(item);
            entity.setID(id);
            entity.setDescription(description);
            if (entityTextures[id] != null) {
                Gdx.app.log("Tileset", "Entity texture id " + id + " defined multiple times.");
            } else {
                entityTextures[id] = tr;
                Entity.registerEntity(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void parsePlayer(String[] line) {
        int x = Integer.parseInt(line[1]);
        int y = Integer.parseInt(line[2]);
        if (playerTexture != null) {
            Gdx.app.log("Tileset", "Player texture defined multiple times.");
        } else {
            playerTexture = new TextureRegion(texture, x*tilesize, y*tilesize, tilesize, tilesize);
        }
    }

    public static TextureRegion getTexture(Tile tile) {
        if (tile != null) return tileTextures[tile.getID()];
        else return missing;
    }

    public static TextureRegion getTexture(Entity entity) {
        if (entity != null) return entityTextures[entity.getId()];
        else return missing;
    }

    public static TextureRegion getPlayerTexture() {
        return playerTexture;
    }

}
