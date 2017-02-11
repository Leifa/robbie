package de.leifaktor.robbie.data.entities;

public class Elektrozaun extends Entity {
    
    public Elektrozaun() {};
    
    @Override
    public Entity clone() {
        Elektrozaun e = new Elektrozaun();
        e.setID(this.id);
        return e;
    }

}
