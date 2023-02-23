package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Data.Sprite;
import logic.Control;
//import timer.stopWatchX;  //for module 0

public class Main{
	// Fields (Static) below...
	public static String clr = "";
	public static Sprite tree_s;
	//public static Sprite s;
	//public static Color c = new Color(255, 0, 0);         //for module 0
	//public static boolean isImageDrawn = false;           //for module 0
	//public static stopWatchX timer = new stopWatchX(250); //for module 0
	//public static String trigger = "";                    //for module 0
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		BufferedImage treeImage = ctrl.getSpriteFromBackBuffer("naked_tree").getSprite();
		BufferedImage buf2 = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < 10; i++) { //for top and bottom lines
			int x = i << 7; //*128
			int y = 0;
			int y2 = 592;
			Graphics g = buf2.getGraphics();
			g.drawImage(treeImage, x, y, null);
			g.drawImage(treeImage, x, y2, null);
		}
		for (int i = 0; i < 3; i++) {
			int x = 0;
			int x2 = 9 << 7;
			int y = 168 + (i << 7);
			Graphics g = buf2.getGraphics();
			g.drawImage(treeImage, x, y, null);
			g.drawImage(treeImage, x2, y, null);
		}
		Graphics g = buf2.getGraphics();
		Font temp = ctrl.getFont().deriveFont(Font.BOLD);
		g.setFont(temp);
		g.setColor(Color.GREEN);
		g.drawString("My tile-based tree level", 535, 360);
		g.dispose();
		tree_s = new Sprite (0, 0, buf2, "buf2");
		//Color c = new Color(pImage.getRGB(100,50)); //original eye-dropper tool
		//clr += c.getRed() + ", ";
		//clr += c.getGreen() + ", ";
		//clr += c.getBlue() + ", ";
		//int c = pImage.getRGB(100, 50);             //using bit-masking for eye-dropper
		//int a = (c >> 24) & 0xff;
		//int r = (c >> 16) & 0xff;
		//int g = (c >> 8) & 0xff;
		//int b = c & 0xff;
		//clr = a + ", " + r + ", " + g + ", " + b;
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		//if (isImageDrawn) //for module 0
		ctrl.addSpriteToFrontBuffer(0, 0, "naked_tree");						 				// Add a tester sprite to render list by tag (Remove later! Test only!)
		ctrl.drawString(20, 150, clr, Color.WHITE);					// Test drawing text on screen where you want (Remove later! Test only!)
		ctrl.addSpriteToFrontBuffer(tree_s);
		
		/*if (timer.isTimeUp()) { //for module 0
			isImageDrawn = !isImageDrawn;
			timer.resetWatch();
		}*/ 
	}
	
	// Additional Static methods below...(if needed)

}
