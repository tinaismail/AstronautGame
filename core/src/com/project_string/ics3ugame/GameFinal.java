package com.project_string.ics3ugame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
/*import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;*/
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import Screens.MenuScreen;
import Screens.PlayScreen;
import Screens.WinScreen;

public class GameFinal extends Game {
	//gamecam world view
	public static final int V_WIDTH = 190;
	public static final int V_HEIGHT = 190;
	public static final float PPM = 100; 
	
	public static final short GROUND_BIT = 1;
	public static final short ASTRO_BIT = 2;
	public static final short DOOR_BIT = 4;
	public static final short STAR_BIT = 8;
	public static final short DESTROYED_BIT = 16; //they're all factors of 2 so that it is easier to OR them
	
	public SpriteBatch batch;
	
	//manages audio
	public static AssetManager manager;
	
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		manager = new AssetManager();//create asset manager
		manager.load("audio/Music/SciFiMusic.mp3", Music.class);//load background music from audio assets folder
		manager.load("audio/Sounds/star.mp3", Sound.class); //load sound effect from audio assets folder
		manager.finishLoading();//end audio import
		
		//opens menu screen
		this.setScreen(new MenuScreen(this,0));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
