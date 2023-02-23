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
	public static String clr = "";
	public static Sprite tree_s;
	public static Sprite s; //for module 4
	//public static Color c = new Color(255, 0, 0);         //for module 0
	//public static boolean isImageDrawn = false;           //for module 0
	//public static stopWatchX timer = new stopWatchX(250); //for module 0
	//public static String trigger = "";                    //for module 0
	public static Color c = new Color(255, 0, 0);
	public static boolean isImageDrawn = false;
	public static stopWatchX timer = new stopWatchX(250);
	
	public static String trigger = "";
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		//BufferedImage treeImage = ctrl.getSpriteFromBackBuffer("naked_tree").getSprite();
		//BufferedImage tree_buf = treeImage.getSubimage(0, 0, 128, 128);
		//Graphics g = tree_buf.getGraphics();
		//tree_s = new Sprite(0, 0, treeImage, "naked_tree");
		//BufferedImage pImage = ctrl.getSpriteFromBackBuffer("f0").getSprite(); //for module 4
		//BufferedImage bi2 = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_ARGB); //blank canvas
		//Graphics g = bi2.getGraphics();
		//Font temp = ctrl.getFont(); //.deriveFont(Font.BOLD); //makes print BOLD
		//g.setFont(temp);
		//g.setColor(Color.red);
		//g.drawString("Tester string direct", 500, 300);
		//g.dispose();
		//s = new Sprite(0, 0, bi2, "bi2");
		//BufferedImage bi2 = pImage.getSubimage(0, 0, 64, 64);
		//s = new Sprite(200, 50, bi2, "psub");
		/*for (int i = 0; i < 10; i++) { //draw copies
			int x = i << 7; // *128                                     
			int y = 128;                                                
			BufferedImage pCopy = pImage.getSubimage(0, 0, 128, 128);   
			Graphics g = bi2.getGraphics();                             
			g.drawImage(pCopy, x, y, null);                            
		}*/
		//s = new Sprite(0, 0, bi2, "bi2");
		//Graphics g = bi2.getGraphics(); //go back to clean canvas                     
		//g.setColor(Color.black);
		//g.fillRect(0, 0, bi2.getWidth(), bi2.getHeight());
		//s = new Sprite(0, 0, bi2, "bi2");
		//Graphics g = bi2.getGraphics();      //draw duplicate image
		//g.drawImage(pImage, 128, 128, null); //draw duplicate image
		//bi2.setRGB(0, 0, 0xffffffff);  // Writes a pure white pixel at 0,0 of the canvas
		//BufferedImage bi2 = pImage.getSubimage(0, 0, 64, 64); //for subImage demo
		//Color c = new Color(pImage.getRGB(100,50)); //original eye-dropper tool
		//clr += c.getRed() + ", ";
		//clr += c.getGreen() + ", ";
		//clr += c.getBlue() + ", ";
		//int c = pImage.getRGB(100, 50);  //using bit-masking for eye-dropper
		//int a = (c >> 24) & 0xff;
		//int r = (c >> 16) & 0xff;
		//int g = (c >> 8) & 0xff;
		//int b = c & 0xff;
		//clr = a + ", " + r + ", " + g + ", " + b;
	}
	public static void start(){}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		//if (isImageDrawn) //for module 0
		ctrl.addSpriteToFrontBuffer(0, 0, "f0");						 				// Add a tester sprite to render list by tag (Remove later! Test only!)
		ctrl.drawString(20, 150, clr, Color.WHITE);					// Test drawing text on screen where you want (Remove later! Test only!)
		//ctrl.addSpriteToFrontBuffer(0, 0, "naked_tree");
		//ctrl.addSpriteToFrontBuffer(s); //for module 4
		if (isImageDrawn)
			ctrl.addSpriteToFrontBuffer(0, 0, "star");						 				// Add a tester sprite to render list by tag (Remove later! Test only!)
		ctrl.drawString(20, 150, trigger, c);					// Test drawing text on screen where you want (Remove later! Test only!)
		
		if (timer.isTimeUp()) {
			isImageDrawn = !isImageDrawn;
			timer.resetWatch();
		}
	}
	
	// Additional Static methods below...(if needed)

}
