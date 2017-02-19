package de.leifaktor.robbie.game;

import de.leifaktor.robbie.data.entities.Entity;

public class RuntimeEntity {
    
    public EntityController controller;
    public Entity entity;
    public float x;
    public float y;
    private int direction;
    private float speed; // in tiles per frame
    private float remainingDistance;
    private float walkedSinceLastTile;
    private State state;
    
    public RuntimeEntity(Entity entity) {
        this.entity = entity;
        this.x = entity.getX();
        this.y = entity.getY();
        this.direction = -1;
        this.speed = 0.3f;
        this.remainingDistance = 0;
        this.walkedSinceLastTile = 0;
        this.state = State.IDLE;
    }
    
    enum State {
        IDLE,
        WALKING,
        REACHED_TILE
    }
    
    /**
     * Diese Methode wird am Anfang von jedem Frame aufgerufen. Alle Entities, die sich gerade
     * bewegen, werden erstmal weiter Richtung des nächsten Tiles bewegt und ändern ggf. ihren
     * Status auf REACHED_TILE.
     * @param room
     */
    
    public void continueMovement(RuntimeLayer layer) {        
        this.remainingDistance = speed;
        switch (state) {
        case IDLE: return;
        case WALKING:
            float walkDistance = remainingDistance;
            if (direction < 4) { // gerade laufen
                if (walkedSinceLastTile + walkDistance > 1) {
                    walkDistance = 1 - walkedSinceLastTile;
                    state = State.REACHED_TILE;
                }
                x += Direction.DIR_X[direction]*walkDistance;
                y += Direction.DIR_Y[direction]*walkDistance;
            } else if (direction < 8) { // diagonal laufen
                if (walkedSinceLastTile + walkDistance > 1.41421356f) {
                    walkDistance = 1.41421356f - walkedSinceLastTile;
                    state = State.REACHED_TILE;
                }
                x += Direction.DIR_X[direction]*walkDistance*0.70710678f;
                y += Direction.DIR_Y[direction]*walkDistance*0.70710678f;
            }            
            walkedSinceLastTile += walkDistance;
            break;
        case REACHED_TILE: // should never happen
            break;
        }
    }
    
    public void update(RuntimeLayer layer) {
        switch (state) {
        case WALKING: return;
        case IDLE:
        case REACHED_TILE:
            if (controller != null) {
                this.direction = controller.getDirection();
                if (direction != -1) {
                    tryStartWalkingTowards(direction, layer);
                }
            }
        }        
    }
    
    private void tryStartWalkingTowards(int direction, RuntimeLayer layer) {
        int newx = entity.getX() + Direction.DIR_X[direction];
        int newy = entity.getY() + Direction.DIR_Y[direction];
        if (newx >= 0 && newx < layer.layer.getWidth() && newy >= 0 && newy < layer.layer.getHeight()) {
            if (layer.layer.getTile(newx,newy).isWalkable(this.entity)) {
                entity.setPosition(newx, newy);
                state = State.WALKING;
                walkedSinceLastTile = 0;
            }
        }
    }

    public void setController(EntityController controller) {
        this.controller = controller;
    }

}
