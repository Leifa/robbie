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
import de.leifaktor.robbie.data.RoomManager;
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

    RoomManager rooms; // A reference to the room manager, just for convenience.

    int minx;  // the lowest x coordinate to draw
    int miny;  // the lowest y coordinate to draw
    int maxx;  // the highest x coordinate to draw
    int maxy;  // the highest y coordinate to draw
    int width;  // the number of columns to draw
    int height;  // the number of rows to draw

    public EditorFloorScreen(RobbieMain game, EditorSelectionData data) {
        this.game = game;
        this.data = data;
    }

    @Override
    public void show() {
        rooms = data.episode.getRooms();
        batch = new SpriteBatch();
        shaper = new ShapeRenderer();
        roomRenderer = new RoomRenderer(16);
        roomRenderer.setGrayLayers(false);
        if (data.roomRectHeight == 0) {
            setRectSize(90);
        } else {
            setRectSize(data.roomRectHeight);
        }

        // If roomSelectors are out of bounds for this floor, adjust.
        if (rooms.doesFloorExist(data.floor)) {
            if (data.roomSelectorX > rooms.getMaxX(data.floor) || data.roomSelectorX < rooms.getMinX(data.floor)) {
                data.roomSelectorX = rooms.getMinX(data.floor);
            }
            if (data.roomSelectorY > rooms.getMaxY(data.floor) || data.roomSelectorY < rooms.getMinY(data.floor)) {
                data.roomSelectorY = rooms.getMinY(data.floor);
            }
        } else {
            data.roomSelectorX = 0;
            data.roomSelectorY = 0;
        }

        calculateRowsAndColumns();
        updateOffset();
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
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Room r = rooms.getRoom(data.floor, i + minx, j+miny);
                if (r != null) {
                    roomRenderer.setRoom(r);
                    roomRenderer.setPosition(x+i*data.roomRectWidth, y+j*data.roomRectHeight);
                    roomRenderer.setLayer(r.getLayers().size()-1);
                    roomRenderer.render(batch);
                }
                game.font.setColor(1, 0.4f, 0.4f, 1);
                game.font.draw(batch, (i+minx) + " / " + (j+miny), x+i*data.roomRectWidth+3, y+j*data.roomRectHeight+17);
            }
        }
        batch.end();

        // Rechtecke für nicht existierende Räume zeichnen und Auswahlbox zeichnen
        shaper.begin(ShapeType.Line);
        shaper.setColor(1, 0, 0, 1);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Room r = rooms.getRoom(data.floor, i+minx, j+miny);
                if (r == null) {
                    shaper.setColor(0.7f, 0.7f, 0.7f, 1);
                    shaper.rect(x+i*data.roomRectWidth, y+j*data.roomRectHeight, data.roomRectWidth-1, data.roomRectHeight-1);
                }
                if (i+minx == data.roomSelectorX && j+miny == data.roomSelectorY) {
                    shaper.setColor(0, 1, 0, 0.2f);
                    shaper.rect(x+i*data.roomRectWidth, y+j*data.roomRectHeight, data.roomRectWidth-1, data.roomRectHeight-1);
                    shaper.rect(x+i*data.roomRectWidth+1, y+j*data.roomRectHeight+1, data.roomRectWidth-3, data.roomRectHeight-3);
                }                    
            }
        }
        shaper.end();

    }

    /**
     * Finds out what part of the floor has to be drawn, so that all rooms are visible as well as
     * the selector.
     */

    private void calculateRowsAndColumns() {
        if (rooms.doesFloorExist(data.floor)) {
            minx = rooms.getMinX(data.floor);
            miny = rooms.getMinY(data.floor);
            maxx = rooms.getMaxX(data.floor);
            maxy = rooms.getMaxY(data.floor);
        } else {
            minx = 0;
            miny = 0;
            maxx = 0;
            maxy = 0;
        }
        if (data.roomSelectorX < minx) minx = data.roomSelectorX;
        if (data.roomSelectorX > maxx) maxx = data.roomSelectorX;
        if (data.roomSelectorY < miny) miny = data.roomSelectorY;
        if (data.roomSelectorY > maxy) maxy = data.roomSelectorY;
        width = maxx - minx + 1;
        height = maxy - miny + 1;
    }

    /**
     * Makes sure that the selected room is visible on the screen, in case the floor
     * is too big to show all rooms
     */

    private void updateOffset() {
        if (x + (data.roomSelectorX-minx+1)*data.roomRectWidth > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - (data.roomSelectorX-minx+1)*data.roomRectWidth;
        }
        if (x + (data.roomSelectorX-minx) * data.roomRectWidth < 0) {
            x = - (data.roomSelectorX)*data.roomRectWidth;
        }
        if (y + (data.roomSelectorY-miny) * data.roomRectHeight < 0) {
            y = - (data.roomSelectorY-miny) * data.roomRectHeight;
        }
        if (y + (data.roomSelectorY-miny+1)*data.roomRectHeight > Gdx.graphics.getHeight()) {
            y = Gdx.graphics.getHeight() - (data.roomSelectorY-miny+1)*data.roomRectHeight;
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
        switch (keycode) {
        case Keys.ESCAPE:
            game.setScreen(new EditorScreen(game, data));
            break;
        case Keys.ENTER:
            if (rooms.getRoom(data.floor, data.roomSelectorX, data.roomSelectorY) == null) {
                Room r = RoomFactory.wallRoom(data.episode.getRoomWidth(), data.episode.getRoomHeight());
                rooms.setRoom(data.floor, data.roomSelectorX, data.roomSelectorY, r);                
            }
            data.room = rooms.getRoom(data.floor, data.roomSelectorX, data.roomSelectorY);
            game.setScreen(new EditorRoomScreen(game, data));
            break;
        case Keys.DOWN:
            data.roomSelectorY--;
            calculateRowsAndColumns();
            updateOffset();
            break;
        case Keys.UP:
            data.roomSelectorY++;
            calculateRowsAndColumns();
            updateOffset();            
            break;
        case Keys.LEFT:
            data.roomSelectorX--;
            calculateRowsAndColumns();
            updateOffset();
            break;
        case Keys.RIGHT:
            data.roomSelectorX++;
            calculateRowsAndColumns();
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
            data.floor++;
            calculateRowsAndColumns();
            updateOffset();
            break;
        case Keys.PAGE_DOWN:            
            data.floor--;
            calculateRowsAndColumns();
            updateOffset();
            break;
        case Keys.FORWARD_DEL:
            rooms.setRoom(data.floor, data.roomSelectorX, data.roomSelectorY, null);
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
