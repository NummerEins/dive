package com.dive.game;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;

public class ScoreScreen implements InputProcessor {
	
	private Sprite returnMenu;
	private GameState gameState;
	private BitmapFont font;
	private String[] scoreList, nameList;
	
	
	public ScoreScreen(GameState state,World world, BitmapFont font, Highscores score){
		
		gameState = state;
		this.font = font;
		
		returnMenu = new Sprite(Assets.getInstance().menuButton);
		returnMenu.setBounds(560, 200, 800, 155);
		
		Score[] scores = score.get();
		nameList = new String[10];
		scoreList = new String[10];
		
		int i = 1;
		for(Score s: scores){
			nameList[i-1] = i + ". " + s.getName();
			scoreList[i-1] = Integer.toString(s.getScore());
			i++;
		}
		
	}
	
	public void draw(Batch batch){
		returnMenu.draw(batch);
		for(int i=0; i<10;i++){
			font.draw(batch, nameList[i], 610, 900-(i*50), 450, Align.topLeft, true);
			font.draw(batch, scoreList[i], 1060, 900-(i*50), 250, Align.topRight, true);
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.ESCAPE){
			gameState.returnMenu();
			return true;
		}
		return false;
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
		
		if(gameState.getState() != State.HIGHSCORES || button != Buttons.LEFT){return false;}

		float[] p = Coords.getCameraCoords(screenX, Gdx.graphics.getHeight()-screenY);
		
		if(returnMenu.getBoundingRectangle().contains(p[0],p[1])){
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
		
		if(gameState.getState() != State.HIGHSCORES){return false;}

		float[] p = Coords.getCameraCoords(screenX, Gdx.graphics.getHeight()-screenY);
		
		if(returnMenu.getBoundingRectangle().contains(p[0],p[1])){
			returnMenu.setTexture(Assets.getInstance().menuButton_hover);
			return true;
		}
		else{
			returnMenu.setTexture(Assets.getInstance().menuButton);
		}
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
