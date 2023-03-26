package Graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Data.Sprite;

public class TileMap {
	//fields
	private Sprite spr;
	private BufferedImage map;
	
	//constructor
	public TileMap(BufferedImage buf) {
		BufferedImage map = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < 10; i++) 
			for (int j = 0; j < 6; j++) {
				int x = i << 7;
				int y = (j << 7) - 40;
				Graphics g = map.getGraphics();
				g.drawImage(buf, x, y, null);
			}
		spr = new Sprite(0, 0, map, "map");
	}
	
	//methods
	public Sprite getSprite() {
		return spr;
	}
}
