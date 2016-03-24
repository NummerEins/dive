package com.dive.game;

import com.dive.game.Score;
import com.dive.game.Platform;
import com.dive.game.Highscores;

import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.nio.charset.Charset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;

//import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;

public class SaveGame {

	// TODO: Change topten to private
	private Json serializer;
	private Platform platform;
	
	public SaveGame()
	{
		serializer = new Json();
		serializer.setIgnoreUnknownFields(false);
		platform = Platform.HTML;
	}
	
	public SaveGame(Platform platform)
	{
		serializer = new Json();
		serializer.setIgnoreUnknownFields(false);
		this.platform = platform;
	}
	
	public String getSaveLocation() {
		if (platform == Platform.DESKTOP) {
			return System.getProperty("user.home") + File.separator +"highscores.json";
		}
		else if(platform == Platform.ANDROID){
			return Gdx.files.getLocalStoragePath() + File.separator +"highscores.json";
		}
		else{
			return "";
		}
		

	}
	
	public void dumpHighscores(Highscores highscores) {
		System.out.println(highscores);
	}
	
	public void saveHighscore(String location, Highscores highscores) {
		
		if (platform == Platform.HTML) {
			return;
		}  
		
		// Only save the set highscores, not the default-constructed ones
		String output = serializer.toJson(highscores.get(highscores.size()));
		FileHandle file = Gdx.files.local("highscores.json");
		
		
		try {
//			FileHandle.write(
//				Paths.get(location), 
//				output.getBytes(Charset.forName("UTF-8")), 
//				StandardOpenOption.CREATE
//			);
			file.writeString(output, false);

		} catch (GdxRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Highscores loadHighscore(String location) {
		
		if (platform == Platform.HTML) {
			return new Highscores();
		} 
		
		// TODO: Exception handling
		
		String input;
		// Create empty score array
		Score[] scores = {};
		
		FileHandle file = Gdx.files.internal("highscores.json");
		
		
		try {
//			input  = new String(Files.readAllBytes(Paths.get(location)));
			input = file.readString();
			scores = serializer.fromJson(Score[].class, input);
		} catch (GdxRuntimeException e) {
			// Print error message
			System.err.println("Failed to load file '" + location + "' Continue with default..");			
		}
		
		return new Highscores(scores);
	}
	
	//public static void main(String[] args) {
	//	
	//	System.out.println("### Creating Highscores ###");
	//	Score[] topten = {
	//		new Score("Füü", 777),
	//		new Score("Bar", 999),
	//		new Score("Baz", 888)
	//	};
	//	//Highscores highscores = new Highscores();
	//	//highscores.add(new Score("Füü", 777));
	//	//highscores.add(new Score("Bar", 999));
	//	//highscores.add(new Score("Baz", 888));
	//	Highscores highscores = new Highscores(topten);
	//			
	//	String location = new String("/home/mi/aiko/highscores.json");
	//	
	//	SaveGame saveGame = new SaveGame();
	//	
	//	System.out.println("### Highscores ###");
	//	
	//	//System.out.println(highscores);
	//	for (Score score : topten) {
	//		System.out.println(score);
	//	}
	//	
	//	System.out.println("### Saving files to '" + location + "' ###");
	//	
	//	saveGame.saveHighscore(location, highscores);
	//	
	//	System.out.println("### Reading file from '" + location + "' ###");
	//	saveGame.loadHighscore(location);
	//	
	//	System.out.println("### Highscores ###");
	//	
	//	saveGame.dumpHighscores(highscores);
	//}
	
}
