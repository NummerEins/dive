package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;






public class DiveGame extends ApplicationAdapter implements InputProcessor,ApplicationListener{


	private SpriteBatch batch;
	private ObjectGenerator newObjects;
	private World world;
	private Parallax parallax;
	private GameState gameState;
	private float deltaTime;
	private BitmapFont font;
	private ArrayList<InputProcessor> processors;

	private float widthScale; //breite der black bars 
	private Sprite bb1, bb2; //blackBars für horizontales 16:9
	private EndScreen endscreen;
	private Menu menu;
	private ScoreScreen highscores;

	private Stage stage;
	private Joystick joystick;
	
	private DiverAnimation diverAnimation;
	
	private OrthographicCamera cam;
	

	@Override
	public void create() {
		
		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();
		
		diverAnimation = new DiverAnimation();
		
		batch = new SpriteBatch();
		font = Assets.getInstance().font;
		
		//Gamelogik der Welt erzeugen 
		gameState = new GameState(0);
		newObjects = new ObjectGenerator(8,8,8,8,6,3,3, 0.1f);
		parallax = new Parallax(0.03f);
		world = new World(newObjects,0.1f,gameState, font, diverAnimation);
		
		//Joystick und Stage erzeugen
		stage = new Stage();
		joystick = new Joystick(stage,font);
		joystick.addActors();
		
		//Kamera erzeugen
		cam = new OrthographicCamera(1920, 1920 * (h / w));
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		cam.update();
        
		//schwarze Balken für 16:9 (nur vertikale Streifen)
		bb1 = new Sprite(Assets.getInstance().black);
		bb2 = new Sprite(Assets.getInstance().black);
		
        // Inputverwaltung setzen
		endscreen = new EndScreen(gameState, world, font);
		menu = new Menu(gameState, world);
		highscores = new ScoreScreen(gameState, world, font);
		processors = new ArrayList<InputProcessor>();
		processors.add(endscreen);
		processors.add(stage);
		processors.add(menu);
		processors.add(highscores);
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		world.music.stop();
		Assets.getInstance().dispose();
		stage.dispose();
	}

	@Override
	public void render() {
		
		// kamera updaten
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		//schwarze grundlage zeichnen, deltaTime auslesen
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		deltaTime = Gdx.graphics.getDeltaTime();
		
		//Spiellogik updaten und Welt bewegen
		if(gameState.getState() == State.GAME){
			world.update(deltaTime);
			world.move(deltaTime, joystick.getJoystick().getKnobPercentX(),joystick.getJoystick().getKnobPercentY());
			parallax.setSpeed(world.getSpeed());
			parallax.move(deltaTime);
		}
		else if(gameState.getState() == State.ENDSCREEN){
			endscreen.setScore(world.getScore());
		}
		else if(gameState.getState() == State.HIGHSCORES){
			parallax.setIdle();
			parallax.move(deltaTime);
		}
		else if(gameState.getState() == State.MENU){
			parallax.setIdle();
			parallax.move(deltaTime);
		}	
		
		
		//batch erstellen
		batch.begin();
			parallax.draw(batch);
			if(gameState.getState() == State.GAME || gameState.getState() == State.PAUSE){
				joystick.getCheckbox().addAction(Actions.fadeOut(1));
				world.draw(batch);
			}
			else if(gameState.getState() == State.ENDSCREEN){
				joystick.getCheckbox().addAction(Actions.fadeIn(1));
				world.draw(batch);
				endscreen.draw(batch);
			}
			else if(gameState.getState() == State.HIGHSCORES){
				highscores.draw(batch);
			}
			else if(gameState.getState() == State.MENU){
				joystick.getCheckbox().addAction(Actions.fadeIn(1)); 
				if(joystick.getCheckbox().isChecked()){
					joystick.getJoystick().addAction(Actions.fadeOut(1));
				}else{
					joystick.getJoystick().addAction(Actions.fadeIn(1));
				}
				menu.draw(batch);
			}
			bb1.draw(batch);
			bb2.draw(batch);
		batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		if(width/(float) height < 16.0/9){
			cam.viewportWidth = 1920f;
	        cam.viewportHeight = 1920f * height/width;
	        widthScale = 0;
		}
		else{
			cam.viewportHeight = 1080f;
	        cam.viewportWidth = 1080f * width/height;
	        widthScale = (cam.viewportWidth-1920)/(2*1920);
		}
		bb1.setBounds(-widthScale*1920, 0,widthScale*1920, 1080);
		bb2.setBounds(1920, 0,widthScale*1920, 1080);
        cam.update();
	}

	@Override
	public void pause() {
		gameState.pause();
	}

	@Override
	public void resume() {
		gameState.resume();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.SPACE){
			gameState.toggle();
			return true;
		}
		if(keycode == Keys.CONTROL_LEFT){world.setInfAir();}
		for(InputProcessor p: processors){
			p.keyDown(keycode);
		}
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		for(InputProcessor p: processors){
			p.keyUp(keycode);
		}
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		for(InputProcessor p: processors){
			p.keyTyped(character);
		}
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for(InputProcessor p: processors){
			p.touchDown(screenX, screenY, pointer, button);
		}
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for(InputProcessor p: processors){
			p.touchUp(screenX, screenY, pointer, button);
		}
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		for(InputProcessor p: processors){
			p.touchDragged(screenX, screenY, pointer);
		}
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		for(InputProcessor p: processors){
			p.mouseMoved(screenX, screenY);
		}
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		for(InputProcessor p: processors){
			p.scrolled(amount);
		}
		return false;
	}

}
