package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import Data.Animation;
import Data.Atext;
import Data.Click;
import Data.RECT;
import Data.ScriptReader;
import Data.ScriptSprite;
import Data.ScriptText;
import Data.Sprite;
import Data.Frame;
import FileIO.EZFileWrite;
import Graphics.Graphic;
import Graphics.MoveCharacter;
import Graphics.TileMap;
import FileIO.EZFileRead;
import Input.Mouse;
import logic.Control;
import particles.ParticleSystem;
import particles.Rain;
import script.Command;
import sound.Sound;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static String coord = "";             //coordinate tool
	private static int[] buffer;                 //some hypothetical game variables
	private static ScriptReader scriptReader;              
	private static ArrayList<ScriptSprite> scriptSprites;  
	private static ArrayList<ScriptText> scriptTexts;
	private static TileMap tileMap;
	private static Sprite sprMap1;    
	private static Animation robotAnim;
	//private static final int robotStep = 4;
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
		//scripting
		scriptReader = new ScriptReader("script.txt");
		scriptSprites = new ArrayList<ScriptSprite>(scriptReader.getScriptSprites());
		scriptTexts = new ArrayList<ScriptText>(scriptReader.getScriptTexts());
		
		//map1
		BufferedImage grassTile = ctrl.getSpriteFromBackBuffer("grass").getSprite();
		tileMap = new TileMap(grassTile);
		sprMap1 = tileMap.getSprite();
		
		//robot animations
		Animation botAnim = new Animation(100, false);
		MoveCharacter robotMove = new MoveCharacter(botAnim, 4, 0, 360, 1280, 360);
		robotAnim = robotMove.getAnimation();
		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)	
		
		//coordinate tool
		Point p = Mouse.getMouseCoords();
		coord = p.toString();                           //coordinate tool
		ctrl.drawString(500, 360, coord, Color.WHITE);  //coordinate tool
		
		//map1
		ctrl.addSpriteToFrontBuffer(sprMap1);
		
		//scripting
		if (!scriptSprites.isEmpty())
			for (ScriptSprite spr: scriptSprites) {
				BufferedImage buf = ctrl.getSpriteFromBackBuffer(spr.getTag()).getSprite();
				Sprite sprite = new Sprite(spr.getX(), spr.getY(), buf, spr.getTag());
				ctrl.addSpriteToFrontBuffer(sprite);
			}
		if (!scriptTexts.isEmpty())
			for (ScriptText txt: scriptTexts)
				ctrl.drawString(txt.getX(), txt.getY(), txt.getText(), txt.getColor());
		
		//robot animation
		Frame robotFrame = robotAnim.getCurrentFrame();
		if (robotFrame != null)
			ctrl.addSpriteToFrontBuffer(robotFrame.getX(), robotFrame.getY(), robotFrame.getSpriteTag());
		
	}
	
	// Additional Static methods below...(if needed)
	//create a routine to save the game data
	public static void saveData() {
		//save data to a String to output...
		String out = "";
		for (int i = 0; i < buffer.length; i++)
			out += buffer[i] + "*";
		out = out.substring(0, out.length() - 1);  //remove trailing delimiter
		//save output String to file
		EZFileWrite ezw = new EZFileWrite("save.txt");
		ezw.writeLine(out);
		ezw.saveFile();
	}
	
	//create a routine to restore the game data
	public static void loadData() {
		//retrieve data from the file
		EZFileRead ezr = new EZFileRead("save.txt");
		String raw = ezr.getLine(0);  //read our one and only line (index #0)
		//break this down into tokens
		StringTokenizer st = new StringTokenizer(raw, "*");
		if (st.countTokens() != buffer.length)
			return;  //these must match!!!
		for (int i = 0; i < buffer.length; i++) {
			String value = st.nextToken();
			int val = Integer.parseInt(value);
			buffer[i] = val;
		}
	}
}
