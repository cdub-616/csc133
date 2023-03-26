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
		double slope;
		int y = curY;
		int x = curX;
		if (changeX != 0)  //prevent divide by 0
			slope = Math.atan(changeY / changeX);  //convert slope to radians
		else slope = Math.PI / 2;
		double xStep = Math.abs((changeY) * Math.cos(slope));
		double yStep = Math.abs((changeY) * Math.sin(slope));
		if (slope >= (Math.atan(1))  || slope <= (Math.atan(-1))) {  //up or down animation
			if (changeY >= 0 && changeX >= 0) {     //down animation right
				for (y = curY; y < newY; y += yStep) {
					anima.addFrame(new Frame(x, y, "robDown" + frameCounter));
					x += xStep;
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
			}
			else if (changeY > 0 && changeX < 0) {  //down animation left
				for (x = curX; x > newX; x -= xStep) {
					anima.addFrame(new Frame(x, y, "robDown" + frameCounter));
					y += yStep;
						frameCounter++;
						if (frameCounter > 3)
							frameCounter = 0;
				}
			}
			else if (changeY < 0 && changeX >= 0) { //up animation right
				for (y = curY; y > newY; y -= yStep) {
					anima.addFrame(new Frame(x, y, "robUp" + frameCounter));
					x += xStep;
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
			} else {                                //up animation left
				for (x = curX; x > newX; x -= xStep) {
					anima.addFrame(new Frame(x, y, "robUp" + frameCounter));
					y -= yStep;
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
			}
		} else {                                    //left or right animation
			if (changeX >= 0 && changeY >= 0) {     //right animation down
				for (x = curX; x < newX; x += xStep) {
					anima.addFrame(new Frame(x, y, "robRight" + frameCounter));
					y += yStep;
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
			}
			else if (changeX > 0 && changeY < 0) {  //right animation up
				for (x = curX; x < newX; x += xStep) {
					anima.addFrame(new Frame(x, y, "robRight" + frameCounter));
					y -= yStep;
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
			}
			else if (changeX < 0 && changeY >= 0) { //left animation down
				for (x = curX; x > newX; x -= xStep) {
					anima.addFrame(new Frame(x, y, "robLeft" + frameCounter));
					y += yStep;
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
			} else {                                 //left animation up
				for (x = curX; x > newX; x -= xStep) {
					anima.addFrame(new Frame(x, y, "robLeft" + frameCounter));
					y -= yStep;
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
			}
		}
	}
	
	//methods
	public Animation getAnimation() {
		return anima;
	}
}
