package script;

public class ScriptAnimation {
	//fields
	private int delay;
	private boolean isLooping;
	
	//constructors
	public ScriptAnimation() {
		delay = 1;
		isLooping = false;
	}
	public ScriptAnimation(int delay, boolean isLooping) {
		this.delay = delay;
		this.isLooping = isLooping;
	}
	
	//methods
	public int getDelay() {
		return delay;
	}
	public boolean getIsLooping() {
		return isLooping;
	}
}
