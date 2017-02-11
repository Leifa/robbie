package de.leifaktor.robbie.data.entities;

public class Arrow extends Entity {
    
    int direction;
    
    public Arrow(){};
    
    public Arrow(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public Entity clone() {
        Arrow a = new Arrow(this.direction);
        a.setID(this.id);
        a.setDescription(this.description);
        return a;
    }

}
