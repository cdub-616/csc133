package particles;

public class Shiny {
	//fields
	private ParticleSystem parts;
	private String[] spriteTags;
	
	//constructor
	public Shiny(int xpos, int ypos, int xrange, int yrange, int minlife, int maxlife, int numparticles) {
		spriteTags = new String[7];
		spriteTags[0] = "shiny1";
		spriteTags[1] = "shiny2";
		spriteTags[2] = "shiny3";
		spriteTags[3] = "shiny4";
		spriteTags[4] = "shiny5";
		spriteTags[5] = "shiny6";
		spriteTags[6] = "shiny7";
		int xspeed = 0;
		int yspeed = 0;
		parts = new ParticleSystem(numparticles, xpos, ypos, xrange, yrange, minlife, maxlife, xspeed, yspeed, 0, 10, spriteTags);
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
			int life;
			int age = pa[i].getAge();
			if (age == 0) {
				int newX = Particle.getRandomInt(minX, maxX);
				int newY = Particle.getRandomInt(minY, maxY);
				life = Particle.getRandomInt(pa[i].getMinLife(), pa[i].getmaxLife());
				int delay = Particle.getRandomInt(pa[i].getMindelay(), pa[i].getMaxdelay());
				pa[i].changeX(newX);
				pa[i].changeY(newY);
				pa[i].setDelay(delay);
			}
			else 
				life = pa[i].getLifeCycle();
			pa[i].setLifeCycle(life);
			int range = life / stages;
			if (range > 2)
				range = 2;
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
