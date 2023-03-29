package Data;

public class ScriptTextShadow {
	//fields
	private ScriptText text;
	private ScriptText shadow;
	
	//constructor
	public ScriptTextShadow(ScriptText text, ScriptText shadow) {
		this.text = text;
		this.shadow = shadow;
	}
	
	//methods
	public ScriptText getText() {
		return text;
	}
	
	public ScriptText getShadow() {
		return shadow;
	}

}
