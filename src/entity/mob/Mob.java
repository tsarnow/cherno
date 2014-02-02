package entity.mob;

import level.Level;
import entity.Entity;
import entity.projecttile.Projectile;
import entity.projecttile.WizardProjectile;
import graphics.Screen;
import graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;				// direction of moving
	protected boolean moving = false;
	
	public void move(int xa, int ya) {
		
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		} 
	}
	
	@Override
	public void update() {
		
	}
	
	abstract public void render(Screen screen);
	
	protected void shoot(int x, int y, double dir) {
		
		Projectile p = new WizardProjectile(x, y, dir);
		level.addEntity(p);
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;

		// corner magic :D c % 2 = 0, 1, 0, 1
		for (int c=0; c<4; c++) {
			int xt = ((x+xa) + c % 2 * 12 -7) >> 4;
			int yt =   ((y+ya) + c / 2 * 12 + 1) >> 4;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void init(Level level) {
		this.level = level;
	}
}
