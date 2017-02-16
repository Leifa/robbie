package de.leifaktor.robbie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.leifaktor.robbie.editor.EditorScreen;
import de.leifaktor.robbie.gfx.Tileset;

public class RobbieMain extends Game {

	public BitmapFont font;
	
	int frames;
	float accu;
	
	@Override
	public void create () {        
        font = new BitmapFont();
        new Tileset("tileset16");
        frames = 0;
        accu = 0;
        this.setScreen(new EditorScreen(this));
	}

	@Override
	public void render () {	    
	    super.render();
	    
	    // FPS Logger
	    frames++;
	    accu += Gdx.graphics.getDeltaTime();
        while (accu > 1) {
            accu--;
            //Gdx.app.log("RobbieMain", "FPS: " + frames);
            frames = 0;
        }        
	}
	
	@Override
	public void dispose () {
	}
}
