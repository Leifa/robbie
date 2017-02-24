package de.leifaktor.robbie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.leifaktor.robbie.editor.EditorScreen;
import de.leifaktor.robbie.gfx.Tileset;

public class RobbieMain extends Game {

	public BitmapFont font;	
	FPSLogger fpsLogger;
	
	@Override
	public void create () {        
        font = new BitmapFont();
        new Tileset("tileset16");
        this.setScreen(new EditorScreen(this));
        fpsLogger = new FPSLogger();
	}

	@Override
	public void render () {
	    super.render();
	    fpsLogger.log();     
	}
	
	@Override
	public void dispose () {
	}
}
