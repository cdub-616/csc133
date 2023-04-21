package particles;

public class Shiny {
	//fields
	private ParticleSystem parts;
	private String[] spriteTags;
	
	//constructor
	public Shiny(int xpos, int ypos, int xrange, int yrange, int minlife, int maxlife, int numparticles) {
		spriteTags = new String[4];
		spriteTags[0] = "shiny4";
		spriteTags[1] = "shiny5";
		spriteTags[2] = "shiny6";
		spriteTags[3] = "shiny7";
		int xspeed = 0;
		int yspeed = 0;
		parts = new ParticleSystem(numparticles, xpos, ypos, xrange, yrange, minlife, maxlife, xspeed, yspeed, 0, 0, spriteTags);
	}
	
	//methods
	//update the particles based on the life cycle (and the tile set)
	private void updateParticleSprites() {
		Particle[] pa = parts.getParticleArray();
		for (int i = 0; i < pa.length; i++) {
			int minX = pa[i].getMinX();
			int minY = pa[i].getMinY();
			int maxX = pa[i].getMaxX();
			int maxY = pa[i].getMaxY();
			int stages = spriteTags.length;
			int life = pa[i].getLifeCycle();
			int range = life / stages;
			int age = pa[i].getAge();
			if (age == 0) {
				int newX = pa[i].getRandomInt(minX, maxX);
				int newY = pa[i].getRandomInt(minY, maxY);
				pa[i].changeX(newX);
				pa[i].changeY(newY);
			}
			for (int j = 0; j < stages; j++) {
				if (age >= (range * j) && age < (range * (j + 1))) {
					pa[i].changeSprite(spriteTags[j]);
				}
			}
		}
	}
	
	public ParticleSystem getParticleSystem() {
		updateParticleSprites();
		return parts;
	}
	
}
