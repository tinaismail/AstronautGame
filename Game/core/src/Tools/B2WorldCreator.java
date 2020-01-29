package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.project_string.ics3ugame.GameFinal;

import Sprites.Door;
import Sprites.Star;

/* This class creates all sprite objects of the tileset from tilemap into the game
environment */

public class B2WorldCreator {
	
	public B2WorldCreator(World world, TiledMap map) {
				
				//Create body and fixture variables
				BodyDef bdef = new BodyDef();
				PolygonShape shape = new PolygonShape();
				FixtureDef fdef = new FixtureDef();
				Body body;
				
				//creates ground bodies/fixtures from tiled map
				for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
					Rectangle rect = ((RectangleMapObject) object).getRectangle();
					
					bdef.type = BodyDef.BodyType.StaticBody;
					bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameFinal.PPM, (rect.getY() + rect.getHeight() / 2) / GameFinal.PPM);
					
					body = world.createBody(bdef);
					
					shape.setAsBox(rect.getWidth() / 2 / GameFinal.PPM, rect.getHeight() / 2 / GameFinal.PPM);
					fdef.shape = shape;
					body.createFixture(fdef);
				}
				
				//door object
				for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
					Rectangle rect = ((RectangleMapObject) object).getRectangle();
					
					new Door(world, map, rect);
				}
				
				
				//creates stars
				for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
					Rectangle rect = ((RectangleMapObject) object).getRectangle();
					
					new Star(world, map, rect);
				}
			}
	}
