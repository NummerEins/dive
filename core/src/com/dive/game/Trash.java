package com.dive.game;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Trash extends GameObject {

	private Sprite[] sprites;
	private Random rand;
	private int sizeTrash;
	private int[] listTrashScore;
	private int trashScore;

	// kreiere Müll und ihr wird das Bild, Größe des Bildes (width,height) und
	// Koordinaten übergeben
	public Trash(int xcord, int ycord) {
		// initialize random variable, speed in relation to background
		rand = new Random();
		acc = new float[] { 0, 0 };
		

		// array with sprites to choose from set of random textures
		sprites = new Sprite[] { new Sprite(Assets.getInstance().apple),
				new Sprite(Assets.getInstance().paper) , new Sprite(Assets.getInstance().oil), new Sprite (Assets.getInstance().can) , new Sprite (Assets.getInstance().can)};
		// apple 7 pts, paper 5 pts, oil 15 pts, can 12 pts
		listTrashScore = new int[]{7,5,15, 12, 12};
		
		// set texture, size
		setRandomTexture();

		// set position to the right of the screen at arbitrary height
		sprite.setPosition(xcord, ycord);
		
		// rectangle behind sprite to detect collisions
		shape = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(),
				sprite.getHeight());
		
		//assign other attributes
		active = false;
		fading = false;
		fadeCounter = 0;
		scoreOffset = 0;
		type = ObjectType.TRASH;
		
		font = Assets.getInstance().font_yellow;
		font.getData().setScale(0.5f, 0.5f);
		
		
	}

	public void moveObject(float deltaTime,
			float gameSpeed) {
		// Bewegung des Hais, Hintergrund + eigene Geschwindigkeit
		float yTranslate = acc[1] * height * deltaTime;
		if (yTranslate + sprite.getY() < 20) {
			yTranslate = -sprite.getY() + 20;
		}
		sprite.translate(-1920 * deltaTime * (gameSpeed + acc[0]), yTranslate);

		// bewegt Feld hinter dem Müll für Kollisionserkennung
		shape.setPosition(sprite.getX(), sprite.getY());
		
		if(fading){
			if(fadeCounter<0.3){
//				sprite.setSize(sprite.getWidth()*1.01f, sprite.getHeight()*1.01f);
				scoreOffset += 2;
				fadeCounter += deltaTime;
				sprite.setColor(1, 1, 1, (0.3f-fadeCounter)/0.3f);
			}
			else{
				sprite.setColor(1, 1, 1, 1);
				fading = false;
				fadeCounter = 0;
				sprite.setX(-sprite.getWidth()-1);
				shape.setX(sprite.getX());
				scoreOffset = 0;
			}
			
		}
		
		
	}
	
	public int getTrashScore(){
		return trashScore;
	}

	//method to assign texture and size of sprite
	public void setRandomTexture() {
		int i = rand.nextInt(5);
		sprite = sprites[i];
		trashScore = listTrashScore[i];
		sizeTrash = 40 + rand.nextInt(50);
		if(i==2){
			sprite.setSize(2f* sizeTrash, 2.6f*sizeTrash);
		}
		if(i==3 || i == 4){
			sprite.setSize(0.7f*sizeTrash, sizeTrash);
		}
		if(i == 0 || i == 1){ sprite.setSize(sizeTrash, sizeTrash);}
	}
	
	public void delete(){
		fading = true;
	}
	
	public void draw(Batch batch){	//zeichnet das Objekt auf den gegebenen batch
		if(fading){
			font.draw(batch, "+" + trashScore, sprite.getX(), sprite.getY()+sprite.getHeight()+scoreOffset+20);
		}
		sprite.draw(batch);
	}
	
}
