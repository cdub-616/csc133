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
import sound.Sound;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	//public static String coord = "";  //coordinate tool
	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)	
		ctrl.addSpriteToFrontBuffer(0, 0, "f0");  //add Persephone
		//Point p = Mouse.getMouseCoords();
		//coord = p.toString();  //coordinate tool
		//ctrl.drawString(500, 360, coord, Color.WHITE);  //coordinate tool
	}
	
	// Additional Static methods below...(if needed)

}
