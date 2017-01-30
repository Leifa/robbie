package de.leifaktor.robbie.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.leifaktor.robbie.RobbieMain;
import de.leifaktor.robbie.gfx.Misc;

public class EditorFloorsScreen implements Screen {

    int floorSelector;

    EditorSelectionData data;    
    RobbieMain game;
    
    SpriteBatch batch;
    
    public EditorFloorsScreen(RobbieMain game, EditorSelectionData data) {
        this.game = game;
        this.data = data;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            game.setScreen(new EditorScreen(game, data));
            return;
        }
        if (data.episode.getFloors().size() == 0) {
            Misc.drawStrings(game.font, batch, 300,600, "Kein Floor vorhanden",
                    "N : Neuer Floor");
            if (Gdx.input.isKeyJustPressed(Keys.N)) {
                data.floor = data.episode.addFloor();
                game.setScreen(new EditorFloorScreen(game, data));
            }
        } else {
            Misc.drawStrings(game.font, batch, 300,600, "Wähle Floor: " + (floorSelector+1) + " / " + data.episode.getFloors().size(),
                    "RETURN : Auswahl",
                    "N : Neuer Floor");
            if (Gdx.input.isKeyJustPressed(Keys.UP)) {
                if (floorSelector < data.episode.getFloors().size()-1) floorSelector++;
            } else if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
                if (floorSelector > 0) floorSelector--;
            } else if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
                data.floor = data.episode.getFloors().get(floorSelector);
                game.setScreen(new EditorFloorScreen(game, data));
            } else if (Gdx.input.isKeyJustPressed(Keys.N)) {
                data.floor = data.episode.addFloor();
                game.setScreen(new EditorFloorScreen(game, data));
            }
        }
        batch.end();
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

}
