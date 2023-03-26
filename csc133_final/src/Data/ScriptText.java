package Data;

import java.awt.Color;

public class ScriptText {
	//fields
	private int x = 0, y = 0;
	private String str = "";
	private Color col;
	
	//constructor
	public ScriptText(String str, int x, int y, Color col) {
		this.str = str;
		this.x = x;
		this.y = y;
		this.col = col;
	}
	
	//method
	public String getText() {
		return str;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Color getColor() {
		return col;
	}
}
