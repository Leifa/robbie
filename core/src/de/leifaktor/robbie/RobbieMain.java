package de.leifaktor.robbie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.leifaktor.robbie.gfx.Tileset;
import de.leifaktor.robbie.screens.EditorScreen;

public class RobbieMain extends Game {

	public BitmapFont font;
	
	int frames;
	float accu;
	
	@Override
	public void create () {
        this.setScreen(new EditorScreen(this));
        font = new BitmapFont();
        new Tileset("tileset16");
        frames = 0;
        accu = 0;
	}

	@Override
	public void render () {	    
	    super.render();
	    
	    // FPS Logger
	    frames++;
	    accu += Gdx.graphics.getDeltaTime();
        while (accu > 1) {
            accu--;
            System.out.println("FPS: " + frames);
            frames = 0;
        }
	}
	
	@Override
	public void dispose () {
	}
}
