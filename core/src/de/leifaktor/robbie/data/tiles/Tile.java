package de.leifaktor.robbie.data.tiles;

public abstract class Tile {
    
    public static Tile[] tiles = new Tile[256];
    
    public static Tile empty = new Empty(0);
    public static Tile water = new Water(1,0);
    public static Tile water_sand_se = new Water(2,1);
    public static Tile water_sand_sw = new Water(3,2);
    public static Tile water_sand_ne = new Water(4,3);
    public static Tile water_sand_nw = new Water(5,4);
    public static Tile transparent = new Transparent(6);
    public static Tile tunnel_n = new TunnelEntrance(7,0);
    public static Tile tunnel_e = new TunnelEntrance(8,1);
    public static Tile tunnel_s = new TunnelEntrance(9,2);
    public static Tile tunnel_w = new TunnelEntrance(10,3);
    public static Tile dark_wall = new DarkWall(11, 0);
    public static Tile dark_wall_se = new DarkWall(12, 1);
    public static Tile dark_wall_sw = new DarkWall(13, 2);
    public static Tile dark_wall_ne = new DarkWall(14, 3);
    public static Tile dark_wall_nw = new DarkWall(15, 4);
    public static Tile sand = new Sand(16,0);
    // 17-20
    public static Tile door1 = new Door(21,1);
    public static Tile door2 = new Door(22,2);
    public static Tile door3 = new Door(23,3);
    public static Tile door4 = new Door(24,4);
    public static Tile door5 = new Door(25,5);
    public static Tile door6 = new Door(26,6);
    public static Tile door7 = new Door(27,7);
    public static Tile door8 = new Door(28,8);
    public static Tile door9 = new Door(29,9);
    public static Tile door10 = new Door(30,10);
    public static Tile door11 = new Door(31,11);
    public static Tile door12 = new Door(32,12);
    public static Tile door13 = new Door(33,13);
    public static Tile door14 = new Door(34,14);
    public static Tile door15 = new Door(35,15);
    public static Tile door16 = new Door(36,16);
    public static Tile forest1 = new Jungle(37,0);
    public static Tile forest2 = new Jungle(38,1);
    public static Tile forest3 = new Jungle(39,2);
    public static Tile forest_se = new Jungle(40,3);
    public static Tile forest_sw = new Jungle(41,4);
    public static Tile forest_nw = new Jungle(42,5);
    public static Tile forest_ne = new Jungle(43,6);
    public static Tile pyramid_nw = new Pyramid(44,0);
    public static Tile pyramid_n = new Pyramid(45,1);
    public static Tile pyramid_ne = new Pyramid(46,2);
    public static Tile pyramid_w = new Pyramid(47,3);
    public static Tile pyramid_center = new Pyramid(48,4);
    public static Tile pyramid_e = new Pyramid(49,5);
    public static Tile pyramid_sw = new Pyramid(50,6);
    public static Tile pyramid_s = new Pyramid(51,7);
    public static Tile pyramid_se = new Pyramid(52,8);
    public static Tile glass = new WalkableTile(53);
    public static Tile rock = new SolidTile(54);
    public static Tile dark_rock_sand_se = new SolidTile(55);
    public static Tile dark_rock_sand_sw = new SolidTile(56);
    public static Tile dark_rock_sand_ne = new SolidTile(57);
    public static Tile dark_rock_sand_nw = new SolidTile(58);
    public static Tile bright_rock_sand_se = new SolidTile(59);
    public static Tile bright_rock_sand_sw = new SolidTile(60);
    public static Tile bright_rock_sand_ne = new SolidTile(61);
    public static Tile bright_rock_sand_nw = new SolidTile(62);
    
    protected int id;
    
    public Tile(int id) {
        this.id = id;
        if (tiles[id] != null) throw new RuntimeException("Tile id already in use: " + id);
        tiles[id] = this;
    }
    
    public int getID() {
        return id;
    }
    
    public static Tile get(int id) {
        return tiles[id];
    }
    
    public String toString() {
        return "" + this.id;
    }

}
