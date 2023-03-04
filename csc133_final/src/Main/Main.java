package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Data.Click;
import Data.RECT;
import Data.Sprite;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static String s = "";
	public static RECT [] rs;                       //Declare array of RECT objects
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		rs = new RECT[3];
		rs[0] = new RECT(46, 3, 107, 123, "Persephone");         //Persephone
		rs[1] = new RECT(0, 0, 1279, 359, "Top space");          //Space on top half of screen
		rs[2] = new RECT(0, 360, 1279, 719, "Bottom space");      //Space on bottom half of screen
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)		
		ctrl.addSpriteToFrontBuffer(0, 0, "f0");
		if (Control.getMouseInput() != null) {
			for (RECT r: rs) {
				if (r.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
					s = r.getTag() + " was Clicked!";
					break;
				}
			}
		}
		ctrl.drawString(20, 150, s, Color.WHITE);
	}
	
	// Additional Static methods below...(if needed)

}
