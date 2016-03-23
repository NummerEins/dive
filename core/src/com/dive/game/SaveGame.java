package com.dive.game;

import com.dive.game.Score;
import com.dive.game.Highscores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.charset.Charset;
import com.badlogic.gdx.utils.Json;

public class SaveGame {

	// TODO: Change topten to private
	private Json serializer;
	
	public SaveGame()
	{
		serializer = new Json();
		serializer.setIgnoreUnknownFields(false);
	}
	
	public void dumpHighscores(Highscores highscores) {
		System.out.println(highscores);
	}
	
	public void saveHighscore(String location, Highscores highscores) {
		
		// Only save the set highscores, not the default-constructed ones
		String output = serializer.toJson(highscores.get(highscores.size()));
		
		try {
			Files.write(
				Paths.get(location), 
				output.getBytes(Charset.forName("UTF-8")), 
				StandardOpenOption.CREATE
			);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Highscores loadHighscore(String location) {
		
		// TODO: Exception handling
		
		String input;
		// Create empty score array
		Score[] scores = {};
		
		try {
			input  = new String(Files.readAllBytes(Paths.get(location)));
			scores = serializer.fromJson(Score[].class, input);
		} catch (IOException e) {
			// Print error message
			System.err.println(String.format("Failed to load file '" + location + "'%%nContinue with default.."));			
		}
		
		return new Highscores(scores);
	}
	
	public static void main(String[] args) {
		
		System.out.println("### Creating Highscores ###");
		Score[] topten = {
			new Score("Füü", 777),
			new Score("Bar", 999),
			new Score("Baz", 888)
		};
		//Highscores highscores = new Highscores();
		//highscores.add(new Score("Füü", 777));
		//highscores.add(new Score("Bar", 999));
		//highscores.add(new Score("Baz", 888));
		Highscores highscores = new Highscores(topten);
				
		String location = new String("/home/mi/aiko/highscores.json");
		
		SaveGame saveGame = new SaveGame();
		
		System.out.println("### Highscores ###");
		
		//System.out.println(highscores);
		for (Score score : topten) {
			System.out.println(score);
		}
		
		System.out.println("### Saving files to '" + location + "' ###");
		
		saveGame.saveHighscore(location, highscores);
		
		System.out.println("### Reading file from '" + location + "' ###");
		saveGame.loadHighscore(location);
		
		System.out.println("### Highscores ###");
		
		saveGame.dumpHighscores(highscores);
	}
	
}
