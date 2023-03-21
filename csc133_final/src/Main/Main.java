package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;

import Data.Click;
import Data.RECT;
import Data.Sprite;
import Input.Mouse;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static String s = "";
	public static RECT r;
	public static final int dropShadow = 2;
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		r = new RECT(46, 3, 107, 123, "Persephone", "Princess of Eden's Realm");
	}
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)		
		ctrl.addSpriteToFrontBuffer(0, 0, "f0");  //add a tester sprite to render list by tag
		Point p = Mouse.getMouseCoords();
		int x = (int)p.getX();
		int y = (int)p.getY();
		if (r.isCollision(x, y))
			s = r.getHoverLabel();
		else
			s="";
		ctrl.drawString(x, (y - 2), s, Color.BLACK);
		ctrl.drawString(x - dropShadow, y - dropShadow - 2, s, Color.YELLOW);
	}
	
	// Additional Static methods below...(if needed)

}
