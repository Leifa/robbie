package de.leifaktor.robbie.data.tiles;

public class TunnelEntrance extends Tile {
    
    int tunnelOpening;
    
    public TunnelEntrance(int id, int tunnelOpening) {
        super(id);
        this.tunnelOpening = tunnelOpening;
    }
    
    public int getTunnelDirection() {
        return tunnelOpening;
    }

}
