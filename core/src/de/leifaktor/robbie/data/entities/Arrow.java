package de.leifaktor.robbie.data.entities;

public class Arrow extends Entity {
    
    int direction;
    
    public Arrow(){};
    
    public Arrow(int id, int direction) {
        super(id);
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public Entity clone() {
        Arrow a = new Arrow(id, this.direction);
        return a;
    }

}
