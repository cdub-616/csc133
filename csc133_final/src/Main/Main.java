package Main;

import java.awt.Color;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static Color c = new Color(255, 0, 0);
	public static boolean isImageDrawn = false;
	public static stopWatchX timer = new stopWatchX(250);
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		if (isImageDrawn)
			ctrl.addSpriteToFrontBuffer(0, 0, "star");						 				// Add a tester sprite to render list by tag (Remove later! Test only!)
		ctrl.drawString(20, 150, "This is a test graphics string!", c);					// Test drawing text on screen where you want (Remove later! Test only!)
		
		if (timer.isTimeUp()) {
			isImageDrawn = !isImageDrawn;
			timer.resetWatch();
		}
	}
	
	// Additional Static methods below...(if needed)

}
