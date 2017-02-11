package de.leifaktor.robbie.data.entities;

public class WalkableIsolator extends Entity {

    @Override
    public Entity clone() {
        WalkableIsolator i = new WalkableIsolator();
        i.setID(this.id);
        i.setDescription(this.description);        
        return i;
    }

}
