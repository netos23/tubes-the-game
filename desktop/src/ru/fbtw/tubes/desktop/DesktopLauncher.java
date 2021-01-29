package ru.fbtw.tubes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.fbtw.tubes.MainGame;
import ru.fbtw.tubes.os.Platform;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MainGame.WIDTH;
		config.height = MainGame.HEIGHT;
		new LwjglApplication(new MainGame(Platform.PC), config);
	}
}
