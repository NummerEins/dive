package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


// From https://github.com/libgdx/libgdx-demo-superjumper/blob/master/core/src/com/badlogicgames/superjumper/Assets.java
// Extended by Singleton-mechanics

public class Assets {
	

	// Assets is Singleton
	
	private static Assets instance = null;
	
	//Background
	public Texture backgroundBottom;
	public Texture backgroundMiddle;
	public Texture backgroundTop;
	public Texture background;
	
	//Objekte
	public Texture diver;
	public Texture shark;
	public Texture jellyfish;
	public Texture plant;
	public Texture rock;
	public Texture joystickup;
	public Texture joystickunder;
	public Texture apple;
	public Texture paper;
	public Texture oil;
	public Texture boat;
	public Texture cargoShip;
	public Texture gasBottle;
	
	//Buttons
	public Texture restartButton;
	public Texture restartButton_hover;
	public Texture startButton;
	public Texture startButton_hover;
	public Texture menuButton;
	public Texture menuButton_hover;
	public Texture highscoreButton;
	public Texture highscoreButton_hover;
	
	//air
	public Texture air;
	public Texture air_green;
	public Texture air_orange;
	public Texture air_red;
	
	//Sounds
	public Music music;
	public Sound bite;
	public Sound gasbottlehit;
	public Sound boathit;
	public Sound jellyfishhit;
	public Sound startup;
	
	//misc
	public BitmapFont font;
	public Texture animation;
	public Texture black;
	
	protected Assets() {
		// Empty constructor
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
	
	private static Music loadMusic (String location){
		// Benutze um Musik zu laden
		return Gdx.audio.newMusic(Gdx.files.internal(location));
	}
	
	private static Sound loadSound (String location){
		return Gdx.audio.newSound(Gdx.files.internal(location));
	}

	private void load () {
		
		System.out.println("Loading Assets..");
		
		backgroundBottom 	= loadTexture("background/sandBackgrounds.png");
		backgroundMiddle 	= loadTexture("background/mainBackground.png");
		backgroundTop    	= loadTexture("background/topBackground.png");
		background		 	= loadTexture("background/mainBackground.png");
		diver 			 	= loadTexture("diver/diver.png");
		shark			 	= loadTexture("Hindernisse/myshark.png");
		jellyfish 		 	= loadTexture("Hindernisse/qualle.png");
		plant			 	= loadTexture("Hindernisse/green-plant.png");
		rock 			 	= loadTexture("Hindernisse/Stone.png");
		joystickup       	= loadTexture("joystick/Joystickup.png");
		joystickunder    	= loadTexture("joystick/Joystickunder.png");
		apple 			 	= loadTexture("Garbage/apple.png");
		paper			 	= loadTexture("Garbage/garbage.png");
		oil				 	=  loadTexture("Garbage/oil.png");
		boat			 	= loadTexture("Hindernisse/ship.png");
		cargoShip 			= loadTexture("Hindernisse/cargo_ship.png");
		gasBottle 			= loadTexture("Hindernisse/Tauchflasche.png");
		black			 	= loadTexture("background/black.png");
		restartButton	 	= loadTexture("Gamescreens/restartbutton.png");
		restartButton_hover	= loadTexture("Gamescreens/restartbutton_hover.png");
		startButton		 	= loadTexture("Gamescreens/startbutton.png");
		startButton_hover	= loadTexture("Gamescreens/startbutton_hover.png");
		menuButton		 	= loadTexture("Gamescreens/menubutton.png");
		menuButton_hover 	= loadTexture("Gamescreens/menubutton_hover.png");
		highscoreButton		= loadTexture("Gamescreens/highscorebutton.png");
		highscoreButton_hover=loadTexture("Gamescreens/highscorebutton_hover.png");
		air				 	= loadTexture("air/air.png");
		air_green		 	= loadTexture("air/green.png");
		air_orange		 	= loadTexture("air/orange.png");
		air_red			 	= loadTexture("air/red.png");
		music 		    	= loadMusic("sounds/Backgroundmusic.mp3");
		bite 			 	= loadSound("sounds/Bite.wav");
		gasbottlehit	 	= loadSound("sounds/Gasbottlehit.wav");
		boathit			 	= loadSound("sounds/Boathit.wav");
		jellyfishhit	 = loadSound("sounds/Jellyfishhit.wav");
		startup			 = loadSound("sounds/Startup.wav");
		font 			 = new BitmapFont(Gdx.files.internal("fonts/StS.fnt"));
		animation 		 = loadTexture("spritesheet/spritesheet.png");
		
	}

	public void dispose() {
		// dispose Textures
		Texture[] tex = new Texture[]{backgroundMiddle, background, diver, plant, rock, boat,cargoShip, shark,
										gasBottle, jellyfish, joystickup, joystickunder, apple, paper, oil, air,
										air_green,air_orange,air_red, black,restartButton,restartButton_hover,
										startButton,startButton_hover, menuButton, menuButton_hover, highscoreButton,
										highscoreButton_hover};
		for(Texture t:tex){
			t.dispose();
		}
		// dispose Sounds
		Sound[] sounds = new Sound[]{bite, gasbottlehit, boathit, startup, jellyfishhit};
		for(Sound s:sounds){
			s.dispose();
		}
		// dispose music
		music.dispose();
		// dispose fonts
		font.dispose();
	}

	//public static void playSound (Sound sound) {
	//	if (Settings.soundEnabled) sound.play(1);
	//}
}
