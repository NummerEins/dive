package com.dive.game;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Plant extends GameObject {

	private int ycord;
	private Random rand;
	private int sizePlant;
	public boolean alreadyhit;

	public Plant(int xcord) {
		ycord = 20;
		rand = new Random();
		acc = new float[]{0,0};

		this.sprite = new Sprite(Assets.getInstance().plant);
		
		sprite.setPosition(xcord, ycord);
		
		shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(), sprite.getHeight());
		shape.setPosition(sprite.getX(), sprite.getY());

		reset();
		
		active = false;
		alreadyhit = false;
		type = ObjectType.PLANT;

	}
	
	public void reset(){
		sizePlant = 70 + rand.nextInt(90);
		alreadyhit = false;
		sprite.setSize(sizePlant + 120, sizePlant);
		shape.setSize(sprite.getWidth(), sprite.getHeight());
	}
	
}