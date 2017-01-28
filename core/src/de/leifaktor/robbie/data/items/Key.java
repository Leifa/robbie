package de.leifaktor.robbie.data.items;

public class Key extends Item {
    
    int number;
    
    public Key() {} // no-arg constructor for json
    
    public Key(int number) {
        this.number = number;
    }

    @Override
    public Item clone() {
        return new Key(number);
    }

}
