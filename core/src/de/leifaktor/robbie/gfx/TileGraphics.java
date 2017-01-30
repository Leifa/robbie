package de.leifaktor.robbie.gfx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.leifaktor.robbie.data.entities.Arrow;
import de.leifaktor.robbie.data.entities.Entity;
import de.leifaktor.robbie.data.tiles.Tile;

public class TileGraphics {

    // TILES
    public static TextureRegion empty;
    public static TextureRegion[] redWalls;
    public static TextureRegion transparent;
    public static TextureRegion[] tunnels;
    public static TextureRegion[] darkWalls;
    public static TextureRegion[] sand;
    public static TextureRegion[] doors;
    public static TextureRegion[] forest;
    public static TextureRegion[] pyramid;
    public static TextureRegion glass;
    // ENTITIES
    public static TextureRegion gold;
    public static TextureRegion[] arrows;
    public static TextureRegion elektrozaun;
    // ITEMS
    public static TextureRegion acid;
    public static TextureRegion life;
    public static TextureRegion keys[];
    // PLAYER
    public static TextureRegion player;
    
    public static void createTileGraphics(Tileset tileset) {
        // TILES
        empty = tileset.getTextureRegion(0, 0);
        redWalls = new TextureRegion[5];
        redWalls[0] = tileset.getTextureRegion(1, 0);
        redWalls[1] = tileset.getTextureRegion(2, 0);
        redWalls[2] = tileset.getTextureRegion(3, 0);
        redWalls[3] = tileset.getTextureRegion(4, 0);
        redWalls[4] = tileset.getTextureRegion(5, 0);
        transparent = tileset.getTextureRegion(14, 1);
        tunnels = new TextureRegion[4];
        tunnels[0] = tileset.getTextureRegion(4, 1);
        tunnels[1] = tileset.getTextureRegion(5, 1);
        tunnels[2] = tileset.getTextureRegion(6, 1);
        tunnels[3] = tileset.getTextureRegion(7, 1);
        darkWalls = new TextureRegion[5];
        darkWalls[0] = tileset.getTextureRegion(6, 0);
        darkWalls[1] = tileset.getTextureRegion(7, 0);
        darkWalls[2] = tileset.getTextureRegion(8, 0);
        darkWalls[3] = tileset.getTextureRegion(9, 0);
        darkWalls[4] = tileset.getTextureRegion(10, 0);
        sand = new TextureRegion[5];
        sand[0] = tileset.getTextureRegion(11, 0);
        sand[1] = tileset.getTextureRegion(12, 0);
        sand[2] = tileset.getTextureRegion(13, 0);
        sand[3] = tileset.getTextureRegion(14, 0);
        sand[4] = tileset.getTextureRegion(15, 0);
        doors = new TextureRegion[16];
        doors[0] = tileset.getTextureRegion(0, 2);
        doors[1] = tileset.getTextureRegion(1, 2);
        doors[2] = tileset.getTextureRegion(2, 2);
        doors[3] = tileset.getTextureRegion(3, 2);
        doors[4] = tileset.getTextureRegion(4, 2);
        doors[5] = tileset.getTextureRegion(5, 2);
        doors[6] = tileset.getTextureRegion(6, 2);
        doors[7] = tileset.getTextureRegion(7, 2);
        doors[8] = tileset.getTextureRegion(8, 2);
        doors[9] = tileset.getTextureRegion(9, 2);
        doors[10] = tileset.getTextureRegion(10, 2);
        doors[11] = tileset.getTextureRegion(11, 2);
        doors[12] = tileset.getTextureRegion(12, 2);
        doors[13] = tileset.getTextureRegion(13, 2);
        doors[14] = tileset.getTextureRegion(14, 2);
        doors[15] = tileset.getTextureRegion(15, 2);
        forest = new TextureRegion[7];
        forest[0] = tileset.getTextureRegion(0, 8);
        forest[1] = tileset.getTextureRegion(1, 8);
        forest[2] = tileset.getTextureRegion(2, 8);
        forest[3] = tileset.getTextureRegion(3, 8);
        forest[4] = tileset.getTextureRegion(4, 8);
        forest[5] = tileset.getTextureRegion(5, 8);
        forest[6] = tileset.getTextureRegion(6, 8);        
        pyramid = new TextureRegion[9];
        pyramid[0] = tileset.getTextureRegion(0, 9);
        pyramid[1] = tileset.getTextureRegion(1, 9);
        pyramid[2] = tileset.getTextureRegion(2, 9);
        pyramid[3] = tileset.getTextureRegion(3, 9);
        pyramid[4] = tileset.getTextureRegion(4, 9);
        pyramid[5] = tileset.getTextureRegion(5, 9);
        pyramid[6] = tileset.getTextureRegion(6, 9);
        pyramid[7] = tileset.getTextureRegion(7, 9);
        pyramid[8] = tileset.getTextureRegion(8, 9);
        glass = tileset.getTextureRegion(13, 11);
        
        // ENTITIES
        gold = tileset.getTextureRegion(1, 10);
        arrows = new TextureRegion[4];
        arrows[0] = tileset.getTextureRegion(6, 10);
        arrows[1] = tileset.getTextureRegion(7, 10);
        arrows[2] = tileset.getTextureRegion(8, 10);
        arrows[3] = tileset.getTextureRegion(9, 10);
        elektrozaun = tileset.getTextureRegion(0, 10);
        
        // ITEMS
        acid = tileset.getTextureRegion(2, 10);
        life = tileset.getTextureRegion(7, 11);
        keys = new TextureRegion[16];
        keys[0] = tileset.getTextureRegion(0, 4);
        keys[1] = tileset.getTextureRegion(1, 4);
        keys[2] = tileset.getTextureRegion(2, 4);
        keys[3] = tileset.getTextureRegion(3, 4);
        keys[4] = tileset.getTextureRegion(4, 4);
        keys[5] = tileset.getTextureRegion(5, 4);
        keys[6] = tileset.getTextureRegion(6, 4);
        keys[7] = tileset.getTextureRegion(7, 4);
        keys[8] = tileset.getTextureRegion(8, 4);
        keys[9] = tileset.getTextureRegion(9, 4);
        keys[10] = tileset.getTextureRegion(10, 4);
        keys[11] = tileset.getTextureRegion(11, 4);
        keys[12] = tileset.getTextureRegion(12, 4);
        keys[13] = tileset.getTextureRegion(13, 4);
        keys[14] = tileset.getTextureRegion(14, 4);
        keys[15] = tileset.getTextureRegion(15, 4);
        
        // PLAYER
        player = tileset.getTextureRegion(0, 7);
    }
    
    public static TextureRegion getTexture(Tile tile) {
        TextureRegion t = null;
        if (tile != null) {
            switch (tile.getType()) {
            case 0: t = empty; break;
            case 1: t = redWalls[0]; break;
            case 2: t = redWalls[1]; break;
            case 3: t = redWalls[2]; break;
            case 4: t = redWalls[3]; break;
            case 5: t = redWalls[4]; break;
            case 6: t = transparent; break;
            case 7: t = tunnels[0]; break;
            case 8: t = tunnels[1]; break;
            case 9: t = tunnels[2]; break;
            case 10: t = tunnels[3]; break;
            case 11: t = darkWalls[0]; break;
            case 12: t = darkWalls[1]; break;
            case 13: t = darkWalls[2]; break;
            case 14: t = darkWalls[3]; break;
            case 15: t = darkWalls[4]; break;
            case 16: t = sand[0]; break;
            case 17: t = sand[1]; break;
            case 18: t = sand[2]; break;
            case 19: t = sand[3]; break;
            case 20: t = sand[4]; break;
            case 21: t = doors[0]; break;
            case 22: t = doors[1]; break;
            case 23: t = doors[2]; break;
            case 24: t = doors[3]; break;
            case 25: t = doors[4]; break;
            case 26: t = doors[5]; break;
            case 27: t = doors[6]; break;
            case 28: t = doors[7]; break;
            case 29: t = doors[8]; break;
            case 30: t = doors[9]; break;
            case 31: t = doors[10]; break;
            case 32: t = doors[11]; break;
            case 33: t = doors[12]; break;
            case 34: t = doors[13]; break;
            case 35: t = doors[14]; break;
            case 36: t = doors[15]; break;
            case 37: t = forest[0]; break;
            case 38: t = forest[1]; break;
            case 39: t = forest[2]; break;
            case 40: t = forest[3]; break;
            case 41: t = forest[4]; break;
            case 42: t = forest[5]; break;
            case 43: t = forest[6]; break;
            case 44: t = pyramid[0]; break;
            case 45: t = pyramid[1]; break;
            case 46: t = pyramid[2]; break;
            case 47: t = pyramid[3]; break;
            case 48: t = pyramid[4]; break;
            case 49: t = pyramid[5]; break;
            case 50: t = pyramid[6]; break;
            case 51: t = pyramid[7]; break;
            case 52: t = pyramid[8]; break;
            case 53: t = glass; break;
            }
        }
        return t;
    }
    
    public static TextureRegion getTexture(Entity entity) {
        TextureRegion t = null;
        if (entity != null) {
            switch (entity.getId()) {
            case 0: t = gold; break;
            case 1: t = arrows[0]; break;
            case 2: t = arrows[1]; break;
            case 3: t = arrows[2]; break;
            case 4: t = arrows[3]; break;
            case 5: t = elektrozaun; break;
            case 6: t = acid; break;
            case 7: t = life; break;
            case 8: t = keys[0]; break;
            case 9: t = keys[1]; break;
            case 10: t = keys[2]; break;
            case 11: t = keys[3]; break;
            case 12: t = keys[4]; break;
            case 13: t = keys[5]; break;
            case 14: t = keys[6]; break;
            case 15: t = keys[7]; break;
            case 16: t = keys[8]; break;
            case 17: t = keys[9]; break;
            case 18: t = keys[10]; break;
            case 19: t = keys[11]; break;
            case 20: t = keys[12]; break;
            case 21: t = keys[13]; break;
            case 22: t = keys[14]; break;
            case 23: t = keys[15]; break;
            }
        }
        return t;
    }
    
    public static TextureRegion getPlayerTexture() {
        return player;
    }
    
}
