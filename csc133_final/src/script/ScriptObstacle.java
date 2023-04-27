package script;

import Data.RECT;
import Data.Sprite;

public class ScriptObstacle {
	//fields
	private int x, y, obSize;
	private String sTag, rTag;
	
	//constructors
	public ScriptObstacle() {
		x = 0;
		y = 0;
		obSize = 1;
		sTag = "vanillaSprite";
		rTag = "vanillaRect";
	}
	public ScriptObstacle(int x, int y, String tag, int obSize, String rTag) {
		this.x = x;
		this.y = y;
		this.obSize = obSize;
		this.sTag = tag;
		this.rTag = rTag;
	}
	
	//methods
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
