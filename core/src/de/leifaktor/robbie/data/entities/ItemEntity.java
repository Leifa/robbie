package de.leifaktor.robbie.data.entities;

import de.leifaktor.robbie.data.items.Item;

public class ItemEntity extends Entity {
    
    private Item item;
    
    public ItemEntity(){};
    
    public ItemEntity(Item item) {
        this.item = item;
    }

    @Override
    public Entity clone() {
        ItemEntity ie = new ItemEntity(item.clone());
        ie.setID(this.id);
        ie.setDescription(this.description);
        return ie;
    }
    
    public Item getItem() {
        return item;
    }

}
