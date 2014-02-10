package entity.projecttile;

import entity.Entity;
import entity.Spawner;
import entity.Spawner.Type;
import graphics.Screen;
import graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 10;
	
	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
//		range = random.nextInt(100) + 50;
		damage = 20;
		rateOfFire = 15;
		speed = 4;
		sprite = Sprite.projectile_wizard;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	@Override
	public void update() {
		if (level.entityCollision(x, y, nx, ny, 7)) {
			Entity p = new Spawner((int)x, (int)y, Type.PARTICLE, 20);
			level.addEntity(p);
			remove();
		}
		move();
	}

	@Override
	protected void move() {
		x += nx;
		y += ny;
			
		if (distance() > range) {
			remove();
		}
	}
	
	private double distance() {
		double dist = 0; 
		dist = Math.sqrt(Math.abs(Math.pow((xOrigin - x), 2)) + Math.abs(Math.pow((yOrigin - y), 2)));
		return dist;
	}

	@Override
	public void render(Screen screen) {
		screen.renderTile((int) x - 14, (int) y -2, sprite);
	}
}

