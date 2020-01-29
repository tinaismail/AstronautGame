package Sprites;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.project_string.ics3ugame.GameFinal;

import Screens.PlayScreen;


public class Door extends InteractiveTileObject{
	public Door(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
		fixture.setUserData(this);//setting user data to object
		setCategoryFilter(GameFinal.DOOR_BIT);
	}

	@Override
	public void astroCollide() {
		Gdx.app.log("door", "collision");
		PlayScreen.setWin();
		//next step: switch to winScreen class
		
	}

}
