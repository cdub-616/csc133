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
		rs[0] = new RECT(275, 300, 334, 416, "Persephone"); //Persephone
		rs[1] = new RECT(571, 292, 702, 422, "tree");       //tree
		rs[2] = new RECT(949, 294, 1033, 434, "hero");      //hero
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)		
		ctrl.addSpriteToFrontBuffer(224, 296, "f0");
		ctrl.addSpriteToFrontBuffer(2*224 + 128, 296, "naked_tree");
		ctrl.addSpriteToFrontBuffer(3*224 + 2*128, 296, "hero");
		if (Control.getMouseInput() != null) {
			for (RECT r: rs) {
				if (r.isClicked(Control.getMouseInput(), Click.LEFT_BUTTON)) {
					s = r.getTag() + " was Clicked!";
					break;
				}
				else
					s = "";
			}
		}
		ctrl.drawString(550, 150, s, Color.WHITE);
	}
	
	// Additional Static methods below...(if needed)

}
