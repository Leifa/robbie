package de.leifaktor.robbie.data.tiles;

import de.leifaktor.robbie.data.entities.Entity;

public class SolidTile extends Tile {

    public SolidTile() {}
    
    @Override
    public boolean isWalkable(Entity e) {
        return false;
    }

}
