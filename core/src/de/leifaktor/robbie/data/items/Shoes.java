package de.leifaktor.robbie.data.items;

public class Shoes extends Item {

    @Override
    public Item clone() {
        return new Shoes();
    }

}
