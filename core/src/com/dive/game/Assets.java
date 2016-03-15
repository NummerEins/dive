package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

// From https://github.com/libgdx/libgdx-demo-superjumper/blob/master/core/src/com/badlogicgames/superjumper/Assets.java
// Extended by Singleton-mechanics

public class Assets {
	
	// Assets is Singleton
	
	private static Assets instance = null;
	
	/*
	 * 	TEXTUREN
	 */
	public Texture backgroundBottom;
	public Texture backgroundMiddle;
	public Texture backgroundTop;
	public Texture background;
	public Texture diver;
	public Texture shark;
	public Texture wal;
	//public TextureRegion backgroundRegion;
	
	/*
	 * 	SOUNDS
	 */
	//public Sound makeSomeNoise;
	
	protected Assets() {
		// Empty
	}
	
	public static Assets getInstance() {
		if (instance == null) {
			instance = new Assets();
			instance.load();
		}
		return instance;
	}

	private static Texture loadTexture (String location) {
		// Falls exceptions auftreten, die besagen, dass eine Datei nicht
		// gefunden wurde, ersetze die folgende Zeile mit der nachfolgenden.
		return new Texture(Gdx.files.internal(location));
		//return new Texture(location);
	}

	private void load () {
		
		System.out.println("Loading Assets..");
		
		backgroundBottom = loadTexture("background/sandBackgrounds.png");
		backgroundMiddle = loadTexture("background/mainBackground.png");
		backgroundTop    = loadTexture("background/topBackground.png");
		background		 = loadTexture("background/mainBackground.png");
		diver 			 = loadTexture("diver/diver.png");
		wal				 = loadTexture("wal.png");
		shark			= loadTexture("Hindernisse/myshark.png");
		
	}

	//public static void playSound (Sound sound) {
	//	if (Settings.soundEnabled) sound.play(1);
	//}
}
