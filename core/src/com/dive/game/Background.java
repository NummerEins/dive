package com.dive.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Background {

	private Sprite sprite1,sprite2;
	private float speed;
	private GameScreen screen;
	
	public Background(Sprite sprite, float speed, Gamescreen screen){
		sprite1 = sprite;
		sprite2 = sprite;
		sprite1.setSize(screen.w, screen.h);
		sprite2.setSize(screen.w, screen.h);
		sprite1.setX(screen.x);
		sprite2.setX(screen.x + screen.width);
		
		this.speed = speed;
        
	}
	
	public void move (float deltatime){
		if ((sprite1.getX()-(speed*screen.width*deltatime)) <0){
			sprite1.translateX(-speed*screen.width*deltatime);
			sprite2.translateX(-speed*screen.width*deltatime);
		}else{
			sprite1.translateX(-speed*screen.width*deltatime);
			sprite2.translateX(-speed*screen.width*deltatime);
		}
		
	}
	
	public void resize(){
		sprite1.setSize(2*screen.width, screen.height);
		sprite2.setSize(2*screen.width, screen.height);
		if(sprite1.getX() < sprite2.getX()){
			sprite2.setX(sprite1.getX() + screen.width);
		}else{
			sprite1.setX(sprite2.getX() + screen.width);
		}
		
	}

	
}


