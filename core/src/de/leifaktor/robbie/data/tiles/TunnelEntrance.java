package de.leifaktor.robbie.data.tiles;

public class TunnelEntrance extends Tile {
    
    int tunnelOpening;
    
    public TunnelEntrance() {}
    
    public TunnelEntrance(int tunnelOpening) {
        this.tunnelOpening = tunnelOpening;
    }
    
    public int getTunnelDirection() {
        return tunnelOpening;
    }

}
