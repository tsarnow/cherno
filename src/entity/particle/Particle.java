package entity.particle;

import entity.Entity;
import graphics.Screen;
import graphics.Sprite;

public class Particle extends Entity {
	
	private Sprite sprite;
	private int life;
	private int time = 0;
	
	protected double xx, yy, xa, ya;
	
	public Particle(int x, int y, int life) {
		this.life = life + (random.nextInt(20) - 10);
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		sprite = Sprite.particle_normal;
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}
	
	@Override
	public void update() {
		time ++;
		if (time > 5000) time = 0;
		if (time > life) remove();
		this.xx += xa;
		this.yy += ya;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy, sprite, true);
	}

	public boolean sameRandom(Particle last) {
		if (xa == last.xa && ya == last.ya) {
			return true;
		}
		return false;
	}

}
