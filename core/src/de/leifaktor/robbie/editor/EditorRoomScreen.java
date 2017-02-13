package de.leifaktor.robbie.editor;

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
import de.leifaktor.robbie.data.XYPos;
import de.leifaktor.robbie.data.XYZPos;
import de.leifaktor.robbie.data.entities.Entity;
import de.leifaktor.robbie.data.tiles.Tile;
import de.leifaktor.robbie.gfx.EntityPaletteRenderer;
import de.leifaktor.robbie.gfx.RoomRenderer;
import de.leifaktor.robbie.gfx.TilePaletteRenderer;
import de.leifaktor.robbie.gfx.Tileset;

public class EditorRoomScreen implements Screen, InputProcessor {

    SpriteBatch batch;
    RoomRenderer roomRenderer;
    ShapeRenderer shapeRenderer;
    TilePaletteRenderer tilePaletteRenderer;
    EntityPaletteRenderer entityPaletteRenderer;

    RobbieMain game;
    EditorData data;

    Tile selectedTileLeft;
    Tile selectedTileRight;
    Entity selectedEntityLeft;
    Entity selectedEntityRight;
    boolean leftMouseButtonPressed;

    int brushSize = 1;

    boolean tilePaletteActive = true;

    final int RENDER_ROOM_X = 0;
    final int RENDER_ROOM_WIDTH = 800;
    final int RENDER_PALETTE_X = 840;
    final int RENDER_PALETTE_Y = 630;
    final int PALETTE_TILESIZE = 32;
    final int PALETTE_TILES_PER_ROW = 10;

    XYPos mousePosition;

    enum State {
        EDITING,
        COPY1,
        COPY2,
        PASTE,
        SET_START
    }

    int x1, y1; // position beim kopieren merken

    private State state = State.EDITING;

    public EditorRoomScreen(RobbieMain game, EditorData data) {
        this.game = game;
        this.data = data;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        roomRenderer = new RoomRenderer(16);
        roomRenderer.setRoom(data.room);
        roomRenderer.setEpisode(data.episode);
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
        data.layer = index;
    }

    @Override
    public void render(float delta) {
        data.autoBackup.update(delta);
        
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
        game.font.setColor(1, 1, 1, 1);
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
        game.font.draw(batch, "SHIFT: multiple entities", 830, 745);
        game.font.draw(batch,
                "Layer " + 
                        (data.layer+1) + 
                        " / " + 
                        data.room.getLayers().size(),
                        30,
                        700);
        if (mousePosition != null) {
            game.font.draw(batch,
                    "(" + mousePosition.x + ", " + mousePosition.y + ")",
                    30,
                    680);
            game.font.draw(batch,
                    "TILE: "
                            + data.room.getLayers().get(data.layer).getTiles()[mousePosition.x + data.room.getWidth()*mousePosition.y],
                            30,
                            660);
            game.font.draw(batch,
                    "ENTITIES: "
                            + data.room.getLayers().get(data.layer).getEntitiesAt(mousePosition.x, mousePosition.y).toString(),
                            30,
                            640);
        }
        if (tilePaletteActive) {
            if (selectedTileLeft != null) {
                batch.draw(Tileset.getTexture(selectedTileLeft), 900, 650, 64, 64);
            }
            if (selectedTileRight != null) {
                batch.draw(Tileset.getTexture(selectedTileRight), 970, 650, 64, 64);
            }
        } else {
            if (selectedEntityLeft != null) {
                batch.draw(Tileset.getTexture(selectedEntityLeft), 900, 650, 64, 64);
            }
            if (selectedEntityRight != null) {
                batch.draw(Tileset.getTexture(selectedEntityRight), 970, 650, 64, 64);
            }
        }
        batch.end();
        shapeRenderer.begin(ShapeType.Line);
        // Line on top
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.line(0, 720, Gdx.graphics.getWidth(), 720);        
        // Frame around selection when copying
        if (state == State.COPY2) {
            if (mousePosition != null) {
                drawFrameOnRoom(x1, y1, mousePosition.x, mousePosition.y);
            }
        } else if (state == State.PASTE) {
            if (mousePosition != null) {
                drawFrameOnRoom(mousePosition.x,  mousePosition.y, mousePosition.x + data.area.getWidth() - 1, mousePosition.y + data.area.getHeight() - 1);
            }
        }
        shapeRenderer.end();
    }

    private void drawFrameOnRoom(int x1, int y1, int x2, int y2) {
        if (x1 > x2) {int t=x2; x2=x1; x1=t;}
        if (y1 > y2) {int t=y1; y1=y2; y2=t;}
        float tilesize = roomRenderer.getTilesize();
        float w = (x2-x1+1)*tilesize;
        float h = (y2-y1+1)*tilesize;
        shapeRenderer.setColor(0,1,0,1);
        shapeRenderer.rect(x1*tilesize, y1*tilesize, w,h);
        shapeRenderer.rect(x1*tilesize+1, y1*tilesize+1, w-2,h-2);
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
        RoomLayer newLayer;
        switch (keycode) {
        case Keys.ESCAPE:
            game.setScreen(new EditorFloorScreen(game, data));
            break;
        case Keys.L:
            newLayer = RoomLayerFactory.transparentLayer(data.room.getWidth(), data.room.getHeight());
            data.room.getLayers().add(data.layer+1, newLayer);
            setActiveLayerIndex(data.layer+1);
            break;
        case Keys.K:
            newLayer = RoomLayerFactory.transparentLayer(data.room.getWidth(), data.room.getHeight());
            data.room.getLayers().add(data.layer, newLayer);
            setActiveLayerIndex(data.layer);
            break;
        case Keys.D:            
            data.room.getLayers().remove(data.layer);
            if (data.room.getLayers().size() == 0) {
                data.room.getLayers().add(RoomLayerFactory.emptyLayer(data.room.getWidth(), data.room.getHeight()));
            }
            if (data.layer > 0) data.layer--;
            setActiveLayerIndex(data.layer);
        case Keys.PAGE_UP:
            if (data.layer < data.room.getLayers().size() - 1) setActiveLayerIndex(data.layer+1);
            break;
        case Keys.PAGE_DOWN:
            if (data.layer > 0) setActiveLayerIndex(data.layer-1);
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
        case Keys.S:
            state = State.SET_START;
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
        if (tilePaletteActive || !Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) processClickAt(screenX, screenY);
        return true;
    }

    public void processClickAt(int screenX, int screenY) {
        XYPos p = roomRenderer.getClickPosition(screenX, Gdx.graphics.getHeight() - screenY);
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
                    if (leftMouseButtonPressed) {
                        selectedEntityLeft = entityPaletteRenderer.getEntity(screenX, Gdx.graphics.getHeight() - screenY);
                    } else {
                        selectedEntityRight = entityPaletteRenderer.getEntity(screenX, Gdx.graphics.getHeight() - screenY);
                    }
                }
            } else { // RAUM GEKLICKT            
                if (p != null) {
                    if (tilePaletteActive) {
                        for (int i = p.x - brushSize + 1; i <= p.x + brushSize - 1; i++) {
                            for (int j = p.y - brushSize + 1; j <= p.y + brushSize - 1; j++) {
                                if (i >= 0 && i < data.room.getWidth() && j >= 0 && j < data.room.getHeight()) {
                                    if(leftMouseButtonPressed) {
                                        data.room.getLayers().get(data.layer).setTile(i, j, selectedTileLeft);
                                    } else {
                                        data.room.getLayers().get(data.layer).setTile(i, j, selectedTileRight);
                                    }                                        
                                }                                    
                            }
                        }                            
                    } else {
                        Entity selectedEntity = leftMouseButtonPressed ? selectedEntityLeft : selectedEntityRight;
                        if (!Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) data.room.getLayers().get(data.layer).clearEntitiesAt(p.x, p.y);
                        if (selectedEntity != null) {
                            Entity e = selectedEntity.clone();
                            e.setPosition(p.x, p.y);
                            data.room.getLayers().get(data.layer).getEntities().add(e);                          
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
                data.area = new Area(data.room.getLayers().get(data.layer), x1,y1,p.x,p.y);
                state = State.EDITING;
            }
            break;
        case PASTE:
            data.area.paste(data.room.getLayers().get(data.layer), p.x, p.y);
            state = State.EDITING;
            break;
        case SET_START:
            if (p != null) {
                XYZPos roomPosition = new XYZPos(data.roomSelectorX, data.roomSelectorY, data.floor);
                Position startingPosition = new Position(roomPosition, data.layer, p.x, p.y);
                data.episode.setStartingPosition(startingPosition);
                state = State.EDITING;
            }
            break;
        }
    }

}
