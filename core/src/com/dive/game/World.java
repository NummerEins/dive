package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class World {
	
	private ArrayList<GameObject> objects;	//Liste aller im Spiel aktiven Objekte
	private float speed;					//Spielgeschwindigkeit in (% der Darstellungsbreite pro Sek.)
	private ObjectGenerator objectGen;		//der Objekt-Generator, welcher die Spielwelt erzeugt (wird im Konstruktor übergeben)
	private Diver diver;					//der Diver (wird im Konstruktor erstellt)
	private GameState state;				//setzt den SPielzustand (zB um zu pausieren)
	private float distance;					//die zurückgelegte Strecke - legt Geschwindigkeit fest
	private int score;						//Anzahl des gesammelten Mülls
	private BitmapFont font;
	public Music music;
	private Sound bite;
	private Sound gasbottlehit;
	private Sound boathit;
	private Sound jellyfishhit;
	private Sound startup;
	private boolean infAir;
	private DiverAnimation diverAnimation;
	public World(ObjectGenerator objectGen, float iniSpeed, GameState state, BitmapFont font, DiverAnimation animation){
		
		objects = new ArrayList<GameObject>();
		speed = iniSpeed;
		distance = 0;
		score = 0;

		this.objectGen = objectGen;
		this.state = state;
		this.font = font;
		
		diverAnimation = animation;

		diver = new Diver(Assets.getInstance().diver, 150, 75, 300, diverAnimation);
		// start playing background music
		music = Assets.getInstance().music;	
		music.setVolume(0.1f);
		music.play();
		music.setLooping(true);
		// loading sounds
		bite = Assets.getInstance().bite;
		gasbottlehit = Assets.getInstance().gasbottlehit;
		boathit = Assets.getInstance().boathit;
		jellyfishhit = Assets.getInstance().jellyfishhit;
		startup = Assets.getInstance().startup;
		infAir = false;
		
	}
	
	
	public void draw(Batch batch){			//Alle Spielobjekte zeichnen
		for(GameObject o: objects){o.draw(batch);}
		diver.draw(batch);
		font.draw(batch, Integer.toString(score),0, 1080);
	}
	
	public void move(float deltaTime,float x,float y){
		for(GameObject o: objects){
			o.moveObject(deltaTime, speed);
			}
		diver.move(deltaTime);
		diver.moveonjoystick(x, y);	//wird implementiert
	}
	

	
	public void update(float deltaTime){
		//Diver auf Standardgeschwindigkeit (nachdem er verlangsamt wurde durch kollision)
		diver.refresh(speed);
		
		//Level aufbauen
		objectGen.nextRock(objects, deltaTime, distance);
		objectGen.nextPlant(objects, deltaTime);
		objectGen.nextShark(objects, deltaTime, distance);
		objectGen.nextTrash(objects, deltaTime, distance);
		objectGen.nextBoat(objects, deltaTime);
		objectGen.nextJellyfish(objects, deltaTime);
		objectGen.nextGasBottle(objects, deltaTime);
		

		
		
		//Kollisionsabfragen
		ArrayList<GameObject> collisions = Collision.checkCollision(diver, objects);
		for(GameObject o: collisions){
			if(o.getType() == ObjectType.TRASH && !o.isFading()){
				o.delete();
				score+=o.getTrashScore();
			}
			if(o.getType() == ObjectType.SHARK){
				bite.play();
				state.gameOver();
				break;
			}else if(o.getType() == ObjectType.BOAT){
				boathit.play(20f);
				state.gameOver();
				break;
			}else if (o.getType() == ObjectType.ROCK){
				state.gameOver();
				break;
			}
			else if(o.getType() == ObjectType.PLANT){
				if(o.alreadyhit == false){
					o.alreadyhit = true;
				}
				diver.slow(speed);
			}
			else if(o.getType() == ObjectType.JELLYFISH){
				if(o.getAlreadyhit() == false){
					o.setAlreadyhit(true);
					jellyfishhit.play(100f);
				}
				System.out.println(o.alreadyhit);
				diver.slow(speed);
				diver.setBreath(2000);
			}
			else if (o.getType() == ObjectType.GASBOTTLE){o.delete();
				gasbottlehit.play();
				diver.breathe(-4000);
			}	
		}
		
		
		//Luft updaten
		if(diver.getSprite().getY() + diver.getSprite().getHeight()>=950){diver.recover();}
		if(!infAir){diver.breathe(deltaTime);}
		if(!diver.hasAir()){state.gameOver();}
		
		//Score verwalten und Spielgeschwindigkeit anpassen
		distance += 10*speed*deltaTime;

		speed = (float) (0.001*distance+0.1);
		speed = (float) Math.min(speed, 1);
		
		
	}
	
	public float getSpeed(){
		return speed;
	}


	public int getScore() {
		return score;
	}
	
	public void reset(){
		distance = 0;
		score = 0;
		speed = 0.1f;
		objects.clear();
		diver.reset();
		
		objectGen.reset();
		startup.play();
	}
	
	public void setInfAir(){
		infAir = !infAir;
	}

}
