package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import Data.Animation;
import Data.Atext;
import Data.Click;
import Data.RECT;
import Data.Sprite;
import Data.Frame;
import FileIO.EZFileWrite;
import Graphics.Graphic;
import FileIO.EZFileRead;
import Input.Mouse;
import logic.Control;
import particles.ParticleSystem;
import particles.Rain;
import script.Command;
import script.ScriptReader;
import script.ScriptRectTextHover;
import script.ScriptSprite;
import script.ScriptText;
import script.ScriptTextShadow;
import sound.Sound;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	//public static String coord = "";  //coordinate tool
	
	//variables for scripting
	private static ArrayList<ScriptSprite> scriptSprites;  
	private static ArrayList<ScriptText> scriptTexts;
	private static ArrayList<ScriptTextShadow> scriptTextShadows;
	private static ArrayList<ScriptRectTextHover> scriptRectTextHovers;
	private static ScriptReader scriptReader; 
	
	private static int[] buffer;  //some hypothetical game variables

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
		
		//variables for scripting
		int x = (int)p.getX(), y = (int)p.getY(), shadow = 0;
		RECT rect = new RECT();
		ScriptText rText = new ScriptText();
		ScriptText rShadow = new ScriptText();
		ArrayList<RECT> rectArray = new ArrayList<>();
		Vector<Integer> intVec = new Vector<>();
		ArrayList<ScriptText> rTArray = new ArrayList<>();
		ArrayList<ScriptText> rSArray = new ArrayList<>();	
		boolean sprites = false, texts = false, textShadows = false, rectTextHovers = false;
		
		//scripting
		if (!scriptSprites.isEmpty() && sprites == false) {
			sprites = true;
			for (ScriptSprite spr: scriptSprites) {
				BufferedImage buf = ctrl.getSpriteFromBackBuffer(spr.getTag()).getSprite();
				Sprite sprite = new Sprite(spr.getX(), spr.getY(), buf, spr.getTag());
				ctrl.addSpriteToFrontBuffer(sprite);
			}
		}
		if (!scriptTexts.isEmpty() && texts == false) {
			texts = true;
			for (ScriptText txt: scriptTexts) {
				ctrl.drawString(txt.getX(), txt.getY(), txt.getText(), txt.getColor());
			}
		}
		if (!scriptTextShadows.isEmpty() && textShadows == false) {
			textShadows = true;
			texts = true;
			for (ScriptTextShadow txt: scriptTextShadows) {
				ScriptText text = txt.getText();
				ScriptText shad = txt.getShadow();
				ctrl.drawString(shad.getX(), shad.getY(), shad.getText(), shad.getColor());
				ctrl.drawString(text.getX(), text.getY(), text.getText(), text.getColor());		
			}
		}
		if (!scriptRectTextHovers.isEmpty() && rectTextHovers == false) {
			rectTextHovers = true;
			for (ScriptRectTextHover hover: scriptRectTextHovers) {
				rectArray.add(hover.getRect());
				intVec.add(hover.getShadow());
				ScriptTextShadow textShadow = hover.getScriptTextShadow();
				rTArray.add(textShadow.getText());
				rSArray.add(textShadow.getShadow());
			}
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
