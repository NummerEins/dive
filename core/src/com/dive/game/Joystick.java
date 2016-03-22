package com.dive.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Joystick{
	private Touchpad joystick;
	private Drawable knob;
	private Drawable background;
	private Drawable checkboxOff;
	private Drawable checkboxOn;
	private TouchpadStyle joystickstyle;
	private Skin Joystickskin;
	private Skin Checkboxskin;
	private CheckBoxStyle checkboxstyle;
	private CheckBox checkbox;
	private BitmapFont touch;
	private Color color;
	private Table table;
	private Stage stage;
	
	public Joystick(Stage stage, BitmapFont font){
		
		table = new Table();
		this.stage = stage;
		
		this.Joystickskin = new Skin();										//Ein Skin wird erzeugt um aus Texture Dateien Drawables zu machen
		this.Joystickskin.add("background",Assets.getInstance().joystickunder);
    	this.Joystickskin.add("knob",Assets.getInstance().joystickup);
		this.background = Joystickskin.getDrawable("background");
    	this.knob = Joystickskin.getDrawable("knob");
    	this.joystickstyle = new TouchpadStyle(background,knob);
    	
    	
		this.knob.setMinWidth(125);						//Größe des Joysticks
		this.knob.setMinHeight(125);
		
		this.joystick = new Touchpad(5,joystickstyle);	//Joystick wird erstellt mit Bewegungsradius des Knüppels = 1/10 des Bildschirms
		this.joystick.setBounds(54,  5 ,180, 180);//Größe und Platzierung des Joystickpads
    	
		
    	this.Checkboxskin = new Skin();	//Ein Skin wird erzeugt um aus Texture Dateien Drawables zu machen
		this.Checkboxskin.add("checkboxOff",Assets.getInstance().joystickunder);
    	this.Checkboxskin.add("checkboxOn",Assets.getInstance().joystickup);
		this.checkboxOff = Checkboxskin.getDrawable("checkboxOff");
    	this.checkboxOn = Checkboxskin.getDrawable("checkboxOn");
    	
    	
    	this.touch = font;
    	this.color = Color.BLACK;
    	
    	this.checkboxstyle= new CheckBoxStyle(this.checkboxOn,this.checkboxOff,this.touch,this.color);
		this.checkbox = new CheckBox("a",this.checkboxstyle);
		this.checkbox.setBounds(1850, 900, 100, 100);
	

	
	}
	
	public void addActors(){
		table.addActor(checkbox);
		stage.addActor(table);
		stage.addActor(joystick);
		
	}
	public Touchpad getJoystick(){
		return this.joystick;
	}
	public CheckBox getCheckbox(){
		return this.checkbox;
	}
	public Skin getCheckboxskin(){
		return this.Checkboxskin;
	}
}
	
