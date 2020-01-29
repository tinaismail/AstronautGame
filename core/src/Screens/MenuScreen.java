package Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.*;
import com.project_string.ics3ugame.GameFinal;

/* Main menu screen to display instructions and game */
public class MenuScreen implements Screen{
	Texture playButton_inactive;
	Texture playButton_active;
	Texture exitButton_inactive;
	Texture exitButton_active;
	private Viewport viewport;
	private Stage stage;
	private GameFinal game;
	
	public MenuScreen (final GameFinal game, int state) {
		this.game = game;
		playButton_inactive = new Texture("Untitled - 1_0.png");
		playButton_active = new Texture("Untitled - 1_1.png");
		exitButton_inactive = new Texture("Untitled - 1_2.png");
		exitButton_active = new Texture("Untitled - 1_3.png");
		
		ImageButton playButton=getTextImageButton(playButton_active);
		ImageButton exitButton=getTextImageButton(exitButton_active);
		viewport = new FitViewport(GameFinal.V_WIDTH, GameFinal.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, ((GameFinal) game).batch);
		
		Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
		Label scoreLabel=null;
		Label timeoutLabel=null;
		if(state == 1) {
			scoreLabel = new Label("Mission success!", font);
			
		}
		if(state == 2) {
			timeoutLabel = new Label("Time Out, Try Again!", font);
		}
		
		Table table = new Table();
		table.center();
		table.setFillParent(true);
		
		Label BeginLabel = new Label("Houston, we have a problem!", font);
		BeginLabel.setFontScale(0.8f);
		playButton.addListener(new ClickListener(){
 	        @Override
 	        public void clicked(InputEvent event, float x, float y) {
 	        	game.setScreen(new InstructionScreen((GameFinal) game));
 	        	dispose();
 	        }
 	    });
		// adds a listener to exit button to check for mouseclick
		exitButton.addListener(new ClickListener(){
 	        @Override
 	        public void clicked(InputEvent event, float x, float y) {
 	        	game.dispose();
 	        }
 	    });
		// adds buttons and labels to table
		table.add(BeginLabel).expandX();
		table.row();
		table.row();
		table.add(playButton).expandX();
		table.row();
		table.add(exitButton).expandX();
		table.row();
		table.add(scoreLabel).expandX();
		table.row();
		table.add(timeoutLabel).expandX();
		stage.addActor(table);
		
		//this prepares the stage to receive the input
		Gdx.input.setInputProcessor(stage);

	}
	// creates button objects
	public ImageButton getTextImageButton(Texture texture) {
		TextureRegion myTextureRegion = new TextureRegion(texture);
	    TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        return button;
	
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		game.batch.draw(new Texture("menuscreenimage.png"), 0, 0, GameFinal.V_WIDTH, GameFinal.V_HEIGHT);
		game.batch.end();
 		stage.draw();	
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
