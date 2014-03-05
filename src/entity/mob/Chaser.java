package entity.mob;

import java.util.List;

import entity.Entity;

import graphics.AnimatedSprite;
import graphics.Screen;
import graphics.SpriteSheet;

public class Chaser extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 0, 0, 6);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 0, 0, 6);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 0, 0, 6);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 0, 0, 6);
	
	private AnimatedSprite animSprite = down;
	
	private int xa = 0; 
	private int ya = 0;
	
	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = animSprite.getSprite();
	}
	
	@Override
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, this);
	}

	@Override
	public void update() {
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		
		if (ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		} else if (ya > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		} else if (xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}
		move();
	}

	private void move() {
		xa = 0;
		ya = 0;

		List<Entity> players = level.getPlayers(this, 50);
		if (players.size() > 0) {
			Player player = (Player) players.get(0);
			if (x < player.getX() + 20) xa++;
			if (x > player.getX() - 20) xa--;
			if (y < player.getY() + 20) ya++;
			if (y > player.getY() - 20) ya--;
		}
		if (xa != 0 || ya != 0) { 
			move (xa, ya);
			walking = true;
		} else {			
			walking = false;
		}
	}

}
