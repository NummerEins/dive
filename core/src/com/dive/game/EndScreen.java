package com.dive.game;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EndScreen implements InputProcessor {
	
	private Sprite restart, menu;
	private int score;
	private GameState gameState;
	private World world;
	private BitmapFont font;
	private boolean[] hovers;
	
	
	public EndScreen(GameState state, World world, BitmapFont font){
		
		gameState = state;
		this.world = world;
		this.font = font;
		score = 0;
		
		hovers = new boolean[]{false,false};
		
		restart = new Sprite(Assets.getInstance().restartButton);
		restart.setBounds(560, 200, 800, 155);
		
		menu = new Sprite(Assets.getInstance().menuButton);
		menu.setBounds(560, 405, 800, 155);
		
		
	}
	
	public void draw(Batch batch){
		
		if(hovers[0]){menu.setTexture(Assets.getInstance().menuButton_hover);}
		else{menu.setTexture(Assets.getInstance().menuButton);}
		if(hovers[1]){restart.setTexture(Assets.getInstance().restartButton_hover);}
		else{restart.setTexture(Assets.getInstance().restartButton);}
		
		restart.draw(batch);
		menu.draw(batch);
		font.draw(batch, Integer.toString(score),1000, 800);
		
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public Sprite getRestart(){
		return restart;
	}
	
	public void setSprite(Texture tex){
		restart = new Sprite(tex);
		restart.setBounds(500, 200, 920, 178);
	}

	@Override
	public boolean keyDown(int keycode) {
		if(gameState.getState() != State.ENDSCREEN){return false;}
		
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
			if(hovers[0]){gameState.returnMenu();reset();}
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
		
		if(gameState.getState() != State.ENDSCREEN || button != Buttons.LEFT){return false;}
		
		screenY = Gdx.graphics.getHeight()-screenY;
		float[] p = Coords.getCameraCoords(screenX, screenY);
		
		if(restart.getBoundingRectangle().contains(p[0],p[1])){
			world.reset();
			gameState.resume();
			return true;
		}
		if(menu.getBoundingRectangle().contains(p[0],p[1])){
			gameState.returnMenu();
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
		
		if(gameState.getState() != State.ENDSCREEN){return false;}
		
		reset();
		
		screenY = Gdx.graphics.getHeight()-screenY;
		float[] p = Coords.getCameraCoords(screenX, screenY);
		
		if(menu.getBoundingRectangle().contains(p[0],p[1])){
			hovers[0] = true;
		}
		if(restart.getBoundingRectangle().contains(p[0],p[1])){
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
