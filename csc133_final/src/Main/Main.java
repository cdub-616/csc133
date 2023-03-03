package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Data.Sprite;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static String s = "";
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		BufferedImage pImage = ctrl.getSpriteFromBackBuffer("f0").getSprite();
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		ctrl.addSpriteToFrontBuffer(0, 0, "f0");
		if (Control.getMouseInput() != null) {
			s = Control.getMouseInput().toString();
		}
		ctrl.drawString(20, 150, s, Color.WHITE);
	}
	
	// Additional Static methods below...(if needed)

}
