package com.dive.game;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;


public class Boat extends GameObject{
	
	private Random rand;
	private int sizeBoat, ycord;
	private Sprite[] sprites;


	// kreiere Boot und ihr wird das Bild, Größe des Bildes (width,height) und
	// Koordinaten übergeben
	public Boat (int xcord) {
		
		ycord = 920;
		// initialize random variable, speed in relation to background
		rand = new Random();
		acc = new float[] { 0, 0 };
		
		sprites = new Sprite []{ new Sprite(Assets.getInstance().boat), new Sprite(Assets.getInstance().cargoShip)};
		
		sprite = sprites[0];
		// set texture, size
		shape = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(),
				sprite.getHeight());

		reset();
		// set position to the right of the screen at arbitrary height
		sprite.setPosition(xcord, ycord);
		
		// rectangle behind sprite to detect collisions
		shape.setPosition(xcord, ycord);
		
		
		//assign other attributes
		active = false;
		type = ObjectType.BOAT;
	}

	public void moveObject(float deltaTime,
			float gameSpeed) {
		
		sprite.translate(-1920 * deltaTime * (gameSpeed + acc[0]), 0);
		
		// bewegt Feld hinter dem Boot für Kollisionserkennung
		shape.setPosition(sprite.getX(), sprite.getY());
	}

	//method to assign texture and size of sprite
	public void reset(){
		int i = rand.nextInt(2);
		sprite = sprites[i];
		sizeBoat = 60 + rand.nextInt(40);
		if(i == 1){
			sprite.setSize(sizeBoat + 600, sizeBoat + 60);
		}
		else{sprite.setSize(sizeBoat+140, sizeBoat + 100);}
		shape.setSize(sprite.getWidth(), sprite.getHeight());
	}
}