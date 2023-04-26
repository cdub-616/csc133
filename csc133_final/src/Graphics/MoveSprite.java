package Graphics;

import Data.Animation;
import Data.Frame;

public class MoveSprite {
	//fields
	Animation anima;
	int endX, endY;
	
	//constructor
	public MoveSprite(Animation anima, int step, int curX, int curY, int endX, int endY) {
		this.anima = anima;
		this.endX = endX;
		this.endY = endY;
		int frameCounter = 0;
		double changeX = getChange(curX, endX);
		double changeY = getChange(curY, endY);
		double slope = getSlope(curX, curY, endX, endY);
		double x, y, xStep, yStep;
		System.out.println(1);
		if (slope >= (Math.atan(1))  || slope <= (Math.atan(-1))) {  //up or down animation
			if (changeY >= 0 && changeX >= 0) {                        //down animation right
				for (x = curX, y = curY; x < endX; x += xStep, y += yStep) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, "robDown" + frameCounter));
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, "robDown" + frameCounter));
			}
			else if (changeY > 0 && changeX < 0) {                      //down animation left
				for (x = curX, y = curY; x > endX; x -= xStep, y += yStep) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, "robDown" + frameCounter));
						frameCounter++;
						if (frameCounter > 3)
							frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, "robDown" + frameCounter));
			}
			else if (changeY < 0 && changeX >= 0) {                     //up animation right
				for (x = curX, y = curY; y > endY; x += xStep, y -= yStep) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, "robUp" + frameCounter));
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, "robUp" + frameCounter));
			} else {                                                    //up animation left
				for (x = curX, y = curY; x > endX; x -= xStep, y -= yStep) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, "robUp" + frameCounter));
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, "robUp" + frameCounter));
			}
		} else {                                                      //left or right animation
			if (changeX >= 0 && changeY >= 0) {                         //right animation down
				for (x = curX, y = curY; x < endX; x += xStep, y += yStep) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, "robRight" + frameCounter));
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, "robRight" + frameCounter));
			}
			else if (changeX > 0 && changeY < 0) {                      //right animation up
				for (x = curX, y = curY; x < endX; x += xStep, y -= yStep) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, "robRight" + frameCounter));
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, "robRight" + frameCounter));
			}
			else if (changeX < 0 && changeY > 0) {                      //left animation down
				for (x = curX, y = curY; x > endX; x -= xStep, y += yStep) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, "robLeft" + frameCounter));
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, "robLeft" + frameCounter));
			} else {                                                    //left animation up
				for (x = curX, y = curY; x > endX; x -= xStep, y -= yStep) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, "robLeft" + frameCounter));
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, "robLeft" + frameCounter));
			}
		}
	}
	
	//methods
	private double getChange(double start, int finish) {
		return finish - start;
	}
	
	private double getSlope(double curX, double curY, int endX, int endY) {
		double changeX = getChange(curX, endX);
		double changeY = getChange(curY, endY);
		if (changeX != 0)
			return Math.atan(changeY / changeX);  //convert to radians
		else return Math.PI / 2;
	}
	
	private double getXStep(int step, double slope) {
		return Math.abs(step * Math.cos(slope));
	}
	
	private double getYStep(int step, double slope) {
		return Math.abs(step * Math.sin(slope));
	}
	
	public Animation getAnimation() {
		return anima;
	}
	public void changeCoords(int nX, int nY) {
		endX = nX;
		endY = nY;
	}
	public boolean compareCoords(int nX, int nY) {
		if (nX == endX && nY == endY)
			return true;
		else
			return false;
	}
}
