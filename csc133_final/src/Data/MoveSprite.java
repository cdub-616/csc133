package Data;

public class MoveSprite {
	//fields
	Animation anima;
	int step, curX, curY, endX, endY;
	RECT rect;
	
	//constructor
	public MoveSprite(int spriteSize, String tag, String[] face, 
			Animation anima, int step, int curX, int curY, int endX, int endY) {
		this.anima = anima;
		this.step = step;
		this.curX = curX;
		this.curY = curY;
		this.endX = endX;
		this.endY = endY;
		int x1 = curX, y1 = curY, x2 = curX + spriteSize, 
			y2 = curY + spriteSize;
		String down = face[0], up = face[1], right = face[2], left = face[3];
		rect = new RECT(x1, y1, x2, y2, tag);
		int frameCounter = 0;
		double changeX = getChange(curX, endX);
		double changeY = getChange(curY, endY);
		double slope = getSlope(curX, curY, endX, endY);
		double x, y, xStep, yStep;
		x = curX;
		y = curY;
		if (slope >= (Math.atan(1))  || slope <= (Math.atan(-1))) {  //up or down animation
			if (changeY >= 0 && changeX >= 0) {                        //down animation right
				//for (x = curX, y = curY; x < endX; x += xStep, y += yStep) {	
				while (curX < endX) {		
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, down + 
						frameCounter));
					x += xStep;
					y += yStep;
					curX = (int)x;
					curY = (int)y;
					x1 = curX;
					y1 = curY;
					x2 = curX + spriteSize;
					y2 = curY + spriteSize;
					rect.updateRect(x1, y1, x2, y2);
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, down + frameCounter));
			}
			else if (changeY > 0 && changeX < 0) {                      //down animation left
				//for (x = curX, y = curY; x > endX; x -= xStep, y += yStep) {
				while (curX > endX) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, down + 
						frameCounter));
					x -= xStep;
					y += yStep;
					curX = (int)x;
					curY = (int)y;
					x1 = curX;
					y1 = curY;
					x2 = curX + spriteSize;
					y2 = curY + spriteSize;
					rect.updateRect(x1, y1, x2, y2);
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, down + frameCounter));
			}
			else if (changeY < 0 && changeX >= 0) {                     //up animation right
				//for (x = curX, y = curY; y > endY; x += xStep, y -= yStep) {
				while (curY > endY) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, up + 
						frameCounter));
					x += xStep;
					y -= yStep;
					curX = (int)x;
					curY = (int)y;
					x1 = curX;
					y1 = curY;
					x2 = curX + spriteSize;
					y2 = curY + spriteSize;
					rect.updateRect(x1, y1, x2, y2);
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, up + frameCounter));
			} else {                                                    //up animation left
				//for (x = curX, y = curY; x > endX; x -= xStep, y -= yStep) {
				while (curX > endX) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, up + 
						frameCounter));
					x -= xStep;
					y -= yStep;
					curX = (int)x;
					curY = (int)y;
					x1 = curX;
					y1 = curY;
					x2 = curX + spriteSize;
					y2 = curY + spriteSize;
					rect.updateRect(x1, y1, x2, y2);
					frameCounter++;
					if (frameCounter > 3)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, up + frameCounter));
			}
		} else {                                                      //left or right animation
			if (changeX >= 0 && changeY >= 0) {                         //right animation down
				//for (x = curX, y = curY; x < endX; x += xStep, y += yStep) {
				while (curX < endX) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, right + 
						frameCounter));
					x += xStep;
					y += yStep;
					curX = (int)x;
					curY = (int)y;
					x1 = curX;
					y1 = curY;
					x2 = curX + spriteSize;
					y2 = curY + spriteSize;
					rect.updateRect(x1, y1, x2, y2);
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, right + frameCounter));
			}
			else if (changeX > 0 && changeY < 0) {                      //right animation up
				//for (x = curX, y = curY; x < endX; x += xStep, y -= yStep) {
				while (curX < endX) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, right + 
						frameCounter));
					x += xStep;
					y -= yStep;
					curX = (int)x;
					curY = (int)y;
					x1 = curX;
					y1 = curY;
					x2 = curX + spriteSize;
					y2 = curY + spriteSize;
					rect.updateRect(x1, y1, x2, y2);
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, right + frameCounter));
			}
			else if (changeX < 0 && changeY > 0) {                      //left animation down
				//for (x = curX, y = curY; x > endX; x -= xStep, y += yStep) {
				while (curX > endX) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, left + 
						frameCounter));
					x -= xStep;
					y += yStep;
					curX = (int)x;
					curY = (int)y;
					x1 = curX;
					y1 = curY;
					x2 = curX + spriteSize;
					y2 = curY + spriteSize;
					rect.updateRect(x1, y1, x2, y2);
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, left + frameCounter));
			} else {                                                    //left animation up
				//for (x = curX, y = curY; x > endX; x -= xStep, y -= yStep) {
				while (curX > endX) {
					slope = getSlope(x, y, endX, endY);
					xStep = getXStep(step, slope);
					yStep = getYStep(step, slope);
					anima.addFrame(new Frame((int)x, (int)y, left + 
						frameCounter));
					x -= xStep;
					y -= yStep;
					curX = (int)x;
					curY = (int)y;
					x1 = curX;
					y1 = curY;
					x2 = curX + spriteSize;
					y2 = curY + spriteSize;
					rect.updateRect(x1, y1, x2, y2);
					frameCounter++;
					if (frameCounter > 1)
						frameCounter = 0;
				}
				if (x != endX || y != endY)
					anima.addFrame(new Frame(endX, endY, left + frameCounter));
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
	public int getStep() {
		return step;
	}
	public int getCurX() {
		return curX;
	}
	public int getCurY() {
		return curY;
	}
	/*public void updateCoords(int cX, int cY) {
		curX = cX;
		curY = cY;
	}*/
	public RECT getRect() {
		return rect;
	}
	public boolean compareCoords(int nX, int nY) {
		if (nX == endX && nY == endY)
			return true;
		else
			return false;
	}
}
