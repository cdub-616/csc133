package script;

public class ScriptAnimation {
	//fields
	private int delay, step;
	private boolean isLooping;
	
	//constructors
	public ScriptAnimation() {
		delay = 1;
		isLooping = false;
		step = 1;
	}
	public ScriptAnimation(int delay, boolean isLooping, int step) {
		this.delay = delay;
		this.isLooping = isLooping;
		this.step = step;
	}
	
	//methods
	public int getDelay() {
		return delay;
	}
	public boolean getIsLooping() {
		return isLooping;
	}
	public int getStep() {
		return step;
	}
}