package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DiverAnimation {

    private Animation animation;
    private Texture animationTexture;
    private TextureRegion[] animationRegion; 
    public float animationTimer;
 
    
    private static final int        FRAME_COLS = 8;     
    
    public DiverAnimation(){
    	animationTexture = Assets.getInstance().animation;
		TextureRegion[][] animationSplitter = TextureRegion.split(animationTexture, animationTexture.getWidth()/FRAME_COLS, animationTexture.getHeight()/1);              // #10
		animationRegion = new TextureRegion[FRAME_COLS];
        int index = 0;
            for (int j = 0; j < FRAME_COLS; j++) {
                animationRegion[index++] = animationSplitter[0][j];
            }
        
        animation = new Animation(0.05f, animationRegion);
    }
	public Animation getAnimation(){
		return this.animation;
	}
	
	public void stopAnimation(){
		animation.setPlayMode(Animation.PlayMode.NORMAL);
	}
	public void animationTimer(){
		animationTimer += Gdx.graphics.getDeltaTime();
	}
	public void drawAnimation(Batch batch,float x, float y ){
		batch.draw(animation.getKeyFrame(animationTimer,true), x, y);
	}
}
