package com.naturegames.thewoods;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.naturegames.thewoods.data.GameSettingsAndData;
import com.naturegames.thewoods.screens.MainMenuScreen;


public class Main extends Game {

	public static float SCREEN_WIDTH = 800;
	public static float SCREEN_HEIGHT = 360;

	private SpriteBatch batch;
	private Music music;

	public void create() {
		initAssets();
		setSettings();
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();
	}

	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void dispose() {
		batch.dispose();
	}

	public void setSettings() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		playMusic(GameSettingsAndData.getMusicOn());
	}

	public void initAssets() {
		batch = new SpriteBatch();
		music = Gdx.audio.newMusic(Gdx.files.internal("music/music.mp3"));
	}

	public void playMusic(boolean isMusicOn) {
		if (isMusicOn) {
			music.setLooping(true);
			music.play();
		} else {
			music.stop();
		}
	}
}

