package script;

public class ScriptAnimation {
	//fields
	private int delay, startX, startY, bitSize, numImages, step;
	private boolean isLooping;
	
	//constructors
	public ScriptAnimation() {
		delay = 1;
		isLooping = false;
		startX = 0;
		startY = 0;
		bitSize = 1;
		numImages = 1;
		step = 1;
	}
	public ScriptAnimation(int delay, boolean isLooping, int startX, int startY,
		int bitSize, int numImages, int step) {
		this.delay = delay;
		this.isLooping = isLooping;
		this.startX = startX;
		this.startY = startY;
		this.bitSize = bitSize;
		this.numImages = numImages;
		this.step = step;
	}
	
	//methods
	public int getDelay() {
		return delay;
	}
	public boolean getIsLooping() {
		return isLooping;
	}
	public int getStartX() {
		return startX;
	}
	public int getStartY() {
		return startY;
	}
	public int getBitSize() {
		return bitSize;
	}
	public int getNumImages() {
		return numImages;
	}
	public int getStep() {
		return step;
	}
}
