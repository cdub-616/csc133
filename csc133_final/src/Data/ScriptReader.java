package Data;

import java.awt.Color;
import java.util.ArrayList;

import FileIO.EZFileRead;
import script.Command;

public class ScriptReader {
	//fields
	private ArrayList<ScriptRectTextHover> rTHovers;
	private ArrayList<ScriptSprite> sprites;
	private ArrayList<ScriptTextShadow> texts;
		
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
		rTHovers = new ArrayList<ScriptRectTextHover>();
		sprites = new ArrayList<ScriptSprite>();
		texts = new ArrayList<ScriptTextShadow>();
		for (Command c: commands) {
			if (c.isCommand("show_sprite") && c.getNumParms() == 3) {
				int x = Integer.parseInt(c.getParmByIndex(0));
				int y = Integer.parseInt(c.getParmByIndex(1));
				String tag = c.getParmByIndex(2);
				sprites.add(new ScriptSprite(x, y, tag));
			} else if (c.isCommand("text") && c.getNumParms() == 10) {
				String display = c.getParmByIndex(0);
				int x = Integer.parseInt(c.getParmByIndex(1));
				int y = Integer.parseInt(c.getParmByIndex(2));
				int red = Integer.parseInt(c.getParmByIndex(3));
				int green = Integer.parseInt(c.getParmByIndex(4));
				int blue = Integer.parseInt(c.getParmByIndex(5));
				Color col = new Color(red, green, blue);
				int shadow = Integer.parseInt(c.getParmByIndex(6));
				int shadowRed = Integer.parseInt(c.getParmByIndex(7));
				int shadowGreen = Integer.parseInt(c.getParmByIndex(8));
				int shadowBlue = Integer.parseInt(c.getParmByIndex(9));
				Color shadowCol = new Color(shadowRed, shadowGreen, shadowBlue);
				ScriptText text = new ScriptText(display, x, y, col);
				ScriptText shadowT = new ScriptText(display, x - shadow, y - shadow, shadowCol);
				texts.add(new ScriptTextShadow(text, shadowT));	
			} else if (c.isCommand("rect_texthover") && c.getNumParms() == 14) {
				int x1 = Integer.parseInt(c.getParmByIndex(0));
				int y1 = Integer.parseInt(c.getParmByIndex(1));
				int x2 = Integer.parseInt(c.getParmByIndex(2));
				int y2 = Integer.parseInt(c.getParmByIndex(3));
				String tag = c.getParmByIndex(4);
				String hoverLabel = c.getParmByIndex(5);
				String display = c.getParmByIndex(6);
				int red = Integer.parseInt(c.getParmByIndex(7));
				int green = Integer.parseInt(c.getParmByIndex(8));
				int blue = Integer.parseInt(c.getParmByIndex(9));
				Color textCol = new Color(red, green, blue);
				int shadow = Integer.parseInt(c.getParmByIndex(10));
				int shadowRed = Integer.parseInt(c.getParmByIndex(11));
				int shadowGreen = Integer.parseInt(c.getParmByIndex(12));
				int shadowBlue = Integer.parseInt(c.getParmByIndex(13));
				Color shadowCol = new Color(shadowRed, shadowGreen, shadowBlue);
				RECT rect = new RECT(x1, y1, x2, y2, tag, hoverLabel);
				ScriptText hoverText = new ScriptText(display, x1 - 2, y1 - 2, textCol);
				ScriptText shadowText = new ScriptText(display, x1 - 2 - shadow, y1 - 2 - shadow, shadowCol);
				ScriptTextShadow text = new ScriptTextShadow(hoverText, shadowText);
				rTHovers.add(new ScriptRectTextHover(rect, text));		
			}
		}
	}
		
	//methods
	public ArrayList<ScriptSprite> getScriptSprites() {
		return sprites;
	}
		
	public ArrayList<ScriptTextShadow> getScriptTextShadows(){
		return texts;
	}
	
	public ArrayList<ScriptRectTextHover> getScriptRectTextHover(){
		return rTHovers;
	}
}
