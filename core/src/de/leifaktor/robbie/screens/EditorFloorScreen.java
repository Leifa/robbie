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
import de.leifaktor.robbie.data.Room;
import de.leifaktor.robbie.data.RoomFactory;
import de.leifaktor.robbie.gfx.RoomRenderer;

public class EditorFloorScreen implements Screen, InputProcessor {

    // lower left corner of the lower left room (drawing coordinates)
    int x;
    int y;

    EditorSelectionData data;
    RobbieMain game;
    
    SpriteBatch batch;
    ShapeRenderer shaper;
    RoomRenderer roomRenderer;

    public EditorFloorScreen(RobbieMain game, EditorSelectionData data) {
        this.game = game;
        this.data = data;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shaper = new ShapeRenderer();
        roomRenderer = new RoomRenderer(16);
        roomRenderer.setGrayLayers(false);
        if (data.roomRectHeight == 0) {
            setRectSize(90);
        } else {
            setRectSize(data.roomRectHeight);
        }
        if (data.roomSelectorX >= data.floor.getWidth() || data.roomSelectorY >= data.floor.getHeight()) {
            data.roomSelectorX = 0;
            data.roomSelectorY = 0;
        }
        Gdx.input.setInputProcessor(this);
    }
    
    private void setRectSize(int height) {
        data.roomRectHeight = height;
        data.roomRectWidth = (int) (data.episode.getRoomWidth() / ((float) data.episode.getRoomHeight()) * data.roomRectHeight);
        roomRenderer.setScale(data.roomRectHeight / (float) (16.0*data.episode.getRoomHeight()));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        
        
        // Existierende Räume zeichnen
        batch.begin();
        if (data.floor != null) {
            for (int i = 0; i < data.floor.getWidth(); i++) {
                for (int j = 0; j < data.floor.getHeight(); j++) {
                    Room r = data.floor.getRoom(i, j);
                    if (r != null) {
                        roomRenderer.setRoom(r);
                        roomRenderer.setPosition(x+i*data.roomRectWidth, y+(data.floor.getHeight()-1-j)*data.roomRectHeight);
                        roomRenderer.setLayer(r.getLayers().size()-1);
                        roomRenderer.render(batch);
                    }
                }
            }
        }
        batch.end();
        // Rechtecke für nicht existierende Räume zeichnen und Auswahlbox zeichnen
        shaper.begin(ShapeType.Line);
        shaper.setColor(1, 0, 0, 1);
        if (data.floor != null) {
            for (int i = 0; i < data.floor.getWidth(); i++) {
                for (int j = 0; j < data.floor.getHeight(); j++) {
                    Room r = data.floor.getRoom(i, j);
                    if (r == null) {
                        shaper.setColor(0.7f, 0.7f, 0.7f, 1);
                        shaper.rect(x+i*data.roomRectWidth, y+(data.floor.getHeight()-1-j)*data.roomRectHeight, data.roomRectWidth-1, data.roomRectHeight-1);
                    }
                    if (i == data.roomSelectorX && j == data.roomSelectorY) {
                        shaper.setColor(0, 1, 0, 0.2f);
                        shaper.rect(x+i*data.roomRectWidth, y+(data.floor.getHeight()-1-j)*data.roomRectHeight, data.roomRectWidth-1, data.roomRectHeight-1);
                        shaper.rect(x+i*data.roomRectWidth+1, y+(data.floor.getHeight()-1-j)*data.roomRectHeight+1, data.roomRectWidth-3, data.roomRectHeight-3);
                    }                    
                }
            }
        }
        shaper.end();


    }
    
    /**
     * Makes sure that the selected room is visible on the screen, in case the floor
     * is too big to show all rooms
     */
    
    private void updateOffset() {
        if (x+(data.roomSelectorX+1)*data.roomRectWidth > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - (data.roomSelectorX+1)*data.roomRectWidth;
        }
        if (x+(data.roomSelectorX)*data.roomRectWidth < 0) {
            x = - (data.roomSelectorX)*data.roomRectWidth;
        }
        if (y+(data.floor.getHeight()-1-data.roomSelectorY)*data.roomRectHeight < 0) {
            y = - (data.floor.getHeight()-1-data.roomSelectorY)*data.roomRectHeight;
        }
        if (y+(data.floor.getHeight()-data.roomSelectorY)*data.roomRectHeight > Gdx.graphics.getHeight()) {
            y = Gdx.graphics.getHeight() - (data.floor.getHeight()-data.roomSelectorY)*data.roomRectHeight;
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {Gdx.input.setInputProcessor(null);}

    @Override
    public void dispose() {}

    @Override
    public boolean keyDown(int keycode) {
        int currentFloorIndex = data.episode.getFloors().indexOf(data.floor);
        switch (keycode) {
        case Keys.ESCAPE:
            game.setScreen(new EditorFloorsScreen(game, data));
            break;
        case Keys.ENTER:
            if (data.floor.getRoom(data.roomSelectorX, data.roomSelectorY) == null) {
                Room r = RoomFactory.wallRoom(data.episode.getRoomWidth(), data.episode.getRoomHeight());
                data.floor.setRoom(data.roomSelectorX, data.roomSelectorY, r);                
            }
            data.room = data.floor.getRoom(data.roomSelectorX, data.roomSelectorY);
            game.setScreen(new EditorRoomScreen(game, data));
            break;
        case Keys.DOWN:
            if (data.roomSelectorY == data.floor.getHeight()-1) data.floor.expandSouth();
            data.roomSelectorY++;
            updateOffset();
            break;
        case Keys.UP:
            if (data.roomSelectorY == 0) {
                data.floor.expandNorth();
                data.roomSelectorY++;
            }
            data.roomSelectorY--;
            updateOffset();
            break;
        case Keys.LEFT:
            if (data.roomSelectorX == 0) {
                data.floor.expandWest();
                data.roomSelectorX++;
            }
            data.roomSelectorX--;
            updateOffset();
            break;
        case Keys.RIGHT:
            if (data.roomSelectorX == data.floor.getWidth() - 1) data.floor.expandEast();
            data.roomSelectorX++;
            updateOffset();
            break;
        case Keys.MINUS:
            if (data.roomRectHeight > 20) setRectSize(data.roomRectHeight - 10);
            updateOffset();
            break;
        case Keys.PLUS:
            if (data.roomRectHeight < 200) setRectSize(data.roomRectHeight + 10);
            updateOffset();
            break;
        case Keys.PAGE_UP:
            int numberOfFloors = data.episode.getFloors().size();
            if (currentFloorIndex < numberOfFloors - 1) {
                data.floor = data.episode.getFloors().get(currentFloorIndex + 1); 
            }
            break;
        case Keys.PAGE_DOWN:            
            if (currentFloorIndex > 0) {
                data.floor = data.episode.getFloors().get(currentFloorIndex - 1); 
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
