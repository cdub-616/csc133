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
	public static String s = "";
	public static String s2 = "";
	public static ArrayList<Command> commands;
	private static int[] buffer;  //some hypothetical game variables
	/*private static RECT disk;
	private static int dropShadow = 2;*/
	/*public static Command c;
	public static Command c2;*/
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		/*String raw = "show_sprite: 100, 100, f0";
		String raw2 = "text: Howdy partner!";
		c = new Command(raw);
		c2 = new Command(raw2);*/
		/*disk = new RECT(101, 52, 162, 112, "savetag", "Save Game");
		buffer = new int[40];  //initialize game vars
		//assign random values to the buffer for testing
		for (int i = 0; i < buffer.length; i++) {
			int value = (int)(Math.random() * 100);
			buffer[i] = value;
		}*/
		EZFileRead ezr = new EZFileRead("script.txt");
		commands = new ArrayList<>();
		for (int i = 0; i < ezr.getNumLines(); i++) {
			String raw = ezr.getLine(i);
			raw = raw.trim();
			if (!raw.equals("")) {
				boolean b = raw.charAt(0) == '#';
				if (!b)
					commands.add(new Command(raw));
			}
		}
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)	
		/*if (c.isCommand("show_sprite") && c.getNumParms() == 3) {
			int x = Integer.parseInt(c.getParmByIndex(0));
			int y = Integer.parseInt(c.getParmByIndex(1));
			String tag = c.getParmByIndex(2);
			ctrl.addSpriteToFrontBuffer(x, y, tag);
		}
		if (c2.isCommand("text") && c2.getNumParms() == 1) {
			String display = c2.getParmByIndex(0);
			ctrl.drawString(0, 250, display, Color.WHITE);
		}*/
		//Point p = Mouse.getMouseCoords();
		//coord = p.toString();  //coordinate tool
		//ctrl.drawString(500, 360, coord, Color.WHITE);  //coordinate tool
		/*ctrl.addSpriteToFrontBuffer(100, 50, "save");  //display the save icon
		//for hover...
		Point p = Mouse.getMouseCoords();
		int x = (int)p.getX();
		int y = (int)p.getY();
		if (disk.isCollision(x, y))
			s = disk.getHoverLabel();
		else
			s = "";
		ctrl.drawString(x, y - 2, s, Color.BLACK);
		ctrl.drawString(x - dropShadow, y - 2 - dropShadow, s, Color.YELLOW);
		//for mouse polling...
		if (Control.getMouseInput() != null) {
			if (disk.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
				saveData();
				s2 = "Game Saved";
			}
		}	
		ctrl.drawString(0, 200, s2, Color.WHITE);  //display saved game message when applicable*/
		for (Command c: commands) {
			if (c.isCommand("show_sprite") && c.getNumParms() == 3) {
				int x = Integer.parseInt(c.getParmByIndex(0));
				int y = Integer.parseInt(c.getParmByIndex(1));
				String tag = c.getParmByIndex(2);
				ctrl.addSpriteToFrontBuffer(x, y, tag);
			}
			else if (c.isCommand("text") && c.getNumParms() == 1) {
				String display = c.getParmByIndex(0);
				ctrl.drawString(0, 250, display, Color.WHITE);
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
