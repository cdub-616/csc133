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
	public static String s = "";
	public static Animation anim;
	public static final int screenWidth = 1280;
	public static final int step = 12;
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		anim = new Animation(80, false);
		int frameCounter = 0;
		for (int x = -128; x < (screenWidth + 128); x+= step) {
			anim.addFrame(new Frame(x, 0, "c" + frameCounter));
			frameCounter++;
			if (frameCounter > 3)
				frameCounter = 0;
		}
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)		
		Frame curFrame = anim.getCurrentFrame();
		if (curFrame != null)
			ctrl.addSpriteToFrontBuffer(curFrame.getX(), curFrame.getY(), curFrame.getSpriteTag());
		if (anim.isFinished())
			ctrl.drawString(0, 150, "Animation is finished!", Color.YELLOW);
	}
	
	// Additional Static methods below...(if needed)

}
