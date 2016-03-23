package com.dive.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Air {

	private int air;
	private int breath, breathOrigin;
	private TextureRegion airRegion;
	private Sprite border_green,border_orange,border_red;
	
	public Air(int breathe){
		air = 10000;
		breathOrigin = this.breath = breathe;
		airRegion = new TextureRegion(Assets.getInstance().air, 7, 7, 47, 467);
		
		border_green = new Sprite(Assets.getInstance().air_green);
		border_green.setBounds(10, 306, 61, 467);
		
		border_orange = new Sprite(Assets.getInstance().air_orange);
		border_orange.setBounds(10, 306, 61, 467);
		
		border_red = new Sprite(Assets.getInstance().air_red);
		border_red.setBounds(10, 306, 61, 467);

	}
	
	public void breathe(float deltaTime){
		air = (int) Math.max(0, air-deltaTime*breath);
		air = Math.min(10000, air);
		airRegion.setRegionY((int) (467-467*(air/10000.0f)));
	}
	
	public void breathe(int k){
		air = Math.max(0, air-k);
	}
	
	public void draw(Batch batch){
		batch.enableBlending();
		batch.draw(airRegion, 17, 306);
		if(air>5000){border_green.draw(batch);}
		else if(air>2500){border_orange.draw(batch);}
		else{border_red.draw(batch);}
	}
	
	public int getAir(){
		return air;
	}
	
	public void catchBreath(){
		breath = breathOrigin;
	}
	
	public void setBreath(int breath){
		this.breath = breath;
	}

	public void reset() {
		air = 10000;
		
	}
	
	
}
