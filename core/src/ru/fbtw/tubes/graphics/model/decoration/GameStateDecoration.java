package ru.fbtw.tubes.graphics.model.decoration;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import ru.fbtw.tubes.MainGame;
import ru.fbtw.tubes.graphics.Renderable;
import ru.fbtw.tubes.graphics.TextureLoader;
import ru.fbtw.tubes.state.GameState;

public class GameStateDecoration implements Renderable {
    public static final String BLOCK_TEXTURE = "textures\\block0";
    public static final String CONNECTOR_TEXTURE = "textures\\connector";
    public static final String BG_TEXTURE = "textures\\main-bg";
    private final int w;
    private final int h;
    private List<Sprite> parts;

    public GameStateDecoration(int w, int h) {
        this.w = w;
        this.h = h;
        parts = new ArrayList<>();

        TextureLoader loader = TextureLoader.getInstance();

        buildBackground(loader);
        buildBlocks(loader);
        buildConnectors(loader);
    }

    private void buildBackground(TextureLoader loader) {
        Texture texture = loader.loadTexture(BG_TEXTURE);
        Sprite background = new Sprite(texture);

        float tubeSide = MainGame.WIDTH * 1.0f / w;
        float y = GameState.OFFSET * 2 + h * tubeSide;

        background.setSize(MainGame.WIDTH, MainGame.HEIGHT - y);
        background.setPosition(0, y);

        parts.add(background);
    }

    private void buildBlocks(TextureLoader loader) {
        Texture texture = loader.loadTexture(BLOCK_TEXTURE);
        float width = MainGame.WIDTH * 1.0f / w;
        for (int i = 0; i < w; i++) {
            Sprite blockBottom = new Sprite(texture);
            Sprite blockTop = new Sprite(texture);

            float x = width * i;
            float topY = width * h + GameState.OFFSET;

            setupPart(blockBottom, width, x, 0);
            setupPart(blockTop, width, x, topY);

            parts.add(blockBottom);
            parts.add(blockTop);
        }
    }

    private void buildConnectors(TextureLoader loader) {
        Texture texture = loader.loadTexture(CONNECTOR_TEXTURE);

        Sprite topConnector = new Sprite(texture);
        Sprite bottomConnector = new Sprite(texture);

        float width = MainGame.WIDTH * 1.0f / w;
        float topY = width * h + GameState.OFFSET;
        float bottomX = (w - 1) * width;

        setupPart(topConnector, width, 0, topY);
        setupPart(bottomConnector, width, bottomX, 0);
        topConnector.setOrigin(width / 2, GameState.OFFSET / 2f);
        topConnector.setRotation(180);

        parts.add(topConnector);
        parts.add(bottomConnector);
    }

    private void setupPart(Sprite block, float width, float x, float y) {
        block.setSize(width, GameState.OFFSET);
        block.setPosition(x, y);
    }

    @Override
    public void resize(float width, float height) {

    }

    @Override
    public void setPosition(float x, float y) {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        for (Sprite sprite : parts) {
            sprite.draw(batch);
        }
    }
}
