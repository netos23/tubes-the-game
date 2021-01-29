package ru.fbtw.tubes.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderable {
    void resize(float width, float height);
    void setPosition(float x, float y);
    void update(float dt);
    void render(SpriteBatch batch);
}
