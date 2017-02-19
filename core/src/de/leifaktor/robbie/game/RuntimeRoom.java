package de.leifaktor.robbie.game;

import de.leifaktor.robbie.data.Position;
import de.leifaktor.robbie.data.Room;

public class RuntimeRoom {
    
    public Room room;
    public RuntimeLayer[] layers;
    int playerLayer;

    public RuntimeRoom(Room room) {
        this.room = room;        
        layers = new RuntimeLayer[room.getLayers().size()];
        for (int i = 0; i < layers.length; i++) {
            RuntimeLayer runtimeLayer = new RuntimeLayer(room.getLayers().get(i));
            layers[i] = runtimeLayer;
        }
    }
    
    public void setPlayer(RuntimeEntity player, Position pos) {
        this.playerLayer = pos.layer;
        layers[playerLayer].addPlayer(player);
    }
    
    public void continueMovement() {
        for (int i = 0; i < layers.length; i++) layers[i].continueMovement();
    }
    
    public void update() {
        for (int i = 0; i < layers.length; i++) layers[i].update();
    }
    
}
