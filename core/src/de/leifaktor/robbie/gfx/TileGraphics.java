package de.leifaktor.robbie.gfx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.leifaktor.robbie.data.entities.Entity;
import de.leifaktor.robbie.data.tiles.Tile;

public class TileGraphics {

    // TILES
    public static TextureRegion empty;
    public static TextureRegion[] redWalls;
    public static TextureRegion transparent;
    public static TextureRegion[] tunnels;
    public static TextureRegion[] darkWalls;
    public static TextureRegion sand;
    public static TextureRegion[] doors;
    public static TextureRegion[] jungle;
    public static TextureRegion[] pyramid;
    public static TextureRegion glass;
    public static TextureRegion rocks[];
    public static TextureRegion water[];
    public static TextureRegion temple[];
    // ENTITIES
    public static TextureRegion gold;
    public static TextureRegion[] arrows;
    public static TextureRegion elektrozaun;
    // ITEMS
    public static TextureRegion acid;
    public static TextureRegion life;
    public static TextureRegion keys[];
    public static TextureRegion magnets[];
    public static TextureRegion notiz;
    public static TextureRegion shoes;
    public static TextureRegion shovel;
    // PLAYER
    public static TextureRegion player;
    
    public static void createTileGraphics(Tileset tileset) {
        // TILES
        empty = tileset.getTextureRegion("empty");
        transparent = tileset.getTextureRegion("transparent");
        tunnels = new TextureRegion[4];
        tunnels[0] = tileset.getTextureRegion("tunnel_n");
        tunnels[1] = tileset.getTextureRegion("tunnel_e");
        tunnels[2] = tileset.getTextureRegion("tunnel_s");
        tunnels[3] = tileset.getTextureRegion("tunnel_w");
        darkWalls = new TextureRegion[5];
        darkWalls[0] = tileset.getTextureRegion("dark_wall");
        darkWalls[1] = tileset.getTextureRegion("dark_wall_se");
        darkWalls[2] = tileset.getTextureRegion("dark_wall_sw");
        darkWalls[3] = tileset.getTextureRegion("dark_wall_ne");
        darkWalls[4] = tileset.getTextureRegion("dark_wall_nw");
        sand = tileset.getTextureRegion("sand");
        doors = new TextureRegion[16];
        doors[0] = tileset.getTextureRegion("door_1");
        doors[1] = tileset.getTextureRegion("door_2");
        doors[2] = tileset.getTextureRegion("door_3");
        doors[3] = tileset.getTextureRegion("door_4");
        doors[4] = tileset.getTextureRegion("door_5");
        doors[5] = tileset.getTextureRegion("door_6");
        doors[6] = tileset.getTextureRegion("door_7");
        doors[7] = tileset.getTextureRegion("door_8");
        doors[8] = tileset.getTextureRegion("door_9");
        doors[9] = tileset.getTextureRegion("door_10");
        doors[10] = tileset.getTextureRegion("door_11");
        doors[11] = tileset.getTextureRegion("door_12");
        doors[12] = tileset.getTextureRegion("door_13");
        doors[13] = tileset.getTextureRegion("door_14");
        doors[14] = tileset.getTextureRegion("door_15");
        doors[15] = tileset.getTextureRegion("door_16");
        jungle = new TextureRegion[7];
        jungle[0] = tileset.getTextureRegion("jungle_1");
        jungle[1] = tileset.getTextureRegion("jungle_2");
        jungle[2] = tileset.getTextureRegion("jungle_3");
        jungle[3] = tileset.getTextureRegion("jungle_sand_se");
        jungle[4] = tileset.getTextureRegion("jungle_sand_sw");
        jungle[5] = tileset.getTextureRegion("jungle_sand_ne");
        jungle[6] = tileset.getTextureRegion("jungle_sand_nw");        
        pyramid = new TextureRegion[9];
        pyramid[0] = tileset.getTextureRegion("pyramid_nw");
        pyramid[1] = tileset.getTextureRegion("pyramid_n");
        pyramid[2] = tileset.getTextureRegion("pyramid_ne");
        pyramid[3] = tileset.getTextureRegion("pyramid_w");
        pyramid[4] = tileset.getTextureRegion("pyramid_center");
        pyramid[5] = tileset.getTextureRegion("pyramid_e");
        pyramid[6] = tileset.getTextureRegion("pyramid_sw");
        pyramid[7] = tileset.getTextureRegion("pyramid_s");
        pyramid[8] = tileset.getTextureRegion("pyramid_se");
        glass = tileset.getTextureRegion("glass");
        rocks = new TextureRegion[9];
        rocks[0] = tileset.getTextureRegion("rock");
        rocks[1] = tileset.getTextureRegion("dark_rock_sand_se");
        rocks[2] = tileset.getTextureRegion("dark_rock_sand_sw");
        rocks[3] = tileset.getTextureRegion("dark_rock_sand_ne");
        rocks[4] = tileset.getTextureRegion("dark_rock_sand_nw");
        rocks[5] = tileset.getTextureRegion("bright_rock_sand_se");
        rocks[6] = tileset.getTextureRegion("bright_rock_sand_sw");
        rocks[7] = tileset.getTextureRegion("bright_rock_sand_ne");
        rocks[8] = tileset.getTextureRegion("bright_rock_sand_nw");
        water = new TextureRegion[5];
        water[0] = tileset.getTextureRegion("water");
        water[1] = tileset.getTextureRegion("water_sand_se");
        water[2] = tileset.getTextureRegion("water_sand_sw");
        water[3] = tileset.getTextureRegion("water_sand_ne");
        water[4] = tileset.getTextureRegion("water_sand_nw");
        temple = new TextureRegion[8];
        temple[0] = tileset.getTextureRegion("temple_1");
        temple[1] = tileset.getTextureRegion("temple_2");
        temple[2] = tileset.getTextureRegion("temple_sand_ns");
        temple[3] = tileset.getTextureRegion("temple_sand_ew");
        temple[4] = tileset.getTextureRegion("temple_sand_se");
        temple[5] = tileset.getTextureRegion("temple_sand_sw");
        temple[6] = tileset.getTextureRegion("temple_sand_ne");
        temple[7] = tileset.getTextureRegion("temple_sand_nw");
        
        // ENTITIES
        gold = tileset.getTextureRegion("gold");
        arrows = new TextureRegion[4];
        arrows[0] = tileset.getTextureRegion("arrow_n");
        arrows[1] = tileset.getTextureRegion("arrow_e");
        arrows[2] = tileset.getTextureRegion("arrow_s");
        arrows[3] = tileset.getTextureRegion("arrow_w");
        elektrozaun = tileset.getTextureRegion("electric_fence");
        redWalls = new TextureRegion[5];
        redWalls[0] = tileset.getTextureRegion("red_wall");
        redWalls[1] = tileset.getTextureRegion("red_wall_se");
        redWalls[2] = tileset.getTextureRegion("red_wall_sw");
        redWalls[3] = tileset.getTextureRegion("red_wall_ne");
        redWalls[4] = tileset.getTextureRegion("red_wall_nw");
        
        // ITEMS
        acid = tileset.getTextureRegion("acid");
        life = tileset.getTextureRegion("life");
        keys = new TextureRegion[16];
        keys[0] = tileset.getTextureRegion("key_1");
        keys[1] = tileset.getTextureRegion("key_2");
        keys[2] = tileset.getTextureRegion("key_3");
        keys[3] = tileset.getTextureRegion("key_4");
        keys[4] = tileset.getTextureRegion("key_5");
        keys[5] = tileset.getTextureRegion("key_6");
        keys[6] = tileset.getTextureRegion("key_7");
        keys[7] = tileset.getTextureRegion("key_8");
        keys[8] = tileset.getTextureRegion("key_9");
        keys[9] = tileset.getTextureRegion("key_10");
        keys[10] = tileset.getTextureRegion("key_11");
        keys[11] = tileset.getTextureRegion("key_12");
        keys[12] = tileset.getTextureRegion("key_13");
        keys[13] = tileset.getTextureRegion("key_14");
        keys[14] = tileset.getTextureRegion("key_15");
        keys[15] = tileset.getTextureRegion("key_16");
        magnets = new TextureRegion[2];
        magnets[0] = tileset.getTextureRegion("magnet_1");
        magnets[1] = tileset.getTextureRegion("magnet_2");
        notiz = tileset.getTextureRegion("notiz");
        shovel = tileset.getTextureRegion("shovel");
        shoes = tileset.getTextureRegion("shoes");        
        
        // PLAYER
        player = tileset.getTextureRegion("player");
    }
    
    public static TextureRegion getTexture(Tile tile) {
        TextureRegion t = null;
        if (tile != null) {
            switch (tile.getID()) {
            case 0: t = empty; break;
            case 1: t = water[0]; break;
            case 2: t = water[1]; break;
            case 3: t = water[2]; break;
            case 4: t = water[3]; break;
            case 5: t = water[4]; break;
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
            case 16: t = sand; break;
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
            case 37: t = jungle[0]; break;
            case 38: t = jungle[1]; break;
            case 39: t = jungle[2]; break;
            case 40: t = jungle[3]; break;
            case 41: t = jungle[4]; break;
            case 42: t = jungle[5]; break;
            case 43: t = jungle[6]; break;
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
            case 54: t = rocks[0]; break;
            case 55: t = rocks[1]; break;
            case 56: t = rocks[2]; break;
            case 57: t = rocks[3]; break;
            case 58: t = rocks[4]; break;
            case 59: t = rocks[5]; break;
            case 60: t = rocks[6]; break;
            case 61: t = rocks[7]; break;
            case 62: t = rocks[8]; break;
            case 63: t = temple[0]; break;
            case 64: t = temple[1]; break;
            case 65: t = temple[2]; break;
            case 66: t = temple[3]; break;
            case 67: t = temple[4]; break;
            case 68: t = temple[5]; break;
            case 69: t = temple[6]; break;
            case 70: t = temple[7]; break;
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
            case 24: t = redWalls[0]; break;
            case 25: t = redWalls[1]; break;
            case 26: t = redWalls[2]; break;
            case 27: t = redWalls[3]; break;
            case 28: t = redWalls[4]; break;
            case 29: t = magnets[0]; break;
            case 30: t = magnets[1]; break;
            case 31: t = notiz; break;
            case 32: t = shovel; break;
            case 33: t = shoes; break;
            }
        }
        return t;
    }
    
    public static TextureRegion getPlayerTexture() {
        return player;
    }
    
}
