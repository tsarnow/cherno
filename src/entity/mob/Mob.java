package entity.mob;

import level.Level;
import entity.Entity;
import entity.projecttile.Projectile;
import entity.projecttile.WizardProjectile;
import graphics.Screen;
import graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected Direction dir = Direction.DOWN;				// direction of moving
	protected boolean walking = false;
	
	protected enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	
	public void move(double xa, double ya) {
		
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = Direction.RIGHT;
		if (xa < 0) dir = Direction.LEFT;
		if (ya > 0) dir = Direction.DOWN;
		if (ya < 0) dir = Direction.UP;
		
		for (int x=0; x < Math.abs(xa); x++) {
			if (!collision(abs(xa), ya)) {
				this.x += abs(xa);
			}
		}
		
		for (int y=0; y < Math.abs(ya); y++) {
			if (!collision(xa, abs(ya))) {
				this.y += abs(ya);
			}
		}
	}
	
	private double abs(double value) {
		if (value < 0) return -1;
		return 1;
	}

	public Sprite getSprite() {
		return sprite;
	}
	
	public abstract void render(Screen screen);
	
	protected void shoot(int x, int y, double dir) {
		
		Projectile p = new WizardProjectile(x, y, dir);
		level.addEntity(p);
	}
	
	private boolean collision(double xa, double ya) {
		boolean solid = false;

		// corner magic :D c % 2 = 0, 1, 0, 1
		for (int c=0; c<4; c++) {
			double xt = ((x + xa) + c % 2 * 16) / 16;
			double yt = ((y + ya) + c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}
	
	public void init(Level level) {
		this.level = level;
	}
}
