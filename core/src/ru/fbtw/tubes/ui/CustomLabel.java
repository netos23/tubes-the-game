package ru.fbtw.tubes.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ru.fbtw.tubes.graphics.Renderable;
import ru.fbtw.tubes.graphics.TextureLoader;

public class CustomLabel implements Renderable {

    private final Label text;
    private Sprite background;
    private boolean isEnableBg;
    private float offsetX, offsetY;


    public CustomLabel(String textString, float x, float y) {
        this(textString);
        setPosition(x, y);
    }

    public CustomLabel(String textString, String background, float x, float y) {
        this(textString);
        setPosition(x, y);
        setBackground(background);
    }

    public CustomLabel(String textString) {
        Skin skin = new Skin(Gdx.files.internal("font/skin.json"));
        text = new Label(textString, skin.get("default", Label.LabelStyle.class));
        text.setColor(Color.BLACK);
        text.setFontScale(0.5f);
        background = new Sprite();
        offsetX = text.getWidth() / 4;
    }

    public void setText(String textString) {
        text.setText(textString);
    }

    public void setColor(Color color) {
        text.setColor(color);
    }

    public void setBackground(String bg) {
        isEnableBg = true;

        Texture texture = TextureLoader.getInstance().loadTexture(bg);
        background = new Sprite(texture);

        background.setSize(text.getWidth(), text.getHeight());
        background.setPosition(text.getX() - offsetX, text.getY() - offsetY);
    }

    public void setBackground(String bg, float w, float h) {
        setBackground(bg);
        resize(w, h);
    }

    @Override
    public void resize(float width, float height) {
        background.setSize(width, height);
    }

    @Override
    public void setPosition(float x, float y) {
        text.setPosition(x, y);
        background.setPosition(x - offsetX, y - offsetY);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        if (isEnableBg) {
            background.draw(batch);
        }
        text.draw(batch, 100);
    }

    public void setFontSize(float size) {
        text.setFontScale(size);
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public void seOffset(float offsetX, float offsetY){
        setOffsetX(offsetX);
        setOffsetY(offsetY);
    }

    public float getOffsetX() {
        return offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }
}
