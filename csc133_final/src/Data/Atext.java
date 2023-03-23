package Data;

import timer.stopWatchX;

public class Atext {
	//fields
	private String srcStr;       //the string to print
	private String destStr;      //the part of the string currently being displayed
	private stopWatchX timer;    //controls the speed of the text
	private boolean isFinished;  //flag for when animation is over
	private int cursor;          //track the index into the srcStr
	
	//constructor
	public Atext(String srcStr, int delay) {
		this.srcStr = srcStr;
		timer = new stopWatchX(delay);
		destStr = "";
		isFinished = false;
		cursor = 0;
	}
	
	//methods
	public String getCurrentStr() {
		if (isFinished)
			return destStr;
		if (timer.isTimeUp()) {
			if (cursor < srcStr.length())
				destStr += srcStr.charAt(cursor++);
			if (cursor >= srcStr.length())
				isFinished = true;
			timer.resetWatch();
		}
		return destStr;
	}
	
	public boolean isAnimationDone() {
		return isFinished;
	}
	
	public void resetAnimation() {
		isFinished = false;
		destStr = "";
		cursor = 0;
		timer.resetWatch();
	}
}
