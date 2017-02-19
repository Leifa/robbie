package de.leifaktor.robbie.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.leifaktor.robbie.RobbieMain;
import de.leifaktor.robbie.data.Episode;
import de.leifaktor.robbie.data.Position;
import de.leifaktor.robbie.data.Room;
import de.leifaktor.robbie.data.entities.Entity;
import de.leifaktor.robbie.data.entities.Player;
import de.leifaktor.robbie.gfx.Tileset;

public class GameScreen extends ScreenAdapter {

    RobbieMain game;
    Episode episode;

    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;
    GameRoomRenderer roomRenderer;

    RuntimeRoom currentRoom;    
    RuntimeEntity player;

    float accu;
    final float oneFrame = 1 / 60f;

    public GameScreen(RobbieMain game, Episode episode) {
        this.game = game;
        this.episode = episode;

        int tilesize = Tileset.tilesize;
        int roomWidth = episode.getRoomWidth();
        int roomHeight = episode.getRoomHeight();

        batch = new SpriteBatch();

        camera = new OrthographicCamera(roomWidth, roomHeight);
        camera.position.set(roomWidth / 2, roomHeight / 2, 0);
        viewport = new FitViewport(roomWidth, roomHeight, camera);

        // Set window size
        int scaleHeight = (Gdx.graphics.getDisplayMode().height-30) / (roomHeight*tilesize);
        int scaleWidth = (Gdx.graphics.getDisplayMode().width-30) / (roomWidth*tilesize);
        int scale = (scaleHeight < scaleWidth) ? scaleHeight : scaleWidth;
        Gdx.graphics.setWindowedMode(roomWidth*tilesize*scale, roomHeight*tilesize*scale);
        Gdx.graphics.setResizable(true);

        Position pos = episode.getStartingPosition();        
        Entity pl = new Player();
        pl.setPosition(pos.x, pos.y);
        player = new RuntimeEntity(pl);
        player.setController(new KeyboardController(player));
        currentRoom = new RuntimeRoom(episode.getRooms().getRoom(pos.roomPosition));
        currentRoom.setPlayer(player, pos);

        roomRenderer = new GameRoomRenderer(currentRoom);      
    }

    @Override
    public void show() {
        accu = 0;
    }

    @Override
    public void render(float delta) {
        accu += delta;
        while (accu > oneFrame) {
            accu -= oneFrame;
            tick();
        }
        draw();
    }
    
    private void tick() {
        currentRoom.continueMovement();
        currentRoom.update();
    }
    
    private void draw() {
        setMaxRenderLayer();
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        roomRenderer.render(batch);
        batch.end();
    }

    private void setMaxRenderLayer() {
        int playerx = player.entity.getX();
        int playery = player.entity.getY();
        int playerLayer = currentRoom.playerLayer;
        int maxRenderLayer = currentRoom.layers.length - 1;
        for (int i = maxRenderLayer; i > playerLayer; i--) {
            if (currentRoom.layers[i].layer.getTile(playerx, playery).getID() != 1) {
                maxRenderLayer = i-1;
            }
        }
        roomRenderer.setMaxRenderLayer(maxRenderLayer);        
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        super.resize(width, height);
    }

}
