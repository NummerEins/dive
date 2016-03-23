package com.dive.game;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Rock extends GameObject {
	
	private int ycord;
	private Random rand;
	private int sizeRock;

	public Rock(int xcord) {
		ycord = 25;
		rand = new Random();
		acc = new float[]{0,0};

		this.sprite = new Sprite(Assets.getInstance().rock);
		
		sprite.setPosition(xcord, ycord);
		
		shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(), sprite.getHeight()*0.85f);
		shape.setPosition(sprite.getX(), sprite.getY());

		reset();
		
		active = false;
		type = ObjectType.ROCK;

	}
	
	public void reset(){
		sizeRock = 150 + rand.nextInt(70);
		sprite.setSize(sizeRock*1.92f, sizeRock);
		shape.setSize(sprite.getWidth()*0.85f, sprite.getHeight()*0.9f);
	}
	

}
