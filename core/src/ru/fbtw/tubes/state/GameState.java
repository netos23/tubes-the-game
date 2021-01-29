package ru.fbtw.tubes.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.fbtw.tubes.MainGame;
import ru.fbtw.tubes.core.Difficulty;
import ru.fbtw.tubes.core.PlayStates;
import ru.fbtw.tubes.core.tube.TubeEntity;
import ru.fbtw.tubes.core.tube.TubeGraph;
import ru.fbtw.tubes.core.tube.TubeGraphBuilder;
import ru.fbtw.tubes.graphics.model.decoration.GameStateDecoration;
import ru.fbtw.tubes.graphics.model.tube.TubeModel;
import ru.fbtw.tubes.graphics.model.tube.TubeModelFactory;
import ru.fbtw.tubes.input.GameStateInputProcessor;
import ru.fbtw.tubes.math.graph.Graph;
import ru.fbtw.tubes.math.graph.RectangleGraphEntity;
import ru.fbtw.tubes.os.Platform;
import ru.fbtw.tubes.ui.CustomButton;
import ru.fbtw.tubes.ui.CustomLabel;
import ru.fbtw.tubes.utils.Vector2;

public class GameState extends State {
	public static final int OFFSET = 25;
	public static final int ADDITIONAL = 1;

	private static final long SECONDS = 1000;
	private static final long MINUTES = SECONDS * 60;
	private static final long GAME_TIME = MINUTES * 3;

	private final Difficulty difficulty;

	private TubeGraph graph;

	private List<TubeModel> models;
	private float blockSide;
	private GameStateDecoration decoration;

	private GameStateInputProcessor processor;
	private Stage buttonsProcessor;

	private final int w;
	private final int h;

	private CustomLabel label;
	private CustomButton submit;
	private long time;

	private PlayStates playState;

	public GameState(GameStateManager gsm, Difficulty difficulty) {
		super(gsm);
		camera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);

		// инициализация логики
		this.difficulty = difficulty;
		w = difficulty.getSize();
		h = w + ADDITIONAL;
		graph = new TubeGraphBuilder(w, h)
				.setDefaultOrigin()
				.setDestination(w - 1, h - 1)
				.setShuffle(true)
				.build();

		// инициализация графики
		blockSide = MainGame.WIDTH * 1.0f / w;
		setupModels();
		decoration = new GameStateDecoration(w, h);

		// инициализация GUI
		submit = new CustomButton("submit");
		if (MainGame.getPlatform() == Platform.ANDROID) {
			label = new CustomLabel("3:00");

			label.setBackground("ui\\button");
			label.setFontSize(2.0f);
			label.setOffsetY(OFFSET);
			label.resize(MainGame.WIDTH / 6f, MainGame.HEIGHT / 22f);
			label.setPosition(MainGame.WIDTH / 2.0f - OFFSET,
					MainGame.HEIGHT - OFFSET * 3.5f);

			CustomLabel btnText = submit.getText();
			btnText.setBackground("ui\\button");
			btnText.setFontSize(2.1f);
			btnText.setOffsetX(OFFSET * .8f);
			btnText.setOffsetY(OFFSET * 1.6f);

			submit.resize(MainGame.WIDTH / 4.0f, MainGame.HEIGHT / 15);
			submit.setPosition(MainGame.WIDTH / 8f * 6, MainGame.HEIGHT / 12f * 11);

		} else {
			label = new CustomLabel("3:00", "ui\\button",
					MainGame.WIDTH / 2.0f - OFFSET, MainGame.HEIGHT - OFFSET * 1.8f);

			CustomLabel btnText = submit.getText();
			btnText.setBackground("ui\\button");
			btnText.setOffsetX(OFFSET * 0.5f);
			btnText.setOffsetY(-OFFSET * 0.1f);

			submit.resize(MainGame.WIDTH / 5, MainGame.HEIGHT / 20);
			submit.setPosition(MainGame.WIDTH - OFFSET * 3, MainGame.HEIGHT - OFFSET * 2);
		}


		// настройки игры
		time = System.currentTimeMillis() + GAME_TIME;
		playState = PlayStates.PLAY;


		// инициализация средств ввода
		submit.setClickListener(this::submit);

		buttonsProcessor = new Stage();
		buttonsProcessor.addActor(submit.getButtonBase());
		processor = new GameStateInputProcessor(w, h, this::handle);

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(buttonsProcessor);
		inputMultiplexer.addProcessor(processor);

		Gdx.input.setInputProcessor(inputMultiplexer);
	}


	// установка графики
	private void setupModels() {
		models = new ArrayList<>();
		TubeEntity[][] entities = (TubeEntity[][]) graph.adjacencyMatrix();
		TubeModelFactory factory = new TubeModelFactory();
		for (int r = entities.length - 1, y = 0; r >= 0; r--, y++) {
			for (int c = 0, colC = entities[0].length; c < colC; c++) {
				TubeModel tube = factory.getTube(entities[r][c]);
				tube.resize(blockSide, blockSide);
				tube.setPosition(blockSide * c, blockSide * y + OFFSET);
				models.add(tube);
			}
		}
	}

	private void finishGame() {
		try {
			RectangleGraphEntity[][] entities = (RectangleGraphEntity[][]) graph.adjacencyMatrix();
			RectangleGraphEntity origin = entities[0][0];
			RectangleGraphEntity dest = entities[h - 1][w - 1];
			boolean win = graph.deepSearch(origin, dest);
			long score = win ? time / SECONDS * 10 : 0;
			if (score < 0) score = 0;

			gsm.set(new GameOverState(gsm, win, score, difficulty));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void handleInput() {
	}

	// колбек на нажатие на экран
	private void handle(int r, int c) {
		// обновление логики
		graph.rotate(r, c);

		// обновление графики
		int index = (h - 1 - r) * w + c;
		TubeEntity[][] entities = (TubeEntity[][]) graph.adjacencyMatrix();
		models.get(index).updateRotation(entities[r][c]);
	}

	private void updateTimer() {
		long curTime = time - System.currentTimeMillis();
		long minutes = curTime / MINUTES;
		long seconds = curTime % MINUTES / SECONDS;

		String format = String.format(Locale.ROOT, "%d:%02d", minutes, seconds);

		label.setText(format);
	}

	private void submit() {
		finishGame();
	}

	//обновление в каждом кадре
	@Override
	public void update(float dt) {
		handleInput();
		updateTimer();

		if (time <= 0) {
			finishGame();
		}
	}

	// отрисовка графики
	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		decoration.render(sb);
		for (TubeModel model : models) {
			model.render(sb);
		}
		label.render(sb);
		submit.render(sb);
	}

	@Override
	public void dispose() {

	}
}
