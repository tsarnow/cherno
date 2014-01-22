package entity.mob;

import entity.Entity;
import graphics.Screen;
import graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;				// direction of moving
	protected boolean moving = false;
	
	public void move(int xa, int ya) {
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		if (!collision()) {
			x += xa;
			y += ya;
		}
	}
	
	abstract public void render(Screen screen);
	
	private boolean collision() {
		return false;
	}
}
