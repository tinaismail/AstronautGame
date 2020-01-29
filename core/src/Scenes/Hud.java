package Scenes;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.*;
import com.project_string.ics3ugame.GameFinal;

public class Hud implements Disposable {
	public Stage stage;
	private Viewport viewport;
	
	public static Integer worldTimer;
	private float timeCount;
	public static Integer stars;
	
	Label countdownLabel;
	public static Label starLabel;
	Label timeLabel;
	Label starscollectedLabel;
	
	public Hud(SpriteBatch sb) {
		worldTimer = 300;
		timeCount = 0;
		stars = 0;
		
		viewport = new FitViewport(GameFinal.V_WIDTH, GameFinal.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		starLabel = new Label(String.format(stars + "/10",  stars), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		starscollectedLabel = new Label("STARS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		setFontSize(0.65f);
		
		//add labels to table for display
		table.add(starscollectedLabel).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);
		table.row();
		table.add(starLabel).expandX();
		table.add(countdownLabel).expandX();
		
		stage.addActor(table);
	
	}
	// sets font size for all labels created
		public void setFontSize(float fontSize) {
			starLabel.setFontScale(fontSize);
			starscollectedLabel.setFontScale(fontSize);
			countdownLabel.setFontScale(fontSize);
			timeLabel.setFontScale(fontSize);
		}
	//keeping time
	public void update(float dt) {//keeping time
		timeCount += dt;
		if (timeCount >= 1) {
			worldTimer--;
			countdownLabel.setText(String.format("%03d",worldTimer));
			timeCount = 0;
		}
	}
	
	public static int getWorldTimer() {
		return worldTimer;
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
	
}
