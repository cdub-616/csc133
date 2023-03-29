package Data;

public class ScriptRectTextHover {
	//fields
	RECT rect;
	ScriptTextShadow text;
	ScriptText sText;
	
	//constructor
	public ScriptRectTextHover(RECT rect, ScriptTextShadow text) { //with shadow
		this.rect = rect;
		this.text = text;
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
		return text;
	}
	
	public ScriptText getScriptText() {
		return sText;
	}
}
