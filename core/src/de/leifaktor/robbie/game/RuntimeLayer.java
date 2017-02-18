package de.leifaktor.robbie.game;

import java.util.ArrayList;

import de.leifaktor.robbie.data.RoomLayer;
import de.leifaktor.robbie.data.entities.Entity;

public class RuntimeLayer {
    
    public RoomLayer layer;
    public ArrayList<RuntimeEntity> entities;
    public RuntimeEntity player;
    
    public RuntimeLayer(RoomLayer layer) {
        this.layer = layer;
        entities = new ArrayList<RuntimeEntity>();
        for (Entity e: layer.getEntities()) {
            entities.add(new RuntimeEntity(e));
        }
    }
    
    public void continueMovement() {
        for (RuntimeEntity e: entities) e.continueMovement(this);
        if (player != null) player.continueMovement(this);
    }
    
    public void update() {
        for (RuntimeEntity e: entities) e.update(this);
        if (player != null) player.update(this);
    }

    public void addPlayer(RuntimeEntity player) {
        this.player = player;
    }

}
