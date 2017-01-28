package de.leifaktor.robbie.data.entities;

public class Elektrozaun extends Entity {
    
    public Elektrozaun() {};
    
    public Elektrozaun(int id) {
        super(id);
    }

    @Override
    public Entity clone() {
        return new Elektrozaun(id);
    }

}
