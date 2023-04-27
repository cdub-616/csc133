package script;

import java.awt.Color;
import java.util.ArrayList;

import Data.RECT;
import FileIO.EZFileRead;

public class ScriptReader {
	//fields
	private ArrayList<ScriptRectTextHover> rTHovers = new ArrayList<>();
	private ArrayList<ScriptSprite> sprites = new ArrayList<>();
	private ArrayList<ScriptTextShadow> textShad = new ArrayList<>();
	private ArrayList<ScriptText> text = new ArrayList<>();
	private ArrayList<ScriptObstacle> obstacles = new ArrayList<>();
		
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
		
		//fill ArrayLists
		for (Command c: commands) {
			if (c.isCommand("show_sprite") && c.getNumParms() == 3) {  
				int x = Integer.parseInt(c.getParmByIndex(0));
				int y = Integer.parseInt(c.getParmByIndex(1));
				String tag = c.getParmByIndex(2);
				sprites.add(new ScriptSprite(x, y, tag));
			} else if (c.isCommand("obstacle") & c.getNumParms() == 5) {
				int x = Integer.parseInt(c.getParmByIndex(0));
				int y = Integer.parseInt(c.getParmByIndex(1));
				String sTag = c.getParmByIndex(2);
				int objectSize = Integer.parseInt(c.getParmByIndex(3));
				String rTag = c.getParmByIndex(4);
				obstacles.add(new ScriptObstacle(x, y, sTag, objectSize, 
					rTag));
			} else if (c.isCommand("text") && c.getNumParms() == 6) { //without shadow
				String display = c.getParmByIndex(0);
				int x = Integer.parseInt(c.getParmByIndex(1));
				int y = Integer.parseInt(c.getParmByIndex(2));
				int red = Integer.parseInt(c.getParmByIndex(3));
				int green = Integer.parseInt(c.getParmByIndex(4));
				int blue = Integer.parseInt(c.getParmByIndex(5));
				Color col = new Color(red, green, blue);
				text.add(new ScriptText(display, x, y, col));
			} else if (c.isCommand("shadowtext") && c.getNumParms() == 10) { //with shadow
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
				Shadow shadowT = new Shadow(shadow, shadowCol, text);
				ScriptText sText = shadowT.getShadow();
				textShad.add(new ScriptTextShadow(text, sText));	
			} else if (c.isCommand("rect_texthover") && c.getNumParms() == 13) {
				int x1 = Integer.parseInt(c.getParmByIndex(0));
				int y1 = Integer.parseInt(c.getParmByIndex(1));
				int x2 = Integer.parseInt(c.getParmByIndex(2));
				int y2 = Integer.parseInt(c.getParmByIndex(3));
				String tag = c.getParmByIndex(4);
				String hoverLabel = c.getParmByIndex(5);
				int red = Integer.parseInt(c.getParmByIndex(6));
				int green = Integer.parseInt(c.getParmByIndex(7));
				int blue = Integer.parseInt(c.getParmByIndex(8));
				Color textCol = new Color(red, green, blue);
				int shadow = Integer.parseInt(c.getParmByIndex(9));
				int shadowRed = Integer.parseInt(c.getParmByIndex(10));
				int shadowGreen = Integer.parseInt(c.getParmByIndex(11));
				int shadowBlue = Integer.parseInt(c.getParmByIndex(12));
				Color shadowCol = new Color(shadowRed, shadowGreen, shadowBlue);
				RECT rect = new RECT(x1, y1, x2, y2, tag, hoverLabel);
				ScriptText shadowText = new ScriptText(hoverLabel, x1 + shadow, y1 + 2 + shadow, shadowCol);
				ScriptText hoverText = new ScriptText(hoverLabel, x1 + 2, y1 + 2, textCol);
				ScriptTextShadow text = new ScriptTextShadow(hoverText, shadowText);
				rTHovers.add(new ScriptRectTextHover(rect, shadow, text));		
			}
		}
	}
		
	//methods
	public ArrayList<ScriptSprite> getScriptSprites() {
		return sprites;
	}
		
	public ArrayList<ScriptTextShadow> getScriptTextShadows(){
		return textShad;
	}
	
	public ArrayList<ScriptRectTextHover> getScriptRectTextHover(){
		return rTHovers;
	}
	
	public ArrayList<ScriptText> getScriptTexts(){
		return text;
	}
	public ArrayList<ScriptObstacle> getScriptObstacles(){
		return obstacles;
	}
}