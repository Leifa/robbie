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
import de.leifaktor.robbie.data.Room;
import de.leifaktor.robbie.gfx.Tileset;

public class GameScreen extends ScreenAdapter {

    RobbieMain game;
    Episode episode;

    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;
    GameRoomRenderer roomRenderer;
    
    Room currentRoom;

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

        int scaleHeight = Gdx.graphics.getDisplayMode().height / (roomHeight*tilesize);
        int scaleWidth = Gdx.graphics.getDisplayMode().width / (roomWidth*tilesize);
        int scale = (scaleHeight < scaleWidth) ? scaleHeight : scaleWidth;

        Gdx.graphics.setWindowedMode(roomWidth*tilesize*scale, roomHeight*tilesize*scale);
        Gdx.graphics.setResizable(true);

        currentRoom = episode.getRooms().getRoom(episode.getStartingPosition().roomPosition);
        roomRenderer = new GameRoomRenderer(currentRoom);
    }

    @Override
    public void show() {
        super.show();
    };

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        roomRenderer.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        super.resize(width, height);
    }

}
