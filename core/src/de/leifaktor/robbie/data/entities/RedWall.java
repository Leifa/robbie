package de.leifaktor.robbie.data.entities;

public class RedWall extends Entity {
    
    private int type;
    
    public RedWall() {}
    
    public RedWall(int id, int type) {
        super(id);
        this.type = type;
    }
    
    public int getType() {
        return type;
    }

    @Override
    public Entity clone() {
        return new RedWall(id, type);
    }

}
