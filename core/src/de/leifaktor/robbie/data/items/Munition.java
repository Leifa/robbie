package de.leifaktor.robbie.data.items;

public class Munition extends Item {
    
    int number;
    
    public Munition() {}
    
    public Munition(int number) {
        this.number = number;
    }

    @Override
    public Item clone() {
        return new Munition(number);
    }

    public void setNumber(int number) {
        this.number = number;        
    }

}
