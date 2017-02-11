package de.leifaktor.robbie.data.items;

public class Notiz extends Item {
    
    String text;
    
    public Notiz() {text = "";}

    public Notiz(String text) {
        this.text = text;
    }
    
    @Override
    public Item clone() {
        return new Notiz(new String(text));
    }

}
