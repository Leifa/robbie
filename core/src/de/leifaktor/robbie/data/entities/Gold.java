package de.leifaktor.robbie.data.entities;

public class Gold extends Entity {
    
    public Gold(){};    

    @Override
    public Entity clone() {
        Gold g = new Gold();
        g.setID(this.id);
        return g;
    }

}
