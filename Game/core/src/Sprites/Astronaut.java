package Sprites;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.project_string.ics3ugame.GameFinal;

import Screens.PlayScreen;

/* Astronaut sprite object is created to define characteristics of astronaut */

public class Astronaut extends Sprite {
	
	public enum State { RUNNING, IDLE, JUMPING };
	public State currentState;
	public State previousState;
	
	public World world;
	public Body b2body;
	
	private Animation astronautRun;
	private Animation astronautIdle;
	private float stateTimer;
	private boolean runningRight;
	
	public Astronaut(World world, PlayScreen screen) {
		super(screen.getAstroAtlas().findRegion("astronautidlewalk"));
		this.world = world;
		
		currentState = State.IDLE;
		previousState = State.IDLE;
		stateTimer = 0;
		runningRight = true;
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
		
		for(int i = 3; i < 9; i++) {
			frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 64));
			
		}
		// animates astronaut's run through looping of images
		astronautRun = new Animation(0.1f, frames, PlayMode.LOOP );
		frames.clear();//clears frame so that next animation may be defined
		
		for(int i = 0; i < 3; i++)
			frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 64));
		
		// idle animation 
		astronautIdle = new Animation(0.2f, frames, PlayMode.LOOP );
		
		defineAstronaut();//calls defineAstronaut method
		setBounds(0, 0, 32 / GameFinal.PPM, 64 / GameFinal.PPM);//sets animation boundaries
		
		
	}
	
	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - 22 / GameFinal.PPM );
		
		setRegion(getFrame(dt));//gets appropriate key frame depending on time
	}
	
	public TextureRegion getFrame(float dt) {
		currentState = getState();
		
		TextureRegion region;//region is the graphic that represents astronaut at a given moment
		
		//do things differently depending on what current state is
		switch(currentState) {		
			case RUNNING:
				region = (TextureRegion) astronautRun.getKeyFrame(stateTimer, true);//loopable animation, boolean for looping
				break;
			case IDLE:
			case JUMPING:
			default://case idle and jumping are both represented by the same idle animation
				region = (TextureRegion) astronautIdle.getKeyFrame(stateTimer);//statetimer decides what frame to give you from the animation
				break;
		}
		//if running in positive x direction, draw to the right 
		if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {//region.isflip
			region.flip(true, false);
			runningRight = false;
		}
		//else flip left
		else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
			region.flip(true, false);//true for x axis, false for y axis
			runningRight = true;
		}
		
		//this just manages keyFrames and delta time
		stateTimer = currentState == previousState? stateTimer + dt : 0;//if current state = previous state, then state timer + dt, else reset timer to 0
		previousState = currentState;
		return region;//returns astronaut graphic
	}
	
	public State getState() {
		if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
			return State.JUMPING;
		else if(b2body.getLinearVelocity().x != 0)
			return State.RUNNING;
		else
			return State.IDLE;
	}
	
	public void defineAstronaut() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(64 / GameFinal.PPM, 32 / GameFinal.PPM);//setting player's start position two tiles to the right and one up
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		shape.setAsBox(5 / GameFinal.PPM, 20 / GameFinal.PPM);
		fdef.shape = shape;
		b2body.createFixture(fdef);
		fdef.filter.categoryBits = GameFinal.ASTRO_BIT; //associate astro_bit with the astronaut's b2body
		fdef.filter.maskBits = GameFinal.GROUND_BIT | GameFinal.DOOR_BIT | GameFinal.STAR_BIT ; //bits astronaut can collide with
		/*
		EdgeShape rightSide = new EdgeShape();
		rightSide.set(new Vector2(5f / GameFinal.PPM, -20 / GameFinal.PPM), new Vector2(5f / GameFinal.PPM, 20 / GameFinal.PPM));
		fdef.shape = rightSide;
		fdef.isSensor = true;
		
		EdgeShape leftSide = new EdgeShape();
		leftSide.set(new Vector2(-5f / GameFinal.PPM, -20 / GameFinal.PPM), new Vector2(-5f / GameFinal.PPM, 20 / GameFinal.PPM));
		fdef.shape = leftSide;
		fdef.isSensor = true;
		*/
		b2body.createFixture(fdef).setUserData("body");
		
		/*BodyDef bdef = new BodyDef();
		bdef.position.set(64 / GameFinal.PPM, 64 / GameFinal.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10 / GameFinal.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);*/
	}
}
