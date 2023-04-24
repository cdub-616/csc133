package particles;

import Data.Frame;
import timer.stopWatchX;

public class Particle {
	//fields
	private int x, y;                  //current positions of a given particle
	private String particleSpriteTag;  //replace with sprite tag instead
	private int lifecycle;             //how many "steps" does the particle take before it dies
	private int age;                   //how "old" the particle is... a counter of sorts
	private int xMove, yMove;          //how much each moves every iteration
	private stopWatchX timer;
	//to preserve for resetting...
	private int rootX, rootY;
	private int minX, maxX, minY, maxY;
	private boolean isReset;
	
	//constructor
	public Particle(int minX, int maxX, int minY, int maxY, String particleSpriteTag, int minLife, int maxLife, int xMove, int yMove, int mindelay, int maxdelay) {
		//initialize all of the needed data for a single particle
		this.particleSpriteTag = particleSpriteTag;
		this.x = getRandomInt(minX, maxX);
		this.y = getRandomInt(minY, maxY);
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		lifecycle = getRandomInt(minLife, maxLife);
		this.xMove = xMove;
		this.yMove = yMove;
		int delay = getRandomInt(mindelay, maxdelay);
		timer = new stopWatchX(delay);
		rootX = x;
		rootY = y;
	}
	
	//methods
	//getters and setters
	public boolean hasBeenReset() {
		return isReset;
	}
	
	public void changeX(int newX) {
		x = newX;
		rootX = x;
	}
	public void changeY(int newY) {
		y = newY;
		rootY = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getMaxX() {
		return maxX;
	}
	public int getMaxY() {
		return maxY;
	}
	public int getMinX() {
		return minX;
	}
	public int getMinY() {
		return minY;
	}
	public int getLifeCycle() {
		return lifecycle;
	}
	public int getAge() {
		return age;
	}
	
	public void changeSprite(String newSpriteTag) {
		particleSpriteTag = newSpriteTag;
	}
	
	//govern particle behavior
	public boolean isParticleDead() {
		if (age >= lifecycle)
			return true;
		//also consider max x/y coordinate in certain situations
		if (y > 720 || x > 1279)
			return true;
		return false;
	}
	
	//this helps solve the problem of particle "plume" in the beginning by artificially aging them off screen
	public void simulateAge() {
		age++;
		x += xMove;
		y += yMove;
		if (isParticleDead()) {
			//reset
			x = rootX;
			y = rootY;
			age = 0;
			isReset = true;
		}
	}
	
	public Frame getCurrentFrame() {
		//update the particle and return results
		if (timer.isTimeUp()) {
			age++;
			x += xMove;
			y += yMove;
			if (isParticleDead()) {
				//reset
				x = rootX;
				y = rootY;
				age = 0;
				isReset = true;
			}
			timer.resetWatch();
		}
		return new Frame(x, y, particleSpriteTag);
	}
	
	//static dependencies
	//return a random number between number first and number last, excluding last
	public static int getRandomInt(int first, int last) {
		int diff = last - first;
		double num = Math.random() * diff;
		int intNum = (int)num;
		return first + intNum;
	}
	
	//seeks to emulate the roll of a die in a paper and pencil RPG
	//dieSides is how many "sides" the die has (d2, d4, d6, d8, etc)
	public static int rollDie(int dieSides) {
		double result = Math.random() * dieSides;
		int res = (int)result;
		res++;  //eliminate chance of rolling "0" which is impossible in real die
		return res;	
	}
}
