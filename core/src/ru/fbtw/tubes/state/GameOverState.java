package ru.fbtw.tubes.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Locale;

import com.badlogic.gdx.scenes.scene2d.Stage;
import ru.fbtw.tubes.MainGame;
import ru.fbtw.tubes.core.Difficulty;
import ru.fbtw.tubes.graphics.TextureLoader;
import ru.fbtw.tubes.ui.CustomButton;
import ru.fbtw.tubes.ui.CustomLabel;

import static ru.fbtw.tubes.state.GameState.OFFSET;

public class GameOverState extends State {
	private static final String BG_NAME = "ui\\empty";
	public static final String WIN = "VICTORY!";
	public static final String LOSE = "GAME OVER";
	private final Difficulty difficulty;

	private Sprite bg;
	private CustomLabel title, score;
	private CustomButton menu, game;

	public GameOverState(GameStateManager gsm, boolean win, long score, Difficulty difficulty) {
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

		String scoreFormat = String.format(Locale.ROOT, "Score: %d", score);
		this.score = new CustomLabel(scoreFormat);
		this.score.setFontSize(1.5f);
		this.score.setColor(Color.WHITE);
		this.score.setPosition(25, MainGame.HEIGHT / 3);

		game = new CustomButton("PLAY AGAIN");
		CustomLabel gameText = game.getText();
		gameText.setBackground("ui\\button");
		gameText.setOffsetX(OFFSET * 0.3f);
		gameText.setOffsetY(-OFFSET * 0.3f);
		game.resize(MainGame.WIDTH / 3.4f, MainGame.HEIGHT / 25f);
		game.setPosition(MainGame.WIDTH / 2f - game.getWidth() / 2,
				MainGame.HEIGHT / 4f - game.getHeight() * 3);

		menu = new CustomButton("MENU");
		CustomLabel menuText = menu.getText();
		menuText.setBackground("ui\\button");
		menuText.setOffsetX(OFFSET * 0.2f);
		menuText.setOffsetY(OFFSET * -0.3f);
		menu.resize(MainGame.WIDTH / 6f, MainGame.HEIGHT / 25f);
		menu.setPosition(MainGame.WIDTH / 2f - menu.getWidth() / 2,
				MainGame.HEIGHT / 4f - menu.getHeight());


		game.setClickListener(this::playAgain);

		Stage inputController = new Stage();
		inputController.addActor(game.getButtonBase());
		inputController.addActor(menu.getButtonBase());
		Gdx.input.setInputProcessor(inputController);
	}

	void playAgain() {
		gsm.set(new GameState(gsm, difficulty));
	}

	void goMenu() {
		//	gsm.set();
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
		game.render(sb);
		menu.render(sb);
	}

	@Override
	public void dispose() {

	}
}
