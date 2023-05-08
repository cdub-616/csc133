package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import Data.Animation;
import Data.Atext;
import Data.Click;
import Data.RECT;
import Data.Sprite;
import Data.Frame;
import Data.MoveRobot;
import FileIO.EZFileWrite;
import Graphics.Graphic;
import Graphics.TileMap;
import FileIO.EZFileRead;
import Input.Mouse;
import logic.Control;
import particles.ParticleSystem;
import particles.Shiny;
import script.ScriptAnimation;
import script.Command;
import script.ScriptBackBufferSprite;
import script.ScriptDrawAnimation;
import script.ScriptItemGoal;
import script.ScriptObstacle;
import script.ScriptReader;
import script.ScriptSound;
import script.ScriptSprite;
import script.ScriptHudSubImage;
import script.ScriptHudSubObstacle;
import script.ScriptStartPosition;
import script.ScriptSubGoal;
import script.ScriptSubImage;
import script.ScriptSubObstacle;
import script.ScriptText;
import sound.Sound;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static String coord = "";             //coordinate tool
	//game variables for save/load
	private static ArrayList<Animation> animations = new ArrayList<>();
	private static ArrayList<Integer> buffer = new ArrayList<>();
	private static ArrayList<String> inventoryList = new ArrayList<>();
	private static ArrayList<String> itemGoalList = new ArrayList<>();
	private static ArrayList<RECT> rectList = new ArrayList<>();
	private static ArrayList<RECT> hudRectList = new ArrayList<>();
	private static ArrayList<RECT> goalRectList = new ArrayList<>();
	private static ArrayList<RECT> itemGoalRectList = new ArrayList<>();
	private static ArrayList<RECT> animateRectList = new ArrayList<>();
	private static ArrayList<Sprite> spriteList = new ArrayList<>();
	private static ArrayList<Sprite> goalSpriteList = new ArrayList<>();
	private static ArrayList<Sprite> hudSpriteList = new ArrayList<>();
	private static ArrayList<Sprite> spriteItemList = new ArrayList<>();
	private static ArrayList<ScriptText> scriptTexts = new ArrayList<>();
	private static ArrayList<ScriptAnimation> scriptAnimations = 
		new ArrayList<>();
	private static ArrayList<ScriptSound> scriptSounds = new ArrayList<>();
	private static BufferedImage sheet;
	private static ScriptReader scriptReader;    
	private static Frame robotFrame;
	private static MoveRobot moveRobot;
	private static Animation robotAnim, botJumpAnim;
	private static Atext atext = new Atext("Who's that...    ", 75);
	private static int startX, startY, curX, curY, newX, newY, level = 0,
		goalX, goalY;
	private static boolean startOver = true, startHud = false, hasItem = false,
		activeHud = false, loaded = false, saved = false, showItem = true,
		newLevel = true, showAText = false, showGoal = true, dontGo = false, 
		playGame = false, loadedGame = false;
	private static Sound song;
	private static Sound tran, backToStart, finish, gotIt;
	private static Sprite mouseSp;
	private static Shiny shiny, shinyGoal;
	private static RECT hudRedButton, hudBlackButton, hudYellowButton;
	private static String mouseTag, specialItem = "";
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(Control ctrl){
		//TODO:  Code your starting conditions here...NOT DRAW CALLS HERE! 
		//(no addSprite or drawString)

		//hide mouse cursor
		ctrl.hideDefaultCursor();
		
		//get sprite sheet
		sheet = ctrl.getSpriteFromBackBuffer("sheet")
				.getSprite();		
		
		//initialize buffer for load/save
		buffer.add(level);
		int item = hasItem ? 1 : 0;
		buffer.add(item);
		
		//initialize list with itemGoal strings for inventory
		itemGoalList.add("green key");
		itemGoalList.add("blue key");
		itemGoalList.add("silver key");
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from 
	 * the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to 
		// show you how it works)
		
		Point p = Mouse.getMouseCoords();
		
		//start and finish screens
		if (!playGame) {		
			int count = 0;
			if (count == 0) {
				ArrayList<Sprite> back = new ArrayList<>();
				BufferedImage mouseBuf = sheet.getSubimage(0, 224, 16, 16);
				mouseSp = new Sprite(0, 0, mouseBuf, "cursor");
				back.add(mouseSp);
				BufferedImage startBuf = sheet.getSubimage(640, 192, 128, 128);
				Sprite start = new Sprite(0, 0, startBuf, "startButton");
				back.add(start);
				BufferedImage redStartBuf = sheet.getSubimage(640, 320, 128, 
					128);
				Sprite redStart = new Sprite(0, 0, redStartBuf, 
					"redStartButton");
				back.add(redStart);
				BufferedImage exitBuf = sheet.getSubimage(512, 192, 128, 128);
				Sprite exit = new Sprite(0, 0, exitBuf, "exitButton");
				back.add(exit);
				BufferedImage redExitBuf = sheet.getSubimage(512, 320, 128, 
					128);
				Sprite redExit = new Sprite(0, 0, redExitBuf, "redExitButton");
				back.add(redExit);
				ctrl.addBufImageToBackBuffer(back);
				
				//initialize sound for start/end screens
				tran = new Sound("Sound/finish_fx.wav");
				
				count = 1;
			}
			
			//mouse cursor
			ctrl.addSpriteToOverlayBuffer(p.x, p.y, mouseSp.getTag());
			
			//coordinate tool
			/*coord = p.toString();                           
			ctrl.drawString(500, 360, coord, Color.WHITE);*/
			
			if (level == 0) {
				ctrl.addSpriteToFrontBuffer(0, 0, "start");
				ctrl.addSpriteToFrontBuffer(550, 400, "startButton");
				RECT startRect = new RECT(550, 400, 550 + 128, 400 + 128, 
					"sRect");
				if (startRect.isCollision(p.x, p.y)) {
					ctrl.addSpriteToFrontBuffer(550, 400, "redStartButton");
				}
				if (Control.getMouseInput() != null) {
					Click click = Control.getMouseInput();
					if (startRect.isClicked(click, Click.LEFT_BUTTON)) {
						level = 1;
						playGame = true;
						tran.playWAV();
					}
				}
			
			}
			
			if (level == 4) {
				ctrl.addSpriteToFrontBuffer(0, 0, "end");
				ctrl.addSpriteToFrontBuffer(550, 520, "exitButton");
				RECT exitRect = new RECT(550, 520, 550 + 128, 520 + 128, 
					"eRect");
				if (exitRect.isCollision(p.x, p.y)) {
					ctrl.addSpriteToFrontBuffer(550, 520, "redExitButton");
				}
				if (Control.getMouseInput() != null) {
					Click click = Control.getMouseInput();
					if (exitRect.isClicked(click, Click.LEFT_BUTTON)) {
						tran.playWAV();
						System.exit(0);
					}
				}
			}
		}
		
		//for playable levels
		if (playGame) {
			
			//determine level
			if (newLevel) {	
				if (loadedGame) {
					int has = buffer.get(1);
					if (has == 0) {
						hasItem = false;
					} else {
						hasItem = true;
					}
				} else {
					hasItem = false;
				}
				spriteList.clear();
				rectList.clear();
				spriteItemList.clear();
				goalRectList.clear();
				goalSpriteList.clear();
				animations.clear();
				animateRectList.clear();
			
				ArrayList<ScriptSubImage> scriptSubImages = new ArrayList<>();
				ArrayList<ScriptStartPosition> scriptStartPositions = 
					new ArrayList<>();
				ArrayList<ScriptSubObstacle> scriptSubObstacles = 
					new ArrayList<>();
				ArrayList<ScriptSubGoal> scriptSubGoals = new ArrayList<>();
				ArrayList<ScriptItemGoal> scriptItemGoals = new ArrayList<>();
				ArrayList<ScriptHudSubObstacle> scriptHudSubObstacles = 
					new ArrayList<>();
				ArrayList<ScriptHudSubImage> scriptHudSubImages = 
					new ArrayList<>();
				ArrayList<ScriptBackBufferSprite> scriptBackBufferSprites = 
					new ArrayList<>();
				ArrayList<Sprite> backSpriteList = new ArrayList<>(); 
				ArrayList<ScriptDrawAnimation> scriptDrawAnimations = 
						new ArrayList<>();
			
				//for level editing
				//level = 2;  
			
				//determine script for level
				String levelNumber = "script" + level + ".txt";;
			
				//scripting
				scriptReader = new ScriptReader(levelNumber);
				scriptSubImages = scriptReader.getScriptSubImage();
				scriptStartPositions = scriptReader.getScriptStartPosition();
				scriptSubObstacles = scriptReader.getScriptSubObstacles();
				scriptAnimations = scriptReader.getScriptAnimations();
				scriptSounds = scriptReader.getScriptSounds();
				scriptSubGoals = scriptReader.getScriptSubGoals();
				scriptItemGoals = scriptReader.getScriptItemGoals();
				scriptHudSubImages = scriptReader.getHudSubImages();
				scriptHudSubObstacles = scriptReader.getHudSubObstacles();
				scriptBackBufferSprites = 
					scriptReader.getScriptBackBufferSprites();
				scriptDrawAnimations = scriptReader.getScriptDrawAnimations();
			
			
				//get scriptSubImage to draw map
				if (!scriptSubImages.isEmpty()) {
					ScriptSubImage mapImage = new ScriptSubImage();
					mapImage = scriptSubImages.get(0);
					BufferedImage map = sheet.getSubimage(mapImage.getX(), 
						mapImage.getY(), mapImage.getWidth(), 
						mapImage.getHeight());
					TileMap tileMap = new TileMap(map);
					Sprite sprMap = new Sprite();
					sprMap = tileMap.getSprite();
					spriteList.add(sprMap);
				}
			
				//get scriptBackBufferSprites
				if (!scriptBackBufferSprites.isEmpty()) {
					for (ScriptBackBufferSprite bufSpr: scriptBackBufferSprites)
						{
						BufferedImage buf = sheet.getSubimage(bufSpr.getBufX(),
							bufSpr.getBufY(), bufSpr.getWidth(), 
							bufSpr.getHeight());
						Sprite sprite = new Sprite(0, 0, buf, bufSpr.getSTag());
						backSpriteList.add(sprite);
						}
					ctrl.addBufImageToBackBuffer(backSpriteList);
					mouseTag = backSpriteList.get(0).getTag();
				}
				
				//get scriptSubObstacles
				if (!scriptSubObstacles.isEmpty()) {
					ScriptSubObstacle obImage = new ScriptSubObstacle();
					for (ScriptSubObstacle ob: scriptSubObstacles) {
						obImage = ob;
						BufferedImage buf = sheet.getSubimage(obImage.getBufX(), 
							obImage.getBufY(), obImage.getWidth(), 
							obImage.getHeight());
						Sprite sprite = new Sprite(obImage.getX(), 
							obImage.getY(), buf, obImage.getSTag());
						RECT rect = new RECT(obImage.getX(), obImage.getY(), 
							obImage.getX() + obImage.getObSize(), obImage.getY()
							+ obImage.getObSize(), obImage.getRTag());
						rectList.add(rect);
						spriteList.add(sprite);
					}
				}
			
				//get scriptHudSubImages
				if (!scriptHudSubImages.isEmpty()) {
					ScriptHudSubImage hudSubImage = new ScriptHudSubImage();
					for (ScriptHudSubImage hudImOb: scriptHudSubImages) {
						hudSubImage = hudImOb;
						BufferedImage buf = sheet.getSubimage(hudSubImage
							.getBufX(), hudSubImage.getBufY(), 
							hudSubImage.getWidth(), hudSubImage.getHeight());
						Sprite sprite = new Sprite(hudSubImage.getBufX(), 
							hudSubImage.getBufY(), buf, hudSubImage.getSTag());
						hudSpriteList.add(sprite);
					}
				}
			
				//get scriptHudSubObstacles
				if (!scriptHudSubObstacles.isEmpty()) {
					ScriptHudSubObstacle hudImage = new ScriptHudSubObstacle();
					for (ScriptHudSubObstacle hudOb: scriptHudSubObstacles) {
						hudImage = hudOb;
						BufferedImage buf = sheet.getSubimage(hudImage
							.getBufX(), hudImage.getBufY(), hudImage.getWidth(), 
							hudImage.getHeight());
						Sprite sprite = new Sprite(hudImage.getX(), 
							hudImage.getY(), buf, hudImage.getSTag());
						RECT rect = new RECT(hudImage.getX(), hudImage.getY(), 
							hudImage.getX() + hudImage.getObSize(), 
							hudImage.getY() + hudImage.getObSize(), 
							hudImage.getRTag());
						hudRectList.add(rect);
						hudSpriteList.add(sprite);
					}
				}
			
				//add HUD sprites to back buffer
				if (!hudSpriteList.isEmpty()) {
					ctrl.addBufImageToBackBuffer(hudSpriteList);
				}
				
				//itemGoal
				if (!scriptItemGoals.isEmpty()) {
					ScriptItemGoal keyImage = new ScriptItemGoal();
					keyImage = scriptItemGoals.get(0);
					BufferedImage key = sheet.getSubimage(keyImage.getBufX(), 
						keyImage.getBufY(), keyImage.getWidth(), 
						keyImage.getHeight());
					Sprite sprKey = new Sprite(keyImage.getX(), keyImage.getY(), 
						key, keyImage.getSTag());
					RECT rectKey = new RECT(keyImage.getX(), keyImage.getY(), 
						keyImage.getX() + keyImage.getObSize(), keyImage.getY() 
						+ keyImage.getObSize(), keyImage.getRTag());
					spriteItemList.add(sprKey);
					itemGoalRectList.add(rectKey);
				}
				
				//goal
				if (!scriptSubGoals.isEmpty()) {
					ScriptSubGoal goalImage = new ScriptSubGoal();
					goalImage = scriptSubGoals.get(0);
					BufferedImage goal = sheet.getSubimage(goalImage.getBufX(), 
						goalImage.getBufY(), goalImage.getWidth(), 
						goalImage.getHeight());
					Sprite goalSprite = new Sprite(goalImage.getX(), 
						goalImage.getY(), goal, goalImage.getSTag());
					RECT goalRect = new RECT(goalImage.getX(), goalImage.getY(), 
						goalImage.getX() + goalImage.getObSize(), 
						goalImage.getY() + goalImage.getObSize(), 
						goalImage.getRTag());
					goalSpriteList.add(goalSprite);
					goalRectList.add(goalRect);
					goalX = goalImage.getX();
					goalY = goalImage.getY();
				}
				
				//start
				if (!scriptStartPositions.isEmpty()) {
					ScriptStartPosition start = new ScriptStartPosition();
					start = scriptStartPositions.get(0);
					startX = start.getStartX();
					startY = start.getStartY();
				}
				
				//set initial starting p location
				p.setLocation(startX, startY);
		
				//particle system
				shiny = new Shiny(startX - 16, startY - 16, 64, 64, 8, 64, 8);
				shinyGoal = new Shiny(goalX - 28, goalY - 28, 200, 200, 8, 64, 
					16);

				//music
				if (!scriptSounds.isEmpty()) {
					song = new Sound(scriptSounds.get(0).getFileName());
					song.setLoop();
					backToStart = new Sound(scriptSounds.get(1).getFileName());
					finish = new Sound(scriptSounds.get(2).getFileName());
					gotIt = new Sound(scriptSounds.get(3).getFileName());
				}
				
				//stay on same level until load new level or complete current
				newLevel = false;
			
				//get animations and set RECT for aText
				if (!scriptDrawAnimations.isEmpty()) {
					for (ScriptDrawAnimation draw: scriptDrawAnimations) {
						botJumpAnim = new Animation(draw.getDelay(), 
						draw.getIsLooping());
					for (int i = 0; i < draw.getNumSprites(); i++) {
						botJumpAnim.addFrame(new Frame(draw.getDrawX(), 
						draw.getDrawY(), draw.getTagName() + i));
					}
					animations.add(botJumpAnim);
					RECT rect = new RECT(draw.getDrawX(), draw.getDrawY(), 
						draw.getDrawX() + 64, draw.getDrawY() + 64, 
						"animateRect");
					animateRectList.add(rect);
					}
				}
			}
		
			//mouse cursor
			if (mouseTag != null) {
				ctrl.addSpriteToOverlayBuffer(p.x, p.y, mouseTag);
			}
		
			//coordinate tool
			/*coord = p.toString();                           
			ctrl.drawString(500, 360, coord, Color.WHITE);*/
		
			//draw sprites ***make sure map sprite is in 0***
			if (!spriteList.isEmpty()) {
				for (Sprite spr: spriteList) {
					ctrl.addSpriteToFrontBuffer(spr);
				}
			}
		
			//draw animations
			if (!animations.isEmpty()) {
				for (Animation animate: animations) {
					Frame curFrame = animate.getCurrentFrame();
					if (curFrame != null) {
						ctrl.addSpriteToFrontBuffer(curFrame.getX(), 
							curFrame.getY(), curFrame.getSpriteTag());
					}
				}
			}
		
			//draw goal sprite
			if (!goalSpriteList.isEmpty()) {
				for (Sprite sprite: goalSpriteList) {
					ctrl.addSpriteToFrontBuffer(sprite);
				}
			}
		
			//draw start particles
			ParticleSystem pm = shiny.getParticleSystem();
			Iterator<Frame> it = pm.getParticles();
			while (it.hasNext()) {
				Frame par = it.next();
				Sprite spr = ctrl.getSpriteFromBackBuffer(par.getSpriteTag());
				BufferedImage buf = ctrl.getSpriteFromBackBuffer(spr.getTag())
					.getSprite();
				Sprite sprite = new Sprite(par.getX(), par.getY(), buf, 
					par.getSpriteTag());
				ctrl.addSpriteToFrontBuffer(sprite);
			}
		
			//draw goal particles
			ParticleSystem goalPM = shinyGoal.getParticleSystem();
			Iterator<Frame> goalIT = goalPM.getParticles();
			while (goalIT.hasNext()) {
				Frame par = goalIT.next();
				Sprite spr = ctrl.getSpriteFromBackBuffer(par.getSpriteTag());
				BufferedImage buf = ctrl.getSpriteFromBackBuffer(spr.getTag())
					.getSprite();
				Sprite sprite = new Sprite(par.getX(), par.getY(), buf, 
					par.getSpriteTag());
				ctrl.addSpriteToFrontBuffer(sprite);
			}
		
			//draw itemGoal
			if (!spriteItemList.isEmpty()) {
				if (showItem) {
					for (Sprite spr: spriteItemList) {
						ctrl.addSpriteToFrontBuffer(spr);
					}
				}
			}
		
			//draw texts
			if (!scriptTexts.isEmpty()) {
				scriptTexts = scriptReader.getScriptTexts();
				for (ScriptText txt: scriptTexts) {
					ctrl.drawString(txt.getX(), txt.getY(), txt.getText(), 
						txt.getColor());
				}
			}
		
			//draw hud
			int blackX = 180, blackY = 170, redX = 315, redY = 170;
			int exitX = 170, exitY = 200;
			if (startHud) {
				if (!hudSpriteList.isEmpty()) {
					ctrl.addSpriteToHudBuffer(100, 100, 
					hudSpriteList.get(0).getTag());
					ctrl.drawHudString(116, 125, "Inventory: ", Color.white);
					String inventoryItem = "empty";
					if (!inventoryList.isEmpty()) {
						inventoryItem = itemGoalList.get(level - 1);
					}
					ctrl.drawHudString(230, 125, inventoryItem, Color.white);
					ctrl.drawHudString(116, 155, "Level: ", Color.white);
					ctrl.drawHudString(190, 155, Integer.toString(level), 
						Color.white);
					ctrl.drawHudString(116, 185, "Load", Color.white);
					ctrl.drawHudString(250, 185, "Save", Color.white);
					ctrl.drawHudString(116, 215, "Exit", Color.white);
					ctrl.addSpriteToHudBuffer(blackX, blackY, 
						hudSpriteList.get(1).getTag());
					ctrl.addSpriteToHudBuffer(redX, redY, 
						hudSpriteList.get(2).getTag());
					ctrl.addSpriteToHudBuffer(exitX, exitY, 
						hudSpriteList.get(3).getTag());
				}	
			
				//HUD rects
				int buttonSize = 16;  //sprite size
				if (!hudRectList.isEmpty()) {
					hudRedButton = new RECT(redX, redY, redX + buttonSize, 
					redY + buttonSize, hudRectList.get(1).getTag());
				hudBlackButton = new RECT(blackX, blackY, blackX + buttonSize, 
					blackY + buttonSize, hudRectList.get(0).getTag());
				hudYellowButton = new RECT(exitX, exitY, exitX + buttonSize, 
					exitY + buttonSize, hudRectList.get(2).getTag());
				String load = "Loaded...", save = "Saved...";
					if (loaded) {
						ctrl.drawHudString(230, 215, load, Color.white);
					}
					if (saved) {
						ctrl.drawHudString(230, 215, save, Color.white);
					}
				}
			}
		
			//robot animation
			int delay = 0, botStep = 0;
			boolean isLooping = false;
			if (!scriptAnimations.isEmpty()) {
				delay = scriptAnimations.get(0).getDelay();
				isLooping = scriptAnimations.get(0).getIsLooping();
				botStep = scriptAnimations.get(0).getStep();
			}
			Animation botAnim = new Animation(delay, isLooping);
			if (startOver) {
				curX = startX;
				curY = startY;
				newX = startX;
				newY = startY;
				startOver = false;
				moveRobot = new MoveRobot(botAnim, botStep, curX, curY, startX, 
					startY);
			}
		
			//graphical button hovers
			if (hudBlackButton != null) {
				if (hudBlackButton.isCollision(p.x, p.y)) {
					ctrl.addSpriteToHudBuffer(blackX, blackY, "gHover_button");
				}
			}
			if (hudRedButton != null) {
				if (hudRedButton.isCollision(p.x, p.y)) {
					ctrl.addSpriteToHudBuffer(redX, redY, "gHover_button");
				}
			}
			if (hudYellowButton != null) {
				if (hudYellowButton.isCollision(p.x, p.y)) {
					ctrl.addSpriteToHudBuffer(exitX, exitY, "gHover_button");
				}
			}
		
			//click = left mouse button
			if (Control.getMouseInput() != null) {
				Click click = Control.getMouseInput();
			
				//check for click with animation RECT for aText
				if (!animateRectList.isEmpty()) {
					for (RECT rect: animateRectList) {
						if (rect.isClicked(click, Click.LEFT_BUTTON)) {
							dontGo = true;
							showAText = true;
						}
					}
				}
			
				//check for mouse left click
				if (click.getButton() == 1)	{
					int hudX = (int)p.getX();
					int hudY = (int)p.getY();
					if (!activeHud && !dontGo) {
						newX = (int)p.getX();
						newY = (int)p.getY();
					}
				
					//check for click with HUD RECTS
					if (hudYellowButton != null) {
						if (hudYellowButton.isCollision(hudX, hudY)){
							System.exit(0);
						}
					}
					if (hudRedButton != null) {
						if (hudRedButton.isCollision(hudX, hudY)) {
							activeHud = true;
							buffer = new ArrayList<>();
							buffer.add(level);
							int item = hasItem ? 1 : 0;
							buffer.add(item);
							saveData(buffer);
							saved = true;
							loaded = false;
						}
					}
					if (hudBlackButton != null) {
						if (hudBlackButton.isCollision(hudX, hudY)) {
							loadedGame = true;
							int oldLevel = level;
							buffer = loadData(buffer);
							level = buffer.get(0);
							if (level != oldLevel) {
								startOver = true;
								newLevel = true;
								song.pauseWAV();
								song.resetWAV();
							}
							level = buffer.get(0);
							int item = buffer.get(1);
							if (item == 1) {
								hasItem = true;
								specialItem = 
									itemGoalList.get(level - 1);
								inventoryList.add(specialItem);
							}
							else {
								hasItem = false;
							}
							loaded = true;
							saved = false;
							if (hasItem) {
								showItem = false;
							}
							else {
								showItem = true;
							}
						}
					}
				}
			
				//click = right mouse button
				if (click.getButton() == 3) {
					startHud = !startHud;
					activeHud = !activeHud;
					loaded = false;
					saved = false;
				}
			}
		
			if (showAText) {  //draw animated text
				ctrl.drawString(curX - 25, curY - 15, atext.getCurrentStr(), 
					Color.white);
				if (atext.isAnimationDone()) {
					showAText = false;
					atext.resetAnimation();
					dontGo = false;
				}
			}
		
			if (!activeHud) {
				if (!moveRobot.compareCoords(newX, newY)) {
					if (robotFrame != null) {
						curX = robotFrame.getX();
						curY = robotFrame.getY();
					}
					moveRobot = new MoveRobot(botAnim, botStep, curX, curY, 
						newX, newY);
				}
				robotAnim = moveRobot.getAnimation();
				robotFrame = robotAnim.getCurrentFrame();
				if (robotFrame != null) {
					ctrl.addSpriteToFrontBuffer(robotFrame.getX(), 
						robotFrame.getY(), robotFrame.getSpriteTag());
				}
				curX = robotFrame.getX();
				curY = robotFrame.getY();
				int myBotSize = 32;  //sprite size
				RECT mybot = new RECT(curX, curY, curX + myBotSize, 
					curY + myBotSize, "myBotRECT");
				
				//check for general collision to send back to start
				for (RECT rect: rectList) {
					if (rect.isCollision(rect, mybot)) {
						backToStart.playWAV();
						startOver = true;
					}
				}
			
				//check for itemGoal collision
				for (RECT rect: itemGoalRectList) {
					if (rect.isCollision(rect, mybot)) {
						gotIt.playWAV();
						showItem = false;
						specialItem = rect.getTag();
						inventoryList.add(specialItem);
						hasItem = true;
					}
				}
			
				//check for goal collision
				for (RECT rect: goalRectList) {
				
					//check for mouse collision with goal for text on hover
					String goalName;
					if (rect.isCollision(p.x, p.y)) {
						goalName = rect.getTag();
						showGoal = true;
					}
					else {
						goalName = "";
					}
					ctrl.drawString(p.x, p.y, goalName, Color.white);
				
					//check for bot collision
					if (rect.isCollision(rect, mybot)) {
						if (hasItem) {
							loadedGame = false;
							finish.playWAV();
							level += 1;
							startOver = true;
							newLevel = true;
							song.pauseWAV();
							song.resetWAV();
							inventoryList.clear();
							showItem = true;
							if (level == 4) {
								playGame = false;
							}
						}
						else {
							showGoal = false;
							ctrl.drawString(rect.getX1() - 30, rect.getY2() + 
								20, "Missing something...", Color.white);
						}
					}
				}
			}
		}
	}

	
	// Additional Static methods below...(if needed)
	//create a routine to save the game data
	public static void saveData(ArrayList<Integer> buf) {
		
		//save data to a String to output...
		String out = "";
		for (int i = 0; i < buf.size(); i++)
			out += buf.get(i) + "*";
		out = out.substring(0, out.length() - 1);  //remove trailing delimiter
		
		//save output String to file
		EZFileWrite ezw = new EZFileWrite("save.txt");
		ezw.writeLine(out);
		ezw.saveFile();
	}
	
	//create a routine to restore the game data
	public static ArrayList<Integer> loadData(ArrayList<Integer> buf) {
		
		//retrieve data from the file
		EZFileRead ezr = new EZFileRead("save.txt");
		String raw = ezr.getLine(0);  //read our one and only line (index #0)
		
		//break this down into tokens
		StringTokenizer st = new StringTokenizer(raw, "*");
		if (st.countTokens() != buf.size()) {
			return buf;  //these must match!!!
		}
		else {
			buf = new ArrayList<>();
		}
		for (int i = 0; i < buffer.size(); i++) {
			String value = st.nextToken();
			int val = Integer.parseInt(value);
			buf.add(val);
		}
		return buf;
	}
}