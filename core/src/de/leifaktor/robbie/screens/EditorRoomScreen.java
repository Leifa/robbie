package de.leifaktor.robbie.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

import de.leifaktor.robbie.RobbieMain;
import de.leifaktor.robbie.data.Position;
import de.leifaktor.robbie.data.RoomLayer;
import de.leifaktor.robbie.data.RoomLayerFactory;
import de.leifaktor.robbie.data.entities.Entity;
import de.leifaktor.robbie.data.tiles.Tile;
import de.leifaktor.robbie.gfx.EntityPaletteRenderer;
import de.leifaktor.robbie.gfx.RoomRenderer;
import de.leifaktor.robbie.gfx.TilePaletteRenderer;

public class EditorRoomScreen implements Screen {

    SpriteBatch batch;
    RoomRenderer roomRenderer;
    ShapeRenderer shapeRenderer;
    TilePaletteRenderer tilePaletteRenderer;
    EntityPaletteRenderer entityPaletteRenderer;

    RobbieMain game;
    EditorSelectionData data;

    Tile selectedTile;
    Entity selectedEntity;

    boolean tilePaletteActive = true;

    final int RENDER_ROOM_X = 0;
    final int RENDER_ROOM_WIDTH = 800;
    final int RENDER_PALETTE_X = 900;
    final int RENDER_PALETTE_Y = 700;
    final int PALETTE_TILESIZE = 32;
    final int PALETTE_TILES_PER_ROW = 8;

    Position mousePosition;

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

        Gdx.input.setInputProcessor(new InputAdapter() {

            public boolean mouseMoved(int screenX, int screenY) {
                mousePosition = roomRenderer.getClickPosition(screenX, Gdx.graphics.getHeight() - screenY);
                return true;
            }

            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                processClickAt(screenX, screenY);
                return true;
            }

            public boolean touchDragged(int screenX, int screenY, int pointer) {
                processClickAt(screenX, screenY);
                return true;
            }

            public void processClickAt(int screenX, int screenY) {
                if (screenX >= RENDER_PALETTE_X) { // PALETTE GEKLICKT
                    if (tilePaletteActive) {
                        selectedTile = tilePaletteRenderer.getTile(screenX, Gdx.graphics.getHeight() - screenY);
                    } else {
                        selectedEntity = entityPaletteRenderer.getEntity(screenX, Gdx.graphics.getHeight() - screenY);
                    }
                } else { // RAUM GEKLICKT
                    Position p = roomRenderer.getClickPosition(screenX, Gdx.graphics.getHeight() - screenY);
                    if (p != null) {
                        if (tilePaletteActive) {
                            data.layer.setTile(p.x, p.y, selectedTile);
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
            }
        });
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
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) game.setScreen(new EditorFloorScreen(game, data));
        if (Gdx.input.isKeyJustPressed(Keys.L)) {
            int index = data.room.getLayers().indexOf(data.layer);
            RoomLayer newLayer = RoomLayerFactory.transparentLayer(data.room.getWidth(), data.room.getHeight());
            data.room.getLayers().add(index+1, newLayer);
            setActiveLayerIndex(index+1);
        } else if (Gdx.input.isKeyJustPressed(Keys.K)) {
            int index = data.room.getLayers().indexOf(data.layer);
            RoomLayer newLayer = RoomLayerFactory.transparentLayer(data.room.getWidth(), data.room.getHeight());
            data.room.getLayers().add(index, newLayer);
            setActiveLayerIndex(index);
        } else if (Gdx.input.isKeyJustPressed(Keys.PAGE_UP)) {
            int index = data.room.getLayers().indexOf(data.layer);
            if (index < data.room.getLayers().size() - 1) setActiveLayerIndex(index+1);
        } else if (Gdx.input.isKeyJustPressed(Keys.PAGE_DOWN)) {
            int index = data.room.getLayers().indexOf(data.layer);
            if (index > 0) setActiveLayerIndex(index-1);
        }
        if (Gdx.input.isKeyJustPressed(Keys.T)) {
            roomRenderer.setGrayLayers(!roomRenderer.getGrayLayers());
        } else if (Gdx.input.isKeyJustPressed(Keys.P)) {
            tilePaletteActive = !tilePaletteActive;
        }
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
        game.font.draw(batch, "T: transparency", 630, 785);
        game.font.draw(batch, "P: switch palette", 830, 785);
        game.font.draw(batch,
                "Layer " + 
                        (data.room.getLayers().indexOf(data.layer)+1) + 
                        " / " + 
                        data.room.getLayers().size(),
                        30,
                        700);
        if (mousePosition != null) {
            game.font.draw(batch, "(" + mousePosition.x + ", " + mousePosition.y + ")", 30, 680);
        }        
        batch.end();
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.line(0, 720, Gdx.graphics.getWidth(), 720);
        shapeRenderer.end();
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
