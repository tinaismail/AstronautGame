package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.project_string.ics3ugame.GameFinal;

import Scenes.Hud;
import Screens.PlayScreen;

/* This file is responsible rendering the star tile objects in the world, 
 * keeping score, and collision detection */
public class Star extends InteractiveTileObject {
	
	public Star(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
		fixture.setUserData(this);
		setCategoryFilter(GameFinal.STAR_BIT);//sets star fixture to be represented by the category filter star_bit
	}

	public void astroCollide() {
		Gdx.app.log("star", "collision");//logs collision in console
		setCategoryFilter(GameFinal.DESTROYED_BIT);//star fixture is removed. converted to a category filter that player cannot interact with 
		getCell().setTile(null);//star graphic is removed
		GameFinal.manager.get("audio/Sounds/star.mp3", Sound.class).play();//plays star mp3 after player collides with star
		Hud.stars ++;//star value increases
		Hud.starLabel.setText(String.format(Hud.stars + "/10",  Hud.stars));//format of score keeping updated
		
	}
	
}
