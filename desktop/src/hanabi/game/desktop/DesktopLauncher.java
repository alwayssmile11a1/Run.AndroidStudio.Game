package hanabi.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import hanabi.game.GameManager;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = GameManager.WORLDHEIGHT;
		config.width = GameManager.WORLDWIDTH;
		new LwjglApplication(new GameManager(), config);
	}
}
