package ru.fbtw.tubes.graphics.model.tube;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

import ru.fbtw.tubes.graphics.TextureLoader;

public abstract class SimpleTube implements TubeModel {
    public static final String BACKGROUND_NAME = "tubes\\var1";
    //  public static final String BACKGROUND_NAME = "badlogic";
    protected final Sprite sprite, bg;
    protected int angle;

    public SimpleTube(String textureName) {
        angle = 0;
        TextureLoader textureLoader = TextureLoader.getInstance();

        sprite = new Sprite(textureLoader.loadTexture(textureName));
        bg = new Sprite(textureLoader.loadTexture(BACKGROUND_NAME));
        //bg.setColor(getRandomColor());
    }

    private Color getRandomColor() {
        Random random = new Random();
        if (random.nextInt(1001) > 500) {
            return Color.CORAL;
        }else {
            return Color.WHITE;
        }
    }


    @Override
    public void resize(float width, float height) {
        sprite.setSize(width, height);
        bg.setSize(width, height);
        sprite.setOrigin(width / 2, height / 2);
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
        bg.setPosition(x, y);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        bg.draw(batch);
        sprite.draw(batch);
    }
}
