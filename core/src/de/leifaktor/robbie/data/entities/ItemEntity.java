package de.leifaktor.robbie.data.entities;

import de.leifaktor.robbie.data.items.Item;

public class ItemEntity extends Entity {
    
    private Item item;
    
    public ItemEntity(){};
    
    public ItemEntity(int id, Item item) {
        super(id);
        this.item = item;
    }

    @Override
    public Entity clone() {
        return new ItemEntity(id, item.clone());
    }
    
    public Item getItem() {
        return item;
    }

}
