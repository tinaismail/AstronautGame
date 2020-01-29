//made so that it would be easier to add more interactive objects

package Sprites;

import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.project_string.ics3ugame.GameFinal;

/* Base class for interactive objects from the tilemap*/


public abstract class InteractiveTileObject {
	protected World world;
	protected TiledMap map;
	protected TiledMapTile tile;
	protected Rectangle bounds;
	protected Body body;

	protected Fixture fixture; 
	
	// constructor of InteractiveTileObject
	public InteractiveTileObject(World world, TiledMap map, Rectangle bounds) {
		this.world = world;
		this.map = map;
		this.bounds = bounds;
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		//taken from gem, in case any more interactive tiles in future
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / GameFinal.PPM, (bounds.getY() + bounds.getHeight() / 2) / GameFinal.PPM);
		
		body = world.createBody(bdef);
		
		shape.setAsBox(bounds.getWidth() / 2 / GameFinal.PPM, bounds.getHeight() / 2 / GameFinal.PPM);
		fdef.shape = shape;
		
		fixture = body.createFixture(fdef);
	}
	
	public abstract void astroCollide();
	
	public void setCategoryFilter(short filterBit) {
		Filter filter = new Filter();
		filter.categoryBits = filterBit;
		fixture.setFilterData(filter);
	}
	
	//responsible for retrieving appropriate graphic coordinates upon being called
	public TiledMapTileLayer.Cell getCell(){
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(4);
		return layer.getCell( (int)(body.getPosition().x * GameFinal.PPM/32), (int)(body.getPosition().y * GameFinal.PPM/32) );
	}
}
