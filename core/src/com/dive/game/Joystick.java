package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
	private Drawable checkboxOff1;
	private Drawable checkboxOn1;
	private Skin Checkboxskin1;
	private TextButtonStyle checkboxstyle1;
	private TextButton checkbox1;
	private BitmapFont touch1;
	private Table table;
	private Stage stage;
	private boolean side;
	private int val;
	private float skalar;
	
	public Joystick(Stage stage, BitmapFont font){
		
		this.skalar = (float)Gdx.graphics.getWidth()/1920f;
		table = new Table();
		this.stage = stage;

		val = side? 1 : 0;

		this.Joystickskin = new Skin();										//Ein Skin wird erzeugt um aus Texture Dateien Drawables zu machen
		this.Joystickskin.add("background",Assets.getInstance().joystickunder);
    	this.Joystickskin.add("knob",Assets.getInstance().joystickup);
		this.background = Joystickskin.getDrawable("background");
    	this.knob = Joystickskin.getDrawable("knob");
    	this.joystickstyle = new TouchpadStyle(background,knob);
    	
    	
		this.knob.setMinWidth(175f*skalar);						//Größe des Joysticks
		this.knob.setMinHeight(175f*skalar);
		
		this.joystick = new Touchpad(5,joystickstyle);	//Joystick wird erstellt mit Bewegungsradius des Knüppels = 1/10 des Bildschirms
		this.joystick.setBounds((-1575f*(val-1) + 50)*skalar,  35*skalar ,250*skalar, 250*skalar);//Größe und Platzierung des Joystickpads
    	
		
    	this.Checkboxskin = new Skin();	//Ein Skin wird erzeugt um aus Texture Dateien Drawables zu machen
		this.Checkboxskin.add("checkboxOff",Assets.getInstance().joystickon);
    	this.Checkboxskin.add("checkboxOn",Assets.getInstance().joystickoff);
		this.checkboxOff = Checkboxskin.getDrawable("checkboxOff");
    	this.checkboxOn = Checkboxskin.getDrawable("checkboxOn");
    
    	this.touch = font;
    	this.color = Color.BLACK;
    	
    	this.checkboxstyle= new CheckBoxStyle(this.checkboxOn,this.checkboxOff,this.touch,this.color);
		this.checkbox = new CheckBox("",this.checkboxstyle);
		this.checkbox.setBounds(1575*skalar, 900*skalar, 300*skalar, 50*skalar);
		
		this.Checkboxskin1 = new Skin();	//Ein Skin wird erzeugt um aus Texture Dateien Drawables zu machen
		this.Checkboxskin1.add("checkboxOff",Assets.getInstance().joystickunder);
    	this.Checkboxskin1.add("checkboxOn",Assets.getInstance().joystickunder);
		this.checkboxOff1 = Checkboxskin1.getDrawable("checkboxOff");
    	this.checkboxOn1 = Checkboxskin1.getDrawable("checkboxOn");
    	
    	this.touch1 = font;
    	this.checkboxstyle1= new TextButtonStyle(checkboxOn1,checkboxOff1,checkboxOn1, touch1);
		this.checkbox1 = new TextButton("",this.checkboxstyle1);
		this.checkbox1.setBounds((1575*val + 50)*skalar, 35*skalar, 250*skalar, 250*skalar);
	
	}
	public Float getSkalar(){
		return this.skalar;
	}
	public Joystick(){
		this.side=true;
	}
	public boolean moveCheckbox1(){
		if(side){
			return false;
		}else{
			return true;
		}
	}
	
	public void addActors(){
		table.addActor(checkbox1);
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
	public TextButton getCheckbox1(){
		return this.checkbox1;
	}
	public Skin getCheckboxskin(){
		return this.Checkboxskin;
	}
	public void moveForReal(){
		if(side){
			this.side = false;
		}else{
			this.side = true;
		}
	}
}
	
