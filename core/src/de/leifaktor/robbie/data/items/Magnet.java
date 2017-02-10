package de.leifaktor.robbie.data.items;

public class Magnet extends Item {
    
    boolean positive;
    
    public Magnet() {}
    
    public Magnet(boolean positive) {
        this.positive = positive;
    }

    @Override
    public Item clone() {
        return null;
    }

}
