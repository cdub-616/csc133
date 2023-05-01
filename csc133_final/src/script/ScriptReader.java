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
	private ArrayList<ScriptSubImage> subImages = new ArrayList<>();
	private ArrayList<ScriptStartPosition> startPositions = new ArrayList<>();
	private ArrayList<ScriptSubObstacle> subObstacles = new ArrayList<>();
	private ArrayList<ScriptAnimation> animations = new ArrayList<>();
	private ArrayList<ScriptSound> sounds = new ArrayList<>();
		
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
			}else if (c.isCommand("sound") & c.getNumParms() == 1) {
				String fileName = c.getParmByIndex(0);
				ScriptSound sound = new ScriptSound(fileName);
				sounds.add(sound);
			}else if (c.isCommand("animation") & c.getNumParms() == 7) {
				int delay = Integer.parseInt(c.getParmByIndex(0));
				boolean isLooping = Boolean.parseBoolean(c.getParmByIndex(1));
				int startX = Integer.parseInt(c.getParmByIndex(2));
				int startY = Integer.parseInt(c.getParmByIndex(3));
				int bitSize = Integer.parseInt(c.getParmByIndex(4));
				int numImages = Integer.parseInt(c.getParmByIndex(5));
				int step = Integer.parseInt(c.getParmByIndex(6));
				ScriptAnimation animation = new ScriptAnimation(delay, 
					isLooping, startX, startY, bitSize, numImages, step);
				animations.add(animation);
			}else if (c.isCommand("start") & c.getNumParms() == 2) {
				int x = Integer.parseInt(c.getParmByIndex(0));
				int y = Integer.parseInt(c.getParmByIndex(1));
				ScriptStartPosition startPosition = 
					new ScriptStartPosition(x, y);
				startPositions.add(startPosition);
			}else if (c.isCommand("subImage") & c.getNumParms() == 5) {
				int x = Integer.parseInt(c.getParmByIndex(0));
				int y = Integer.parseInt(c.getParmByIndex(1));
				int width = Integer.parseInt(c.getParmByIndex(2));
				int height = Integer.parseInt(c.getParmByIndex(3));
				String sTag = c.getParmByIndex(4);
				ScriptSubImage subImage = new ScriptSubImage(x, y, width, 
					height, sTag);
				subImages.add(subImage);
			} else if (c.isCommand("obstacle") & c.getNumParms() == 5) {
				int x = Integer.parseInt(c.getParmByIndex(0));
				int y = Integer.parseInt(c.getParmByIndex(1));
				String sTag = c.getParmByIndex(2);
				int objectSize = Integer.parseInt(c.getParmByIndex(3));
				String rTag = c.getParmByIndex(4);
				obstacles.add(new ScriptObstacle(x, y, sTag, objectSize, 
					rTag));
			} else if (c.isCommand("subObstacle") & c.getNumParms() == 9){
				int bufX = Integer.parseInt(c.getParmByIndex(0));
				int bufY = Integer.parseInt(c.getParmByIndex(1));
				int width = Integer.parseInt(c.getParmByIndex(2));
				int height = Integer.parseInt(c.getParmByIndex(3));
				int x = Integer.parseInt(c.getParmByIndex(4));
				int y = Integer.parseInt(c.getParmByIndex(5));
				String sTag = c.getParmByIndex(6);
				int objectSize = Integer.parseInt(c.getParmByIndex(7));
				String rTag = c.getParmByIndex(8);
				ScriptSubObstacle subObstacle = new ScriptSubObstacle(bufX, bufY, 
					width, height, x, y, sTag, objectSize, rTag);
				subObstacles.add(subObstacle);
			//without shadow
			} else if (c.isCommand("text") && c.getNumParms() == 6) { 
				String display = c.getParmByIndex(0);
				int x = Integer.parseInt(c.getParmByIndex(1));
				int y = Integer.parseInt(c.getParmByIndex(2));
				int red = Integer.parseInt(c.getParmByIndex(3));
				int green = Integer.parseInt(c.getParmByIndex(4));
				int blue = Integer.parseInt(c.getParmByIndex(5));
				Color col = new Color(red, green, blue);
				text.add(new ScriptText(display, x, y, col));
			//with shadow
			} else if (c.isCommand("shadowtext") && c.getNumParms() == 10) { 
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
				ScriptText shadowText = new ScriptText(hoverLabel, x1 + shadow, 
					y1 + 2 + shadow, shadowCol);
				ScriptText hoverText = new ScriptText(hoverLabel, x1 + 2, 
					y1 + 2, textCol);
				ScriptTextShadow text = new ScriptTextShadow(hoverText, 
					shadowText);
				rTHovers.add(new ScriptRectTextHover(rect, shadow, text));		
			}
		}
	}
		
	//methods
	public ArrayList<ScriptSprite> getScriptSprites() {
		return sprites;
	}
	public ArrayList<ScriptSubImage> getScriptSubImage(){
		return subImages;
	}
	public ArrayList<ScriptTextShadow> getScriptTextShadows(){
		return textShad;
	}
	public ArrayList<ScriptStartPosition> getScriptStartPosition(){
		return startPositions;
	}
	public ArrayList<ScriptRectTextHover> getScriptRectTextHover(){
		return rTHovers;
	}
	public ArrayList<ScriptSubObstacle> getScriptSubObstacles(){
		return subObstacles;
	}
	public ArrayList<ScriptText> getScriptTexts(){
		return text;
	}
	public ArrayList<ScriptObstacle> getScriptObstacles(){
		return obstacles;
	}
	public ArrayList<ScriptAnimation> getScriptAnimations(){
		return animations;
	}
	public ArrayList<ScriptSound> getScriptSounds(){
		return sounds;
	}
}