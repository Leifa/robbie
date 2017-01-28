package de.leifaktor.robbie.data.entities;

public class Gold extends Entity {
    
    public Gold(){};
    
    public Gold(int id) {
        super(id);
    }

    @Override
    public Entity clone() {
        return new Gold(id);
    }

}
