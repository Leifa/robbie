package de.leifaktor.robbie.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.leifaktor.robbie.RobbieMain;
import de.leifaktor.robbie.gfx.Misc;

public class EditorFloorsScreen implements Screen, InputProcessor {

    EditorSelectionData data;    
    RobbieMain game;

    SpriteBatch batch;
    ShapeRenderer shaper;

    public EditorFloorsScreen(RobbieMain game, EditorSelectionData data) {
        this.game = game;
        this.data = data;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shaper = new ShapeRenderer();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);        

        if (data.episode.getFloors().size() == 0) {
            batch.begin();
            Misc.drawStrings(game.font, batch, 300,600, "Kein Floor vorhanden",
                    "N : Neuer Floor");    
            batch.end();
        } else {
            batch.begin();
            Misc.drawStrings(game.font, batch, 300,600, "Wähle Floor: ",
                    "RETURN : Auswahl",
                    "N : Neuer Floor");
            for (int i = 0; i < data.episode.getFloors().size(); i++) {
                game.font.draw(batch, "Floor " + i, 500, 300+30*i);
            }
            batch.end();
            shaper.begin(ShapeType.Line);
            shaper.rect(497, 300+30*data.floorSelector-17,70,20);
            shaper.end();
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
        case Keys.ESCAPE:
            game.setScreen(new EditorScreen(game, data));
            break;
        case Keys.N:
            data.floor = data.episode.addFloor();
            break;
        case Keys.D:
            if (data.floorSelector < data.episode.getFloors().size()) {
                data.episode.getFloors().remove(data.floorSelector);
                if (data.floorSelector > 0 && data.floorSelector >= data.episode.getFloors().size()) {
                    data.floorSelector--;
                }
            }
            break;
        case Keys.UP:
            if (data.floorSelector < data.episode.getFloors().size()-1) data.floorSelector++;
            break;
        case Keys.DOWN:
            if (data.floorSelector > 0) data.floorSelector--;
            break;
        case Keys.ENTER:
            if (data.floorSelector < data.episode.getFloors().size()) {
                data.floor = data.episode.getFloors().get(data.floorSelector);
                game.setScreen(new EditorFloorScreen(game, data));
            }
            break;
        }    
        return true;
    }


@Override
public boolean keyUp(int keycode) {
    // TODO Auto-generated method stub
    return false;
}

@Override
public boolean keyTyped(char character) {
    // TODO Auto-generated method stub
    return false;
}

@Override
public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    // TODO Auto-generated method stub
    return false;
}

@Override
public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    // TODO Auto-generated method stub
    return false;
}

@Override
public boolean touchDragged(int screenX, int screenY, int pointer) {
    // TODO Auto-generated method stub
    return false;
}

@Override
public boolean mouseMoved(int screenX, int screenY) {
    // TODO Auto-generated method stub
    return false;
}

@Override
public boolean scrolled(int amount) {
    // TODO Auto-generated method stub
    return false;
}

}
