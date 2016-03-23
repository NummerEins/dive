package com.dive.game;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;

public class EndScreen implements InputProcessor {
	
	private Sprite restart, menu;
	private int score;
	private GameState gameState;
	private World world;
	private BitmapFont font_green, font_yellow;
	private boolean[] hovers;
	private String topAlert, deathReason;
	private int distance;
	
	
	public EndScreen(GameState state, World world, BitmapFont font_green, BitmapFont font_yellow, Highscores scores){
		
		gameState = state;
		this.world = world;
		this.font_green = font_green;
		this.font_yellow = font_yellow;
		
		if(font_yellow == null){
			System.out.println("fail") ;
		}
		
		score = 0;
		
		hovers = new boolean[]{false,false};
		
		restart = new Sprite(Assets.getInstance().restartButton);
		restart.setBounds(560, 200, 800, 155);
		
		menu = new Sprite(Assets.getInstance().menuButton);
		menu.setBounds(560, 360, 800, 155);
		
		topAlert = "GAME OVER";
		deathReason = "[suddenly died?]";
		distance = 0;
		
	}
	
	public void draw(Batch batch){
		
		if(hovers[0]){menu.setTexture(Assets.getInstance().menuButton_hover);}
		else{menu.setTexture(Assets.getInstance().menuButton);}
		if(hovers[1]){restart.setTexture(Assets.getInstance().restartButton_hover);}
		else{restart.setTexture(Assets.getInstance().restartButton);}
		
		
		font_green.draw(batch, topAlert, 560, 900, 800, Align.center, true);
		font_green.draw(batch, "your score:", 560, 850, 800, Align.center, true);
		font_yellow.getData().setScale(2, 2);
		font_yellow.draw(batch, score + "", 560, 800, 800, Align.center, true);
		font_yellow.getData().setScale(1, 1);
		font_green.draw(batch, "Unfortunately you " + deathReason + " after " + distance + "m!" , 0, 650, 1920, Align.center, true);
		font_green.draw(batch, "Better luck next time..." , 560, 600, 800, Align.center, true);
		
		restart.draw(batch);
		menu.draw(batch);
		
		
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void setDistance(float d){
		distance = (int) d;
	}
	
	public void setReason(String type){
		if(type == "shark")	{deathReason = "were consumed by a shark";}
		else if(type == "boat")	{deathReason = "tried to fight a boat";}
		else if(type == "air")	{deathReason = "forgot to breathe";}
		else if(type == "rock")	{deathReason = "hit rock bottom";}
		else{deathReason = "";}
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
