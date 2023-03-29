package Data;

public class ScriptRectTextHover {
	//fields
	RECT rect;
	ScriptTextShadow text;
	
	//constructor
	public ScriptRectTextHover(RECT rect, ScriptTextShadow text) {
		this.rect = rect;
		this.text = text;
	}
	
	//methods
	public RECT getRect() {
		return rect;
	}
	
	public ScriptTextShadow getScriptText() {
		return text;
	}
}
