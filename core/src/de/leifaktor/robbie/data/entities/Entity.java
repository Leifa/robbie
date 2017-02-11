package de.leifaktor.robbie.data.entities;

public abstract class Entity {
    
    public static Entity[] entities = new Entity[256];
    
    int x;
    int y;
    
    protected int id;
    protected String description;
    
    public Entity() {}; // no-arg constructor for json
    
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

    public void setID(int id) {
        this.id = id;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public static void registerEntity(Entity entity) {
        if (entities[entity.id] != null) throw new RuntimeException("Entity id already in use: " + entity.id);
        entities[entity.id] = entity;
    }
}
