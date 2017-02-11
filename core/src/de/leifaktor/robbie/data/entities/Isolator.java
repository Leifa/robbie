package de.leifaktor.robbie.data.entities;

public class Isolator extends Entity {

    @Override
    public Entity clone() {
        Isolator i = new Isolator();
        i.setID(this.id);
        i.setDescription(this.description);
        return i;
    }

}
