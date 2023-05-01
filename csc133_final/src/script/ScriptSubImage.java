package script;

public class ScriptSubImage {
	//fields
	private int x, y, width, height;
	private String sTag;
	
	//constructors
	public ScriptSubImage() {
		x = 0;
		y = 0;
		width = 1;
		height = 1;
		sTag = "vanilla";
	}
	public ScriptSubImage(int x, int y, int width, int height, String sTag) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sTag = sTag;
	}
	//methods
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
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
