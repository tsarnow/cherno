package entity.particle;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import graphics.Screen;
import graphics.Sprite;

public class Particle extends Entity {
	
	private List<Particle> particles = new ArrayList<Particle>();
	private Sprite sprite;
	
	private int life;
	
	protected double xx, yy, xa, ya;
	
	public Particle(int x, int y, int life) {
		this.life = life;
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		sprite = Sprite.particle_normal;
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}
	
	public Particle(int x, int y, int life, int amount) {
		this(x, y, life);
		particles.add(this);
		for (int i=0; i<amount - 1; i++) {
			particles.add(new Particle(x, y, life));
		}
	}

	@Override
	public void update() {
		this.xx += xa;
		this.yy += ya;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy, sprite, true);
	}

}
