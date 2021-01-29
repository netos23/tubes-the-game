package ru.fbtw.tubes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.fbtw.tubes.core.Difficulty;
import ru.fbtw.tubes.graphics.TextureLoader;
import ru.fbtw.tubes.os.Platform;
import ru.fbtw.tubes.state.GameOverState;
import ru.fbtw.tubes.state.GameStateManager;

public class MainGame extends ApplicationAdapter {
    public static int WIDTH = 384;
    public static int HEIGHT = 668;

    private SpriteBatch batch;
    private GameStateManager gsm;

    private static Platform platform = Platform.PC;

    public MainGame(Platform platform) {
        this.platform = platform;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();

        if (platform == Platform.ANDROID) {
            WIDTH = Gdx.graphics.getWidth();
            HEIGHT = Gdx.graphics.getHeight();
        }

        //gsm.push(new GameState(gsm, Difficulty.EASY));
        gsm.push(new GameOverState(gsm, true, 100, Difficulty.EASY));
    }


    @Override
    public void render() {
        // обновление логики
        gsm.update(Gdx.graphics.getDeltaTime());

        // очистка экрана
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // главный цыкл отрисовки
        batch.begin();
        gsm.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        TextureLoader.getInstance().dispose();
    }

    public static Platform getPlatform() {
        return platform;
    }
}
