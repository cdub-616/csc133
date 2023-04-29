package script;

public class ScriptStartPosition {
	//fields
	private int startX, startY;
	
	//constructors
	public ScriptStartPosition() {
		startX = 0;
		startY = 0;
	}
	public ScriptStartPosition(int startX, int startY) {
		this.startX = startX;
		this.startY = startY;
	}
	
	//methods
	public int getStartX() {
		return startX;
	}
	public int getStartY() {
		return startY;
	}
}
