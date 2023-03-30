package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Data.Animation;
import Data.Click;
import Data.RECT;
import Data.ScriptReader;
import Data.ScriptRectTextHover;
import Data.ScriptSprite;
import Data.ScriptText;
import Data.ScriptTextShadow;
import Data.Sprite;
import Data.Frame;
import FileIO.EZFileWrite;
import FileIO.EZFileRead;
import Input.Mouse;
import logic.Control;
import script.Command;
import sound.Sound;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	//public static String coord = "";  //coordinate tool
	private static ArrayList<ScriptSprite> scriptSprites;  
	private static ArrayList<ScriptText> scriptTexts;
	private static ArrayList<ScriptTextShadow> scriptTextShadows;
	private static ArrayList<ScriptRectTextHover> scriptRectTextHovers;
	private static int[] buffer;  //some hypothetical game variables
	private static ScriptReader scriptReader; 
	private static String perString = "";
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
		scriptTextShadows = new ArrayList<ScriptTextShadow>(scriptReader.getScriptTextShadows());
		scriptRectTextHovers = new ArrayList<ScriptRectTextHover>(scriptReader.getScriptRectTextHover());	
		scriptTexts = new ArrayList<ScriptText>(scriptReader.getScriptTexts());
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)	
		
		Point p = Mouse.getMouseCoords();
		/*coord = p.toString();  //coordinate tool
		ctrl.drawString(500, 360, coord, Color.WHITE);  //coordinate tool*/
		
		int x = (int)p.getX();
		int y = (int)p.getY();
		
		//scripting
		if (!scriptSprites.isEmpty())
			for (ScriptSprite spr: scriptSprites) {
				BufferedImage buf = ctrl.getSpriteFromBackBuffer(spr.getTag()).getSprite();
				Sprite sprite = new Sprite(spr.getX(), spr.getY(), buf, spr.getTag());
				ctrl.addSpriteToFrontBuffer(sprite);
			}
		if (!scriptTexts.isEmpty())
			for (ScriptText txt: scriptTexts) {
				ctrl.drawString(txt.getX(), txt.getY(), txt.getText(), txt.getColor());
			}
		if (!scriptTextShadows.isEmpty())
			for (ScriptTextShadow txt: scriptTextShadows) {
				ScriptText text = txt.getText();
				ScriptText shadow = txt.getShadow();
				ctrl.drawString(shadow.getX(), shadow.getY(), shadow.getText(), shadow.getColor());
				ctrl.drawString(text.getX(), text.getY(), text.getText(), text.getColor());		
			}
		if (!scriptRectTextHovers.isEmpty())
			for (ScriptRectTextHover hover: scriptRectTextHovers) {
				RECT rect = hover.getRect();
				int shadow = hover.getShadow();
				ScriptTextShadow textShadow = hover.getScriptTextShadow();
				ScriptText rText = textShadow.getText();
				ScriptText rShadow = textShadow.getShadow();
				if (rect.isCollision(x, y))  //check for chicken collision
					perString = rect.getHoverLabel();
				else
					perString = "";
				ctrl.drawString(x, y, perString, rShadow.getColor());
				ctrl.drawString(x - shadow, y - shadow, perString, rText.getColor());
			}
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
