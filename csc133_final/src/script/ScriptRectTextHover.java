package script;

import Data.RECT;

public class ScriptRectTextHover {
	//fields
	private RECT rect;
	private ScriptTextShadow shadText;
	private ScriptText sText;
	private int shadow;
	
	//constructors
	public ScriptRectTextHover() {
		rect = new RECT();
		shadText = new ScriptTextShadow();
		sText = new ScriptText();
		shadow = 0;
	}
	
	public ScriptRectTextHover(RECT rect, int shadow, ScriptTextShadow shadText) { //with shadow
		this.rect = rect;
		this.shadText = shadText;
		this.shadow = shadow;
	}
	
	public ScriptRectTextHover(RECT rect, ScriptText sText) {      //without shadow
		this.rect = rect;
		this.sText = sText;
	}
	
	//methods
	public RECT getRect() {
		return rect;
	}
	
	public ScriptTextShadow getScriptTextShadow() {
		return shadText;
	}
	
	public ScriptText getScriptText() {
		return sText;
	}
	
	public int getShadow() {
		return shadow;
	}
}