package script;

public class ScriptDrawAnimation {
	
	//fields
	private int delay, drawX, drawY, numSprites;
	private boolean isLooping;
	private String tagName;
	
	//constructors
	public ScriptDrawAnimation(int delay, boolean isLooping, int drawX, 
		int drawY, String tagName, int numSprites) {
		this.delay = delay;
		this.isLooping = isLooping;
		this.drawX = drawX;
		this.drawY = drawY;
		this.tagName = tagName;
		this.numSprites = numSprites;
	}
	//methods
	public int getDelay() {
		return delay;
	}
	public boolean getIsLooping() {
		return isLooping;
	}
	public int getDrawX() {
		return drawX;
	}
	public int getDrawY() {
		return drawY;
	}
	public String getTagName() {
		return tagName;
	}
	public int getNumSprites() {
		return numSprites;
	}
}