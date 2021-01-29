package ru.fbtw.tubes.state;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Locale;

import ru.fbtw.tubes.MainGame;
import ru.fbtw.tubes.core.Difficulty;
import ru.fbtw.tubes.graphics.TextureLoader;
import ru.fbtw.tubes.ui.CustomButton;
import ru.fbtw.tubes.ui.CustomLabel;

public class GameOverState extends State {
    private static final String BG_NAME = "ui\\empty";
    public static final String WIN = "VICTORY!";
    public static final String LOSE = "GAME OVER";
    private final Difficulty difficulty;

    private Sprite bg;
    private CustomLabel title, score;
    private CustomButton menu, game;

    public GameOverState(GameStateManager gsm, boolean win, int score, Difficulty difficulty) {
        super(gsm);
        this.difficulty = difficulty;

        Texture texture = TextureLoader.getInstance().loadTexture(BG_NAME);
        bg = new Sprite(texture);
        bg.setSize(MainGame.WIDTH, MainGame.HEIGHT);
        bg.setColor(Color.BLACK);

        title = new CustomLabel(win ? WIN : LOSE);
        title.setFontSize(1.5f);
        title.setPosition(25, MainGame.HEIGHT / 2);
        title.setColor(Color.WHITE);

        String scoreFormat = String.format(Locale.ROOT,"Score: %d", score);
        this.score = new CustomLabel(scoreFormat);
        this.score.setFontSize(1.5f);
        this.score.setColor(Color.WHITE);
        this.score.setPosition(25,MainGame.HEIGHT/3);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        bg.draw(sb);
        score.render(sb);
        title.render(sb);
    }

    @Override
    public void dispose() {

    }
}
