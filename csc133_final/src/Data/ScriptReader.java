package Data;

import java.awt.Color;
import java.util.ArrayList;

import FileIO.EZFileRead;
import script.Command;

public class ScriptReader {
	//fields
	private ArrayList<ScriptSprite> sprites;
	private ArrayList<ScriptText> texts;
	
	//constructor
	public ScriptReader(String filename) {
		EZFileRead ezr = new EZFileRead(filename);
		int totalLines = ezr.getNumLines();
		ArrayList<Command> commands = new ArrayList<>();
		for (int i = 0; i < totalLines; i++) {
			String raw = ezr.getLine(i);
			raw = raw.trim();
			if (!raw.equals("")) {
				boolean b = raw.charAt(0) == '#';
				if (!b)
					commands.add(new Command(raw));
			}
		}
		sprites = new ArrayList<ScriptSprite>();
		texts = new ArrayList<ScriptText>();
		for (Command c: commands) {
			if (c.isCommand("show_sprite") && c.getNumParms() == 3) {
				int x = Integer.parseInt(c.getParmByIndex(0));
				int y = Integer.parseInt(c.getParmByIndex(1));
				String tag = c.getParmByIndex(2);
				sprites.add(new ScriptSprite(x, y, tag));
			} else if (c.isCommand("text") && c.getNumParms() == 6) {
				String display = c.getParmByIndex(0);
				int x = Integer.parseInt(c.getParmByIndex(1));
				int y = Integer.parseInt(c.getParmByIndex(2));
				int red = Integer.parseInt(c.getParmByIndex(3));
				int green = Integer.parseInt(c.getParmByIndex(4));
				int blue = Integer.parseInt(c.getParmByIndex(5));
				Color col = new Color(red, green, blue);
				texts.add(new ScriptText(display, x, y, col));	
			}
		}
	}
	
	//methods
	public ArrayList<ScriptSprite> getScriptSprites() {
		return sprites;
	}
	
	public ArrayList<ScriptText> getScriptTexts(){
		return texts;
	}
}