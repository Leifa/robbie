package de.leifaktor.robbie.data.entities;

public class RedWall extends Entity {
    
    public RedWall() {}
    
    @Override
    public Entity clone() {
        RedWall r = new RedWall();
        r.setID(this.id);
        return r;
    }

}
