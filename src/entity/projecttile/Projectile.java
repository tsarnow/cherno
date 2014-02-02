package entity.projecttile;

import java.util.Date;
import java.util.Random;

import entity.Entity;
import graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double x, y;
	protected double distance;
	protected double speed, rateOfFire, range, damage;
	
	protected final Random random = new Random(new Date().getTime());
	
	public Projectile(int x, int y, double dir) {
		this.x = x; 
		this.y = y;
		xOrigin = x;
		yOrigin = y;
		angle = dir;
	}

	protected void move() {
		
	}
}
