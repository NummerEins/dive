package com.dive.game;

import com.badlogic.gdx.graphics.Texture;

public class randomObject {
	
	Texture penguin = new Texture("penguin.png");
	float a,b;

	public randomObject(int i, int j) {
		this.a=i;
		this.b=j;
	}
	
	public void setX(int i) {
		this.a=i;
	}
	public void setY(int i) {
		this.b=i;
	}

	public Texture getTexture(){
		return penguin;
	}
	public Float getX(){
		return a;
	}
	public Float getY(){
		return b;
	}
}
