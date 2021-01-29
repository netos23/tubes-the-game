package ru.fbtw.tubes.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ru.fbtw.tubes.graphics.Renderable;
import ru.fbtw.tubes.input.CustomClickListener;

public class CustomButton implements Renderable {
    private Button buttonBase;
    private CustomLabel text;
    private CustomClickListener clickListener;



    public CustomButton(String text) {
        this.text = new CustomLabel(text);
        buttonBase = new Button();
        clickListener = CustomClickListener.EMPTY;
        setOnClick();
    }

    // переделать
    private void setOnClick() {
        buttonBase.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clickListener.handle();
                return true;
            }
        });
    }


    @Override
    public void resize(float width, float height) {
        buttonBase.setSize(width, height);
        text.resize(width, height);
    }

    @Override
    public void setPosition(float x, float y) {
        buttonBase.setPosition(x - text.getOffsetX(), y - text.getOffsetY());
        text.setPosition(x, y);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        text.render(batch);
    }

    public CustomLabel getText() {
        return text;
    }

    public Button getButtonBase() {
        return buttonBase;
    }

    public void setClickListener(CustomClickListener clickListener) {
        this.clickListener = clickListener;
        //setOnClick();
    }
}
