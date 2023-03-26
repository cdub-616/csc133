package Data;

public class ScriptSprite {
	//fields
	int x, y;
	String tag;
	
	//constructor
	public ScriptSprite(int x, int y, String tag) {
		this.x = x;
		this.y = y;
		this.tag = tag;
	}
	
	//methods
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getTag() {
		return tag;
	}
}
