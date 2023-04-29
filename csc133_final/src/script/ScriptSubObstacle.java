package script;

public class ScriptSubObstacle {
	//fields
	private int bufX, bufY, width, height, x, y, obSize;
	private String sTag, rTag;
	
	//constructors
	public ScriptSubObstacle() {
		bufX = 0;
		bufY = 0;
		width = 1;
		height = 1;
		x = 0;
		y = 0;
		sTag = "vanilla";
		obSize = 1;
		rTag = "vanillaRect";
	}
	public ScriptSubObstacle(int bufX, int bufY, int width, int height, int x, 
			int y, String sTag, int obSize, String rTag) {
			this.bufX = bufX;
			this.bufY = bufY;
			this.width = width;
			this.height = height;
			this.x = x;
			this.y = y;
			this.sTag = sTag;
			this.obSize = obSize;
			this.rTag = rTag;
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
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getObSize() {
		return obSize;
	}
	public String getSTag() {
		return sTag;
	}
	public String getRTag() {
		return rTag;
	}
}
