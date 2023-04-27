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
import Data.Sprite;
import Data.Frame;
import Data.MoveSprite;
import FileIO.EZFileWrite;
import Graphics.Graphic;
import Graphics.TileMap;
import FileIO.EZFileRead;
import Input.Mouse;
import logic.Control;
import particles.ParticleSystem;
import particles.Rain;
import script.Command;
import script.ScriptObstacle;
import script.ScriptReader;
import script.ScriptSprite;
import script.ScriptText;
import sound.Sound;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static String coord = "";             //coordinate tool
	private static int[] buffer;                 //some hypothetical game variables
	private static String[] myRobotTags;
	private static ScriptReader scriptReader;              
	private static ArrayList<ScriptSprite> scriptSprites = new ArrayList<>();  
	private static ArrayList<ScriptText> scriptTexts = new ArrayList<>();
	private static ArrayList<ScriptObstacle> scriptObstacles = 
		new ArrayList<>();
	private static TileMap tileMap;
	private static Sprite sprMap1;   
	private static MoveSprite robotMove;
	private static Animation robotAnim;
	private static int startX = 50, startY = 650, curX, curY, newX, newY;
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
		scriptSprites = scriptReader.getScriptSprites();
		scriptTexts = scriptReader.getScriptTexts();
		scriptObstacles = scriptReader.getScriptObstacles();
		
		//map1
		BufferedImage grassTile = ctrl.getSpriteFromBackBuffer("grass").getSprite();
		tileMap = new TileMap(grassTile);
		sprMap1 = tileMap.getSprite();
		
		//robot animations
		curX = startX;
		curY = startY;
		newX = startX;
		newY = startY;
		myRobotTags = new String[]{"robDown", "robUp", "robRight", "robLeft"};
		Animation botAnim = new Animation(100, false);
		robotMove = new MoveSprite(myRobotTags, botAnim, 10, curX, curY, 
			startX, startY);
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
				BufferedImage buf = ctrl.getSpriteFromBackBuffer(spr.getTag())
					.getSprite();
				Sprite sprite = new Sprite(spr.getX(), spr.getY(), buf, 
					spr.getTag());
				ctrl.addSpriteToFrontBuffer(sprite);
			}
		if (!scriptObstacles.isEmpty())
			for (ScriptObstacle obs: scriptObstacles) {
				BufferedImage buf = ctrl.getSpriteFromBackBuffer(obs.getSTag())
					.getSprite();
				Sprite sprite = new Sprite(obs.getX(), obs.getY(), buf,
					obs.getSTag());
				ctrl.addSpriteToFrontBuffer(sprite);
				RECT rect = new RECT(obs.getX(), obs.getY(), obs.getX() + 
					obs.getObSize(), obs.getY() + obs.getObSize(), 
					obs.getRTag());
			}
		if (!scriptTexts.isEmpty())
			for (ScriptText txt: scriptTexts)
				ctrl.drawString(txt.getX(), txt.getY(), txt.getText(), txt.getColor());
		
		//robot animation
		if (Control.getMouseInput() != null) {
			Click click = Control.getMouseInput();
			if (click.getButton() == 1)	{
				newX = (int)p.getX();
				newY = (int)p.getY();
			}
		}
		if (!robotMove.compareCoords(newX, newY)) {
			Animation anim = robotMove.getAnimation();
			int stp = robotMove.getStep();
			robotMove = new MoveSprite(myRobotTags, anim, stp, curX, curY, newX, 
				newY);
			robotAnim = robotMove.getAnimation();	
			}
		
		Frame robotFrame = robotAnim.getCurrentFrame();
		if (robotFrame != null) {
			ctrl.addSpriteToFrontBuffer(robotFrame.getX(), robotFrame.getY(), 
				robotFrame.getSpriteTag());
		}
		curX = robotFrame.getX();
		curY = robotFrame.getY();
		int myBotSize = 32;
		RECT mybot = new RECT(curX, curY, curX + myBotSize, curY + myBotSize,
			"myBotRECT");
		
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
