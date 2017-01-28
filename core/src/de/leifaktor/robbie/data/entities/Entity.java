package de.leifaktor.robbie.data.entities;

import de.leifaktor.robbie.data.items.Acid;
import de.leifaktor.robbie.data.items.Key;
import de.leifaktor.robbie.data.items.Life;

public abstract class Entity {
    
    public static Entity[] entities = new Entity[256];
  
    public static Entity gold = new Gold(0);
    public static Entity arrow0 = new Arrow(1,0);
    public static Entity arrow1 = new Arrow(2,1);
    public static Entity arrow2 = new Arrow(3,2);
    public static Entity arrow3 = new Arrow(4,4);
    public static Entity elektrozaun = new Elektrozaun(5);
    public static Entity acid = new ItemEntity(6,new Acid());
    public static Entity life = new ItemEntity(7,new Life());
    public static Entity key1 = new ItemEntity(8,new Key(1));
    public static Entity key2 = new ItemEntity(9,new Key(2));
    public static Entity key3 = new ItemEntity(10,new Key(3));
    public static Entity key4 = new ItemEntity(11,new Key(4));
    public static Entity key5 = new ItemEntity(12,new Key(5));
    public static Entity key6 = new ItemEntity(13,new Key(6));
    public static Entity key7 = new ItemEntity(14,new Key(7));
    public static Entity key8 = new ItemEntity(15,new Key(8));
    public static Entity key9 = new ItemEntity(16,new Key(9));
    public static Entity key10 = new ItemEntity(17,new Key(10));
    public static Entity key11 = new ItemEntity(18,new Key(11));
    public static Entity key12 = new ItemEntity(19,new Key(12));
    public static Entity key13 = new ItemEntity(20,new Key(13));
    public static Entity key14 = new ItemEntity(21,new Key(14));
    public static Entity key15 = new ItemEntity(22,new Key(15));
    public static Entity key16 = new ItemEntity(23,new Key(16));
    
    int x;
    int y;
    
    protected int id;
    
    public Entity() {}; // no-arg constructor for json
    
    public Entity(int id) {
        this.id = id;
        if (entities[id] == null) {
            entities[id] = this;
        }
    }
 
    public int getId() {
        return id;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public abstract Entity clone();
}
