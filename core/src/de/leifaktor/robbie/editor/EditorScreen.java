package de.leifaktor.robbie.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.leifaktor.robbie.RobbieMain;
import de.leifaktor.robbie.data.Episode;
import de.leifaktor.robbie.game.GameScreen;
import de.leifaktor.robbie.gfx.Misc;

public class EditorScreen extends ScreenAdapter implements InputProcessor {

    SpriteBatch batch;

    EditorData data;

    RobbieMain game;

    int widthSelector = 40;
    int heightSelector = 28;
    boolean selectWidth = true;

    public EditorScreen(RobbieMain game) {
        this.game = game;
        data = new EditorData();
        if (data.autoBackup == null) data.autoBackup = new AutoBackup(data);
    }

    public EditorScreen(RobbieMain game, EditorData data) {
        this.game = game;
        this.data = data;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        data.autoBackup.update(delta);
        
        Gdx.gl.glClearColor(0.4f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.font.setColor(1, 1, 1, 1);
        batch.begin();
        if (data.episode == null) {
            Misc.drawStrings(game.font, batch, 300,600,
                    "Neue Episode erstellen:",
                    "Raumbreite/höhe: " + widthSelector + " / " + heightSelector,
                    "N : Episode erstellen",
                    "L : Episode laden",
                    "B : Letztes Backup laden");
        } else {
            Misc.drawStrings(game.font, batch, 300,600,
                    "Episode: " + data.episode.getName(),
                    "Raumbreite: " + data.episode.getRoomWidth(),
                    "Raumhöhe: " + data.episode.getRoomHeight(),
                    "F : Floors ansehen",
                    "S : Episode speichern",
                    "P : Episode abspielen",
                    "L : Episode Laden",
                    "B : Letztes Backup laden",
                    "C : Episode schließen");
        }
        batch.end();
    }
    
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
    
    
    private void showLoadingDialog() {
        LoadDialog listener = new LoadDialog(data);
        Gdx.input.getTextInput(listener, "Load from file", "", "Enter filename");
    }
    
    private void showSavingDialog() {
        SaveDialog listener = new SaveDialog(data.episode);
        Gdx.input.getTextInput(listener, "Save as...", "", "Enter filename");
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.L) {
            showLoadingDialog();
        } else if (keycode == Keys.B) {
            data.autoBackup.loadLatestBackup();
        }
        
        if (data.episode == null) {
            if (selectWidth) {
                if (keycode == Keys.UP && widthSelector < 48) widthSelector++;
                if (keycode == Keys.DOWN && widthSelector > 10) widthSelector--;
            } else {
                if (keycode == Keys.UP && heightSelector < 36) heightSelector++;
                if (keycode == Keys.DOWN && heightSelector > 8) heightSelector--;               
            }
            if (keycode == Keys.RIGHT && selectWidth) selectWidth = false;
            if (keycode == Keys.LEFT && !selectWidth) selectWidth = true;
            if (keycode == Keys.N) {
                data.episode = new Episode(widthSelector, heightSelector);
            }
        } else {
            switch (keycode) {
            case Keys.F: game.setScreen(new EditorFloorScreen(game, data)); break;
            case Keys.S: showSavingDialog(); break;
            case Keys.P: game.setScreen(new GameScreen(game, data.episode)); break;
            case Keys.C: data.reset(); break;
            }
        }
        return false;
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
