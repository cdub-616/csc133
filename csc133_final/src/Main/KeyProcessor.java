/* This will handle the "Hot Key" system. */

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250);
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' ')				return;
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here!    */
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
			
		case 't':
			//Main.trigger = "t is triggered";
			break;
			
		case 'u':
			//Main.trigger = "u is triggered";
			break;
			
		case '$':
			//Main.trigger = "space bar is triggered";
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		}
	}
}