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
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	//public static String coord = "";  //coordinate tool
	public static String per_str = "";
	public static String chick_str = "";
	public static int dropShadow = 2;
	public static int per_x = 1280/3 - 256;
	public static int per_y = 720/2 - 64;
	public static int chick_x = (1280/3) * 2 + 128;
	public static int chick_y = per_y;
	public static RECT per_rect;
	public static RECT chick_rect;
	public static Animation anim;	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		per_rect = new RECT (220, 302, 280, 417, "Persephone", new Frame(per_x, per_y, "f1"));  //Persephone RECT
		chick_rect = new RECT (986, 298, 1094, 419, "Chicken");                                  //chicken RECT
		anim = new Animation(120, true);
		for (int i = 0; i < 4; i++)
			anim.addFrame(new Frame(chick_x, chick_y, "c" + i));
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)	
		ctrl.addSpriteToFrontBuffer(per_x, per_y, "f0");  //add Persephone
		Frame curFrame = anim.getCurrentFrame();
		if (curFrame != null)                             //add chicken 
			ctrl.addSpriteToFrontBuffer(curFrame.getX(), curFrame.getY(), curFrame.getSpriteTag());
		Point p = Mouse.getMouseCoords();
		//coord = p.toString();  //coordinate tool
		int x = (int)p.getX();
		int y = (int)p.getY();
		if (per_rect.isCollision(x, y))                            //check for Persephone collision
			ctrl.addSpriteToFrontBuffer(per_rect.getGraphicalHover().getX(), per_rect.getGraphicalHover().getY(), per_rect.getGraphicalHover().getSpriteTag());
		else
			per_str = "";
		if (chick_rect.isCollision((int)p.getX(), (int)p.getY()))  //check for chicken collision
			chick_str = chick_rect.getTag();
		else
			chick_str = "";
		//ctrl.drawString(500, 360, coord, Color.WHITE);  //coordinate tool
		ctrl.drawString(x, y - 2, chick_str, Color.BLACK);
		ctrl.drawString(x - dropShadow, y - 2 - dropShadow, chick_str, Color.RED);
	}
	
	// Additional Static methods below...(if needed)

}
