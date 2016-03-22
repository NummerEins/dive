package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Menu implements InputProcessor {
	
	private Sprite start, logo, highscores;
	private GameState gameState;
	private World world;
	private boolean[] hovers;

	
	
	public Menu(GameState state,World world){
		
		gameState = state;
		this.world = world;
		
		hovers = new boolean[]{false,false};
		
		start = new Sprite(Assets.getInstance().startButton);
		start.setBounds(560, 200, 800, 155);
		
		highscores = new Sprite(Assets.getInstance().highscoreButton);
		highscores.setBounds(560, 405, 800, 155);
		
		logo = new Sprite(Assets.getInstance().diver);
		logo.setBounds(460, 560, 1000, 400);
		
		
	}
	
	public void draw(Batch batch){
		
		if(hovers[0]){highscores.setTexture(Assets.getInstance().highscoreButton_hover);}
		else{highscores.setTexture(Assets.getInstance().highscoreButton);}
		if(hovers[1]){start.setTexture(Assets.getInstance().startButton_hover);}
		else{start.setTexture(Assets.getInstance().startButton);}
		
		highscores.draw(batch);
		start.draw(batch);
		logo.draw(batch);
	}
	
	public Sprite getRestart(){
		return start;
	}
	
	public void setSprite(Texture tex){
		start = new Sprite(tex);
		start.setBounds(500, 200, 920, 178);
	}

	@Override
	public boolean keyDown(int keycode) {
		if(gameState.getState() != State.MENU){return false;}
		
		if(keycode == Keys.DOWN){
			if(!hovers[0] && !hovers[1]){hovers[0]=true;}
			if(hovers[0]){hovers[0]=false;hovers[1]=true;}
		}
		else if(keycode == Keys.UP){
			if(!hovers[0] && !hovers[1]){hovers[0]=true;}
			if(hovers[1]){hovers[1]=false;hovers[0]=true;}
			else{hovers[0]=true;}
		}
		else if(keycode == Keys.ENTER){
			if(hovers[0]){gameState.seeScores();reset();}
			else if(hovers[1]){world.reset();gameState.resume();reset();}
		}
		return false;
	}
	
	public void reset(){
		hovers[0] = hovers[1] = false; 
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if(gameState.getState() != State.MENU || button != Buttons.LEFT){return false;}
		
		float[] p = Coords.getCameraCoords(screenX, Gdx.graphics.getHeight()-screenY);

		if(highscores.getBoundingRectangle().contains(p[0],p[1])){
			gameState.seeScores();
			return true;
		}
		if(start.getBoundingRectangle().contains(p[0],p[1])){
			world.reset();
			gameState.resume();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(gameState.getState() != State.MENU){return false;}
		
		float[] p = Coords.getCameraCoords(screenX, Gdx.graphics.getHeight()-screenY);
		
		reset();
		
		
		if(highscores.getBoundingRectangle().contains(p[0],p[1])){
			hovers[0] = true;
			return true;
		}
		if(start.getBoundingRectangle().contains(p[0],p[1])){
			hovers[1] = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
