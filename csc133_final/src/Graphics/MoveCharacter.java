package Graphics;

import Data.Animation;
import Data.Frame;

public class MoveCharacter {
	//fields
	Animation anima;
	
	//constructor
	public MoveCharacter(Animation anima, int step, int curX, int curY, int newX, int newY) {
		this.anima = anima;
		int frameCounter = 0;
		int changeX = newX - curX;
		int changeY = newY - curY;
		double slope = 0.0;
		int y = curY;
		if (changeX != 0)  //prevent divide by 0
			slope = changeY / changeX;
		else slope = changeY;
		double xStep = Math.abs(step * Math.cos(slope));
		double yStep = Math.abs(step * Math.sin(slope));
		if (slope > 1 || slope < -1)              //up or down animation
			if (changeY > 0 && changeX > 0)       //down animation right
				for (int x = curX; x < newX; x += xStep) {
					anima.addFrame(new Frame(x, y, "robDown" + frameCounter));
					y += yStep;
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
			else if (changeY > 0 && changeX < 0) { //down animation left
				for (int x = curX; x > newX; x -= xStep) {
					anima.addFrame(new Frame(x, y, "robDown" + frameCounter));
					y += yStep;
						frameCounter++;
						if (frameCounter > 3)
							frameCounter = 0;
				}
			}
			else if (changeY < 0 && changeX > 0)  //up animation right
				for (int x = curX; x < newX; x += xStep) {
					anima.addFrame(new Frame(x, y, "robUp" + frameCounter));
					y -= yStep;
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
			else                                  //up animation left
				for (int x = curX; x > newX; x -= xStep) {
					anima.addFrame(new Frame(x, y, "robUp" + frameCounter));
					y -= yStep;
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
		else                                      //left or right animation
			if (changeX > 0 && changeY > 0)       //right animation down
				for (int x = curX; x < newX; x += xStep) {
					anima.addFrame(new Frame(x, y, "robRight" + frameCounter));
					y += yStep;
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
			else if (changeX > 0 && changeY < 0)  //right animation up
				for (int x = curX; x < newX; x += xStep) {
					anima.addFrame(new Frame(x, y, "robRight" + frameCounter));
					y -= yStep;
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
			else if (changeX < 0 && changeY > 0)  //left animation down
				for (int x = curX; x > newX; x -= xStep) {
					anima.addFrame(new Frame(x, y, "robLeft" + frameCounter));
					y += yStep;
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
			else                                  //left animation up
				for (int x = curX; x < newX; x += xStep) {
					anima.addFrame(new Frame(x, y, "robLeft" + frameCounter));
					y -= yStep;
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
	}
	
	//methods
	public Animation getAnimation() {
		return anima;
	}
}
