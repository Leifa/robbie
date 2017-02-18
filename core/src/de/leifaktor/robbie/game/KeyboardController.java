package de.leifaktor.robbie.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class KeyboardController extends EntityController {

    public KeyboardController(RuntimeEntity entity) {
        super(entity);
    }
    
    public int getDirection() {
        if (Gdx.input.isKeyPressed(Keys.UP)) return 0;
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) return 1;
        if (Gdx.input.isKeyPressed(Keys.DOWN)) return 2;
        if (Gdx.input.isKeyPressed(Keys.LEFT)) return 3;
        if (Gdx.input.isKeyPressed(Keys.PAGE_UP)) return 4;
        if (Gdx.input.isKeyPressed(Keys.PAGE_DOWN)) return 5;
        if (Gdx.input.isKeyPressed(Keys.END)) return 6;
        if (Gdx.input.isKeyPressed(Keys.HOME)) return 7;
        return -1;
    }

}
