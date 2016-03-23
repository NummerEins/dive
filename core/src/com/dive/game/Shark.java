package com.dive.game;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

	public class Shark extends GameObject{
		
		private float x,y;
		private int sizeShark;
		private Random rand;
		private Sprite[] sprites;
		
		
		//kreiere Hai und ihr wird das bild, Größe des Bildes (width,height) und koordinaten übergeben
		public Shark(int xcord, int ycord){
			// initialize random variable, speed in relation to background
			rand = new Random();
			acc = new float[]{0.1f, 0};
			sprites = new Sprite[] {new Sprite(Assets.getInstance().shark_1), new Sprite(Assets.getInstance().shark_2), new Sprite(Assets.getInstance().shark_3), new Sprite(Assets.getInstance().shark_4)};
			
			setRandomTexture();
			
			// set position of shark
			sprite.setPosition(xcord, ycord);
			
			// set shape of shark for collision
			shape = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()*0.5f);
			shape.setPosition(sprite.getX(), sprite.getY());
			
			// set size of shark
			reset();
			
			active = false;
			type = ObjectType.SHARK;
		}
		
		public void setRandomTexture() {
			int i = rand.nextInt(4);
			sprite = sprites[i];
		}
		
		public Rectangle getShape(){
			return shape;
		}
		
		public void moveObject(float deltaTime, float gameSpeed){
			//Bewegung des Hais, Hintergrund + eigene Geschwindigkeit
			sprite.translate(-1920*deltaTime*(gameSpeed+acc[0]), acc[1]*1080*deltaTime);
			// System.out.println("shark: " + -1920*deltaTime*(gameSpeed+acc[0]));
			//bewegt Feld hinter dem Hai für Kollisionserkennung
			shape.setPosition(sprite.getX(), sprite.getY() + sprite.getHeight()*0.18f);
		}

	
	public void reset(){
		sizeShark = 100 + rand.nextInt(120);
		sprite.setSize((float) 1.5*sizeShark, sizeShark);
		shape.setSize(sprite.getWidth(), sprite.getHeight()*0.5f);
		shape.setPosition(sprite.getX(), sprite.getY() + sprite.getHeight()*0.18f);
	}
	
	}
