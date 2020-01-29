package Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.*;
import com.project_string.ics3ugame.GameFinal;
/* Instruction class displays instructions which then opens Menu screen file */

public class InstructionScreen implements Screen{
	
	private Viewport viewport;
	private Stage stage;
	private GameFinal game;
	
	public InstructionScreen (final GameFinal game) {
		this.game = game;
		viewport = new FitViewport(GameFinal.V_WIDTH, GameFinal.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, ((GameFinal) game).batch);
		
		Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
		Table table = new Table();
		table.center();
		table.setFillParent(true);
		
		// label definitions for screen
		Label BeginLabel = new Label("Instructions", font);
		Label BeginLabel1 = new Label("Use up arrow key to jump", font);
		Label BeginLabel2 = new Label("Use left arrow key to move left", font);
		Label BeginLabel3 = new Label("Use right arrow key to move right", font);
		Label BeginLabel4 = new Label("Your mission is to help astronaut navigate ", font);
		Label BeginLabel5 = new Label("the surface of the moon!", font);
		Label BeginLabel6 = new Label("Collect all the stars and reach", font);
		Label BeginLabel7 = new Label("the mission door before liftoff!", font);
		Label BeginLabel8 = new Label("Click anywhere to begin!", font);
		float fontSize=0.5f;
		
		// set font size for labels
		BeginLabel1.setFontScale(fontSize);
		BeginLabel2.setFontScale(fontSize);
		BeginLabel3.setFontScale(fontSize);
		BeginLabel4.setFontScale(fontSize);
		BeginLabel5.setFontScale(fontSize);
		BeginLabel6.setFontScale(fontSize);
		BeginLabel7.setFontScale(fontSize);
		
		// add labels to table 
		table.add(BeginLabel).expandX();
		table.row();
		table.add(BeginLabel1).expandX();
		table.row();
		table.add(BeginLabel2).expandX();
		table.row();
		table.add(BeginLabel3).expandX();
		table.row();
		table.add(BeginLabel4).expandX();
		table.row();
		table.add(BeginLabel5).expandX();
		table.row();
		table.add(BeginLabel6).expandX();
		table.row();
		table.add(BeginLabel7).expandX();
		table.row();
		table.row();
		table.add(BeginLabel8).expandX();
		table.row();
		stage.addActor(table);
		
		//this prepares the stage to receive the input
		Gdx.input.setInputProcessor(stage);

	}
	
	
	
	@Override
	public void render(float delta) {
		 if(Gdx.input.justTouched()) {
			 game.setScreen(new PlayScreen((GameFinal) game));
	            dispose();
	     }
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
