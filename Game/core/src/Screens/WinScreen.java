package Screens;
import com.badlogic.gdx.*;
	import com.badlogic.gdx.graphics.*;
	import com.badlogic.gdx.graphics.g2d.*;
	import com.badlogic.gdx.scenes.scene2d.Stage;
	import com.badlogic.gdx.scenes.scene2d.ui.*;
	import com.badlogic.gdx.utils.viewport.*;
	import com.project_string.ics3ugame.GameFinal;

import Scenes.Hud;
	/* Win Screen */
public class WinScreen implements Screen{
		
		private Viewport viewport;
		private Stage stage;
		public static GameFinal game;
		
		public WinScreen (final GameFinal game) {
			this.game = game;
			viewport = new FitViewport(GameFinal.V_WIDTH, GameFinal.V_HEIGHT, new OrthographicCamera());
			stage = new Stage(viewport, ((GameFinal) game).batch);
			
			Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
			Table table = new Table();
			table.center();
			table.setFillParent(true);
			
			// label definitions for Win
			Label CongratsLabel1 = new Label("Congratulations!", font);
			Label CongratsLabel2 = new Label("You've reached the mission door in time!", font);
			Label CongratsLabel3 = new Label("In fact, you had " + Hud.worldTimer +"s to spare!", font);
			Label CongratsLabel4 = new Label("And you managed to collect " + Hud.stars +" stars!", font);
			Label CongratsLabel5 = new Label("Safe travels astronaut!", font);
			float fontSize = 0.5f;
			
			// set font size for labels
			CongratsLabel1.setFontScale(fontSize);
			CongratsLabel2.setFontScale(fontSize);
			CongratsLabel3.setFontScale(fontSize);
			CongratsLabel4.setFontScale(fontSize);
			CongratsLabel5.setFontScale(fontSize);
			
			// add labels to table 
			table.add(CongratsLabel1).expandX();
			table.row();
			table.add(CongratsLabel2).expandX();
			table.row();
			table.add(CongratsLabel3).expandX();
			table.row();
			table.add(CongratsLabel4).expandX();
			table.row();
			table.add(CongratsLabel5).expandX();
			table.row();
			stage.addActor(table);

		}
		
		
		
		@Override
		public void render(float delta) {
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			stage.draw();
	 		
		}
		
		public void setFontSize(float fontSize) {
		
		}
		
		@Override
		public void show() {
			
		}
	 
		

		@Override
		public void resize(int width, int height) {
			
		}

		@Override
		public void pause() {
			
		}

		@Override
		public void resume() {
			
		}

		@Override
		public void hide() {
			
		}

		@Override
		public void dispose() {
			
		}


}
