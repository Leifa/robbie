package de.leifaktor.robbie.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.leifaktor.robbie.RobbieMain;
import de.leifaktor.robbie.data.Episode;
import de.leifaktor.robbie.gfx.Misc;

public class EditorScreen extends ScreenAdapter {

    SpriteBatch batch;

    EditorSelectionData data;

    RobbieMain game;

    int widthSelector = 40;
    int heightSelector = 28;
    boolean selectWidth = true;

    public EditorScreen(RobbieMain game) {
        this.game = game;
        data = new EditorSelectionData();
    }

    public EditorScreen(RobbieMain game, EditorSelectionData data) {
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

        game.font.setColor(1, 1, 1, 1);
        batch.begin();
        if (data.episode == null) {
            if (selectWidth) {
                if (Gdx.input.isKeyJustPressed(Keys.UP) && widthSelector < 48) widthSelector++;
                if (Gdx.input.isKeyJustPressed(Keys.DOWN) && widthSelector > 10) widthSelector--;
            } else {
                if (Gdx.input.isKeyJustPressed(Keys.UP) && heightSelector < 36) heightSelector++;
                if (Gdx.input.isKeyJustPressed(Keys.DOWN) && heightSelector > 8) heightSelector--;               
            }
            if (Gdx.input.isKeyJustPressed(Keys.RIGHT) && selectWidth) selectWidth = false;
            if (Gdx.input.isKeyJustPressed(Keys.LEFT) && !selectWidth) selectWidth = true;

            Misc.drawStrings(game.font, batch, 300,600,
                    "Neue Episode erstellen:",
                    "Raumbreite/höhe: " + widthSelector + " / " + heightSelector,
                    "N : Episode erstellen",
                    "L : Episode Laden");
            if (Gdx.input.isKeyJustPressed(Keys.N)) {
                data.episode = new Episode(widthSelector, heightSelector);
            } else if (Gdx.input.isKeyJustPressed(Keys.L)) {
                showLoadingDialog();
            }
        } else {
            Misc.drawStrings(game.font, batch, 300,600,
                    "Episode: " + data.episode.getName(),
                    "Raumbreite: " + data.episode.getRoomWidth(),
                    "Raumhöhe: " + data.episode.getRoomHeight(),
                    "F : Floors ansehen",
                    "S : Episode speichern",
                    "L : Episode Laden");
            if (Gdx.input.isKeyJustPressed(Keys.F)) {
                game.setScreen(new EditorFloorScreen(game, data));
            } else if (Gdx.input.isKeyJustPressed(Keys.S)) {
                showSavingDialog();
            } else if (Gdx.input.isKeyJustPressed(Keys.L)) {
                showLoadingDialog();
            }
        }
        batch.end();
    }
    
    private void showLoadingDialog() {
        LoadDialog listener = new LoadDialog(data);
        Gdx.input.getTextInput(listener, "Load from file", "", "Enter filename");
    }
    
    private void showSavingDialog() {
        SaveDialog listener = new SaveDialog(data.episode);
        Gdx.input.getTextInput(listener, "Save as...", "", "Enter filename");
    }

}
