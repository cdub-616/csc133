package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;

import Data.Animation;
import Data.Click;
import Data.RECT;
import Data.Sprite;
import Data.Frame;
import Input.Mouse;
import logic.Control;
import script.Command;
import sound.Sound;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	//public static String coord = "";  //coordinate tool
	public static String s = "";
	public static Command c;
	public static Command c2;
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		String raw = "show_sprite: 100, 100, f0";
		String raw2 = "text: Howdy partner!";
		c = new Command(raw);
		c2 = new Command(raw2);
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)	
		if (c.isCommand("show_sprite") && c.getNumParms() == 3) {
			int x = Integer.parseInt(c.getParmByIndex(0));
			int y = Integer.parseInt(c.getParmByIndex(1));
			String tag = c.getParmByIndex(2);
			ctrl.addSpriteToFrontBuffer(x, y, tag);
		}
		if (c2.isCommand("text") && c2.getNumParms() == 1) {
			String display = c2.getParmByIndex(0);
			ctrl.drawString(0, 250, display, Color.WHITE);
		}
		//Point p = Mouse.getMouseCoords();
		//coord = p.toString();  //coordinate tool
		//ctrl.drawString(500, 360, coord, Color.WHITE);  //coordinate tool
	}
	// Additional Static methods below...(if needed)

}
