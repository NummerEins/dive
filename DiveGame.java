package com.dive.game;


import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class DiveGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture texture;
	Sprite ente;
	float enteSpeed;
	
    
	int max = 450;
	
	@Override
	public void create () {
		
		
		batch = new SpriteBatch();
		texture = new Texture("ente.png");
		ente = new Sprite(texture);
		ente.setPosition(700, randomInteger(max));
		ente.setSize(100,  100);
		enteSpeed = -2f;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		// movement of the duck
		ente.translateX(enteSpeed);
	
		// gets duck back to original position once its left the screen
		if(ente.getX() == -ente.getWidth()){
			ente.setPosition(700, randomInteger(max));}
		
		// draws moving duck
		batch.draw(ente, ente.getX(), ente.getY(), ente.getWidth(), ente.getHeight());
		batch.end();
	}

public int randomInteger(int max) {
    Random rand = new Random();
    int randomNum = (int)(Math.random() * ((max) + 1));
    return randomNum;
}

}
