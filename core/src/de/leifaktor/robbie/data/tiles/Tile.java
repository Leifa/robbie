package de.leifaktor.robbie.data.tiles;

public abstract class Tile {
    
    public static Tile[] tiles = new Tile[256];
    
    public static Tile empty = new Empty(0);
    public static Tile red_wall = new Wall(1,0);
    public static Tile red_wall_se = new Wall(2,1);
    public static Tile red_wall_sw = new Wall(3,2);
    public static Tile red_wall_ne = new Wall(4,3);
    public static Tile red_wall_nw = new Wall(5,4);
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
    public static Tile sand_se = new Sand(17,1);
    public static Tile sand_sw = new Sand(18,2);
    public static Tile sand_ne = new Sand(19,3);
    public static Tile sand_nw = new Sand(20,4);
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
    public static Tile forest1 = new Forest(37,0);
    public static Tile forest2 = new Forest(38,1);
    public static Tile forest3 = new Forest(39,2);
    public static Tile forest_se = new Forest(40,3);
    public static Tile forest_sw = new Forest(41,4);
    public static Tile forest_nw = new Forest(42,5);
    public static Tile forest_ne = new Forest(43,6);
    public static Tile pyramid_nw = new Pyramid(44,0);
    public static Tile pyramid_n = new Pyramid(45,1);
    public static Tile pyramid_ne = new Pyramid(46,2);
    public static Tile pyramid_w = new Pyramid(47,3);
    public static Tile pyramid_center = new Pyramid(48,4);
    public static Tile pyramid_e = new Pyramid(49,5);
    public static Tile pyramid_sw = new Pyramid(50,6);
    public static Tile pyramid_s = new Pyramid(51,7);
    public static Tile pyramid_se = new Pyramid(52,8);
    
    
    protected int id;
    
    public Tile(int id) {
        this.id = id;
        if (tiles[id] != null) throw new RuntimeException("Tile id already in use: " + id);
        tiles[id] = this;
    }
    
    public int getType() {
        return id;
    }
    
    public static Tile get(int id) {
        return tiles[id];
    }

}
