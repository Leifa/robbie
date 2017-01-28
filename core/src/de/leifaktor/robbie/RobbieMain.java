package de.leifaktor.robbie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.leifaktor.robbie.gfx.TileGraphics;
import de.leifaktor.robbie.gfx.Tileset;
import de.leifaktor.robbie.screens.EditorScreen;

public class RobbieMain extends Game {

	public BitmapFont font;
	
	@Override
	public void create () {
        this.setScreen(new EditorScreen(this));
        font = new BitmapFont();
        Tileset tileset = new Tileset("tileset16.png", 16);
        TileGraphics.createTileGraphics(tileset);
	}

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
	}
}
