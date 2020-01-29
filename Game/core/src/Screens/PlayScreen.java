package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.project_string.ics3ugame.GameFinal;

import Scenes.Hud;
import Sprites.Astronaut;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;

public class PlayScreen implements Screen {
	//reference to Game, used to set screens
	private static GameFinal game;
	
	public TextureAtlas AstronautAtlas;
	//public TextureAtlas AlienAtlas;
	
	//basic playscreen variables
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;
	
	//filled map variables
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	//box2D variables
	private World world;
	private Box2DDebugRenderer b2dr;
	
	//sprites
	private Astronaut player;
	//private Alien alien;
	
	private Music music;
	
	
	public PlayScreen(GameFinal game) {
		AstronautAtlas = new TextureAtlas(Gdx.files.internal("idle+walk.pack"));
		
		this.game = game;
		
		//cam following character through world
		gamecam = new OrthographicCamera();
		
		//create a FitViewPort to maintain virtual aspect ratio
		gamePort = new FitViewport(GameFinal.V_WIDTH / GameFinal.PPM, GameFinal.V_HEIGHT / GameFinal.PPM, gamecam);
		
		//create our game HUD for scores/timers/level info
		hud = new Hud( game.batch );
		
		//loads map and sets up map renderer
		maploader= new TmxMapLoader();
		map = maploader.load("spacemap.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / GameFinal.PPM);
		
		//initially sets gamecam to be centered correctly at the start of map
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		
		world = new World(new Vector2(0, -10), true);
		b2dr = new Box2DDebugRenderer();
		
		new B2WorldCreator(world, map);
		
		//create astronaut in game world
		player = new Astronaut(world, this);
		
		//setting up collision detection
		world.setContactListener(new WorldContactListener());
		
		music = GameFinal.manager.get("audio/Music/SciFiMusic.mp3", Music.class);
		music.setLooping(true);
		music.play();
		
		
		
	}
	
	public static void setWin() {
			game.setScreen(new WinScreen((GameFinal) game));
	}
	
	public TextureAtlas getAstroAtlas() {
		return AstronautAtlas;
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	
	
	public void handleInput(float dt) {
		//apply a linear impulse downward on player, acting as a gravitational force: makes character jump and fall
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
			player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(),true);
		//go left or right depending on key pressed
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2) 
			//player.b2body.setLinearVelocity(2f, 0f);
			player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
			//player.b2body.setLinearVelocity(-2f, 0f);
			player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
	}
	
	public void update(float dt) {
		//handle user input
		handleInput(dt);
		
		//takes 1 step in the physics simulation(60 times per second)
		world.step(1/60f, 6, 2);
		
		player.update(dt);
		//alien.update(dt);
		hud.update(dt);
		
		//position of gamecam set to focus around player's x and y coordinates
		gamecam.position.x = player.b2body.getPosition().x;
		gamecam.position.y = player.b2body.getPosition().y;
		
		//update gamecam with correct coordinates after changes
		gamecam.update();
		
		//tells renderer to draw only what camera can see in game world
		renderer.setView(gamecam);
	}

	@Override
	public void render(float delta) {
		//separates update logic from render
		update(delta);
		
		//clear game screen with black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//renders game map
		renderer.render();
		
		//renders Box2DDebugLines
		b2dr.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		player.draw(game.batch);
		//alien.draw(game.batch);
		game.batch.end();
		
		
		//sets batch to draw what the HUD cam sees
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		
		
	}
	
	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);

	}
	
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
		

	}

}
