package ru.fbtw.tubes.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

import ru.fbtw.tubes.MainGame;
import ru.fbtw.tubes.os.Platform;
import ru.fbtw.tubes.state.GameState;

public class GameStateInputProcessor extends InputAdapter {

    private final int w;
    private final int h;
    private final float side;
    private final float min, max;
    private InputCallback callback;

    public GameStateInputProcessor(int w, int h, InputCallback callback) {
        this.w = w;
        this.h = h;
        this.callback = callback;
        side = MainGame.WIDTH * 1.0f / w;
        min = MainGame.HEIGHT - side * h - GameState.OFFSET;
        max = MainGame.HEIGHT - GameState.OFFSET;

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (MainGame.getPlatform() == Platform.PC) {
            // приведение из фактичиского к виртуальному размеру
            screenX = (int) (screenX * 1f / Gdx.graphics.getWidth() * MainGame.WIDTH);
            screenY = (int) (screenY * 1f / Gdx.graphics.getHeight() * MainGame.HEIGHT);
        }

        //обработка области с деталями
        if (screenY > min && screenY < max) {
            screenY -= min;
            int r = (int) Math.floor(screenY / side);
            int c = (int) Math.floor(screenX / side);

            if (r >= h) {
                r = h - 1;
            }

            if (c >= w) {
                c = w - 1;
            }

            callback.handle(r, c);
        }

        return true;
    }

}
