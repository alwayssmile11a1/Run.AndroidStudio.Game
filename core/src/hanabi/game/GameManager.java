package hanabi.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import hanabi.game.Screens.PlayScreen;

//manage audio, sprite, world width, world height, etc.
public class GameManager extends Game {

	//----FINAL VARIABLES-----//
	public static final int WORLDWIDTH = 800;
	public static final int WORLDHEIGHT = 400;


	//batch is used for draw everything into a screen
	//we just need one of this because we only have to draw on it over and over again
	public SpriteBatch batch;

	//Audio manager
	public AssetManager audioManager;

	@Override
	public void create() {
		batch = new SpriteBatch();

		//initial audio
		audioManager = new AssetManager();
		loadEssentialAudio();

		//set screen
		//setScreen(new MenuScreen(this));
		setScreen(new PlayScreen(this,WORLDWIDTH,WORLDHEIGHT));
		//setScreen(new GameOverScreen(this));


	}

	//used for load essential audio
	public void loadEssentialAudio()
	{
		//example
		audioManager.load("musics/music.mp3", Music.class);
		audioManager.load("musics/sfx_wing.ogg",Sound.class);

	}

	@Override
	public void render () {
		super.render();
		audioManager.update();
	}

	@Override
	public void dispose () {
		if(batch!=null) {
			batch.dispose();
		}
		if(audioManager!=null) {
			audioManager.dispose();
		}
	}

}
