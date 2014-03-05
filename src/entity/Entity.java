package entity;

import graphics.Screen;

import java.util.Date;
import java.util.Random;

import level.Level;

public abstract class Entity {

	protected int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random(new Date().getTime());
	
	abstract public void update();
	
	public void render(Screen screen) {
	}
	
	public void remove() {
		// Remove from level
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
