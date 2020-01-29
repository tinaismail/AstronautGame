package Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;


import Sprites.InteractiveTileObject;

/* This class is responsible for detecting collisions */
public class WorldContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		//identify the two fixtures in collision
		Fixture fixA= contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		//if player is colliding with an object
		if(fixA.getUserData() == "body" || fixB.getUserData() == "body") {//so if one of the fixtures are identified from user data to be "body" then enter statement
			Fixture body = fixA.getUserData() == "body" ? fixA : fixB;//assign FixA or B to body fixture depending which represents "body"
			Fixture object = body == fixA ? fixB : fixA;//assign the other to object fixture
			
			//if the object fixture is an interactive tile object enter statement
			if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) 
			{
				((InteractiveTileObject) object.getUserData()).astroCollide();//call method astroCollide onto respective interactive tile object
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
