package particles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Data.Frame;

public class ParticleSystem {
	//fields
	private Particle[] particles;
	private int x, y;
	private int xrange, yrange;
	private int maxlife;
	private String spriteTags[];
	
	//constructor
	public ParticleSystem(int numParticles, int x, int y, int xrange, int yrange, int minlife, int maxlife, int xmove, int ymove, int mindelay, int maxdelay, String[] spriteTags) {
		this.xrange = xrange;
		this.yrange = yrange;
		this.x = x;
		this.y = y;
		this.maxlife = maxlife;
		particles = new Particle[numParticles];
		this.spriteTags = spriteTags;
		initParticles(xmove, ymove, mindelay, maxdelay, minlife);
	}
	
	//methods
	//init particles
	//this sets up all of the particles individually in the system
	private void initParticles(int xmove, int ymove, int mindelay, int maxdelay, int _minlife) {
		for (int i = 0; i < particles.length; i++) {
			int n = spriteTags.length;
			int index = Particle.getRandomInt(0, n - 1);
			particles[i] = new Particle(x, x + xrange, y, y + yrange, spriteTags[index], _minlife, maxlife, xmove, ymove, mindelay, maxdelay);
		}
		//age them until all are through at least one life cycle...
		boolean isDone = false;
		while (isDone == false) {
			isDone = true;  //set as true for the beginning
			for (int i = 0; i < particles.length;  i++) {
				particles[i].simulateAge();
				if (particles[i].hasBeenReset() == false)
					isDone = false;
			}
		}
		//all particles past this point should be aged
	}
	
	//the getters
	//access to the actual Particle array, as needed by the Fire class...
	public Particle[] getParticleArray() {
		return particles;
	}
	
	//retrieve an iterator that includes ALL particles currently...
	public Iterator<Frame> getParticles(){
		List<Frame> parts = new ArrayList<>();
		for (int i = 0; i < particles.length; i++) {
			Frame tmp = particles[i].getCurrentFrame();
			parts.add(tmp);
		}
		return parts.iterator();
	}
}
