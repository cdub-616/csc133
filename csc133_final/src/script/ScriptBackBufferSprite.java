package script;

public class ScriptBackBufferSprite {
	//fields
	private int bufX, bufY, width, height;
	private String sTag;
			
	//constructors
	public ScriptBackBufferSprite() {
		bufX = 0;
		bufY = 0;
		width = 1;
		height = 1;
		sTag = "vanilla";
	}
	public ScriptBackBufferSprite(int bufX, int bufY, int width, int height, 
		String sTag) {
		this.bufX = bufX;
		this.bufY = bufY;
		this.width = width;
		this.height = height;
		this.sTag = sTag;
	}
	
	//methods
	public int getBufX() {
		return bufX;
	}
	public int getBufY() {
		return bufY;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public String getSTag() {
		return sTag;
	}
}