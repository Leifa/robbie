package de.leifaktor.robbie.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import de.leifaktor.robbie.RobbieMain;
import de.leifaktor.robbie.data.Position;
import de.leifaktor.robbie.data.RoomLayer;
import de.leifaktor.robbie.data.RoomLayerFactory;
import de.leifaktor.robbie.data.entities.Entity;
import de.leifaktor.robbie.data.tiles.Tile;
import de.leifaktor.robbie.gfx.EntityPaletteRenderer;
import de.leifaktor.robbie.gfx.RoomRenderer;
import de.leifaktor.robbie.gfx.TileGraphics;
import de.leifaktor.robbie.gfx.TilePaletteRenderer;

public class EditorRoomScreen implements Screen, InputProcessor {

    SpriteBatch batch;
    RoomRenderer roomRenderer;
    ShapeRenderer shapeRenderer;
    TilePaletteRenderer tilePaletteRenderer;
    EntityPaletteRenderer entityPaletteRenderer;

    RobbieMain game;
    EditorSelectionData data;

    Tile selectedTileLeft;
    Tile selectedTileRight;
    Entity selectedEntity;
    boolean leftMouseButtonPressed;
    
    int brushSize = 1;

    boolean tilePaletteActive = true;

    final int RENDER_ROOM_X = 0;
    final int RENDER_ROOM_WIDTH = 800;
    final int RENDER_PALETTE_X = 840;
    final int RENDER_PALETTE_Y = 630;
    final int PALETTE_TILESIZE = 32;
    final int PALETTE_TILES_PER_ROW = 10;

    Position mousePosition;
    
    enum State {
        EDITING,
        COPY1,
        COPY2,
        PASTE
    }
    
    int x1, y1; // position beim kopieren merken
    
    private State state = State.EDITING;

    public EditorRoomScreen(RobbieMain game, EditorSelectionData data) {
        this.game = game;
        this.data = data;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        roomRenderer = new RoomRenderer(16);
        roomRenderer.setRoom(data.room);
        roomRenderer.setWidth(RENDER_ROOM_WIDTH);
        roomRenderer.setGrayLayers(false);
        tilePaletteRenderer = new TilePaletteRenderer();
        tilePaletteRenderer.setTileSize(PALETTE_TILESIZE);
        tilePaletteRenderer.setTilesPerRow(PALETTE_TILES_PER_ROW);
        tilePaletteRenderer.setPosition(RENDER_PALETTE_X, RENDER_PALETTE_Y);
        entityPaletteRenderer = new EntityPaletteRenderer();
        entityPaletteRenderer.setTileSize(PALETTE_TILESIZE);
        entityPaletteRenderer.setTilesPerRow(PALETTE_TILES_PER_ROW);
        entityPaletteRenderer.setPosition(RENDER_PALETTE_X, RENDER_PALETTE_Y);
        shapeRenderer = new ShapeRenderer();

        setActiveLayerIndex(data.room.getLayers().size()-1);

        Gdx.input.setInputProcessor(this);
    }

    private void setActiveLayerIndex(int index) {
        roomRenderer.setLayer(index);
        data.layer = data.room.getLayers().get(index);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
              
        batch.begin();
        
        if (data.room != null) {
            roomRenderer.render(batch);
        }
        if (tilePaletteActive) {
            tilePaletteRenderer.render(batch);
        } else {
            entityPaletteRenderer.render(batch);
        }
        game.font.draw(batch, "ESC: back", 30, 785);
        game.font.draw(batch, "L: new layer above", 230, 785);
        game.font.draw(batch, "K: new layer below", 230, 765);
        game.font.draw(batch, "D: delete layer", 230, 745);
        game.font.draw(batch, "PAGE: choose layer", 430, 785);
        game.font.draw(batch, "C: copy", 430, 765);
        game.font.draw(batch, "V: paste", 430, 745);
        game.font.draw(batch, "T: transparency", 630, 785);
        game.font.draw(batch, "S: set start", 630, 765);
        game.font.draw(batch, "P: switch palette", 830, 785);
        game.font.draw(batch, "B: brush size = " + ((2*brushSize)-1), 830, 765);
        game.font.draw(batch,
                "Layer " + 
                        (data.room.getLayers().indexOf(data.layer)+1) + 
                        " / " + 
                        data.room.getLayers().size(),
                        30,
                        700);
        if (mousePosition != null) {
            game.font.draw(batch,
                    "(" + mousePosition.x + ", " + mousePosition.y + ") : "
                    + data.layer.getTiles()[mousePosition.x + data.room.getWidth()*mousePosition.y],
                    30,
                    680);
        }
        if (selectedTileLeft != null) {
            batch.draw(TileGraphics.getTexture(selectedTileLeft), 900, 650, 64, 64);
        }
        if (selectedTileRight != null) {
            batch.draw(TileGraphics.getTexture(selectedTileRight), 970, 650, 64, 64);
        }        
        batch.end();
        shapeRenderer.begin(ShapeType.Line);
        // Line on top
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.line(0, 720, Gdx.graphics.getWidth(), 720);        
        // Frame around selection when copying
        if (state == State.COPY2) {
            if (mousePosition != null) {
                int x1 = this.x1;
                int y1 = this.y1;
                int x2 = mousePosition.x;
                int y2 = mousePosition.y;
                if (x1 > x2) {int t=x2; x2=x1; x1=t;}
                if (y2 > y1) {int t=y1; y1=y2; y2=t;}
                System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
                y1 = data.room.getHeight() - y1 - 1;
                y2 = data.room.getHeight() - y2 - 1;
                float tilesize = roomRenderer.getTilesize();
                float w = (x2-x1+1)*tilesize;
                float h = (y2-y1+1)*tilesize;
                shapeRenderer.setColor(0,1,0,1);
                shapeRenderer.rect(x1*tilesize, y1*tilesize, w,h);
                shapeRenderer.rect(x1*tilesize+1, y1*tilesize+1, w-2,h-2);
            }
        }
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {}

    @Override
    public boolean keyDown(int keycode) {
        int index;
        RoomLayer newLayer;
        switch (keycode) {
        case Keys.ESCAPE:
            game.setScreen(new EditorFloorScreen(game, data));
            break;
        case Keys.L:
            index = data.room.getLayers().indexOf(data.layer);
            newLayer = RoomLayerFactory.transparentLayer(data.room.getWidth(), data.room.getHeight());
            data.room.getLayers().add(index+1, newLayer);
            setActiveLayerIndex(index+1);
            break;
        case Keys.K:
            index = data.room.getLayers().indexOf(data.layer);
            newLayer = RoomLayerFactory.transparentLayer(data.room.getWidth(), data.room.getHeight());
            data.room.getLayers().add(index, newLayer);
            setActiveLayerIndex(index);
            break;
        case Keys.D:
            index = data.room.getLayers().indexOf(data.layer);
            data.room.getLayers().remove(data.layer);
            if (data.room.getLayers().size() == 0) {
                data.room.getLayers().add(RoomLayerFactory.emptyLayer(data.room.getWidth(), data.room.getHeight()));
            }
            if (index > 0) index--;
            setActiveLayerIndex(index);
        case Keys.PAGE_UP:
            index = data.room.getLayers().indexOf(data.layer);
            if (index < data.room.getLayers().size() - 1) setActiveLayerIndex(index+1);
            break;
        case Keys.PAGE_DOWN:
            index = data.room.getLayers().indexOf(data.layer);
            if (index > 0) setActiveLayerIndex(index-1);
            break;
        case Keys.T:
            roomRenderer.setGrayLayers(!roomRenderer.getGrayLayers());
            break;
        case Keys.P:
            tilePaletteActive = !tilePaletteActive;
            break;
        case Keys.B:
            brushSize++;
            if (brushSize == 4) brushSize = 1;
            break;
        case Keys.C:
            state = State.COPY1;
            break;
        case Keys.V:
            if (data.area != null) state = State.PASTE;
            break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        mousePosition = roomRenderer.getClickPosition(screenX, Gdx.graphics.getHeight() - screenY);
        return true;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Buttons.LEFT) leftMouseButtonPressed = true;
        if (button == Buttons.RIGHT) leftMouseButtonPressed = false;                
        processClickAt(screenX, screenY);
        return true;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        processClickAt(screenX, screenY);
        return true;
    }

    public void processClickAt(int screenX, int screenY) {
        Position p = roomRenderer.getClickPosition(screenX, Gdx.graphics.getHeight() - screenY);
        switch (state) {
        case EDITING:
            if (screenX >= RENDER_PALETTE_X) { // PALETTE GEKLICKT
                if (tilePaletteActive) {
                    if (leftMouseButtonPressed) {
                        selectedTileLeft = tilePaletteRenderer.getTile(screenX, Gdx.graphics.getHeight() - screenY);
                    } else {
                        selectedTileRight = tilePaletteRenderer.getTile(screenX, Gdx.graphics.getHeight() - screenY);
                    }                        
                } else {
                    selectedEntity = entityPaletteRenderer.getEntity(screenX, Gdx.graphics.getHeight() - screenY);
                }
            } else { // RAUM GEKLICKT            
                if (p != null) {
                    if (tilePaletteActive) {
                        for (int i = p.x - brushSize + 1; i <= p.x + brushSize - 1; i++) {
                            for (int j = p.y - brushSize + 1; j <= p.y + brushSize - 1; j++) {
                                if (i >= 0 && i < data.layer.getWidth() && j >= 0 && j < data.layer.getHeight()) {
                                    if(leftMouseButtonPressed) {
                                        data.layer.setTile(i, j, selectedTileLeft);
                                    } else {
                                        data.layer.setTile(i, j, selectedTileRight);
                                    }                                        
                                }                                    
                            }
                        }                            
                    } else {
                        data.layer.clearEntitiesAt(p.x, p.y);
                        if (selectedEntity != null) {
                            Entity e = selectedEntity.clone();
                            e.setPosition(p.x, p.y);
                            data.layer.getEntities().add(e);                          
                        }
                    }
                }
            }
            break;
        case COPY1:            
            if (p != null) {
                x1 = p.x;
                y1 = p.y;
                state = State.COPY2;
            }
            break;
        case COPY2:            
            if (p != null) {
                data.area = new Area(data.layer, x1,y1,p.x,p.y);
                state = State.EDITING;
            }
            break;
        case PASTE:
            data.area.paste(data.layer, p.x, p.y);
            state = State.EDITING;
            break;
        }
    }
    
}
