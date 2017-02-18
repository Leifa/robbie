package de.leifaktor.robbie.game;

public abstract class EntityController {
    
    RuntimeEntity entity;
    
    public EntityController(RuntimeEntity entity) {
        this.entity = entity;
    }
    
    public abstract int getDirection();

}
