package entity.mob;

import graphics.AnimatedSprite;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Dummy extends Mob {
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 0, 0, 6);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 0, 0, 6);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 0, 0, 6);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 0, 0, 6);
	
	private AnimatedSprite animSprite = down;
	
	private int time = 0;
	private int xa = 0; 
	private int ya = 0;
	
	public Dummy(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	@Override
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x, y, sprite, 0);
	}

	@Override
	public void update() {
		time++;
		if (time > 3600) time = 0; // limit for 1 minute
		
		if (time % (random.nextInt(50) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if (random.nextInt(3) == 0) {
				xa = 0;
				ya = 0;
			}
		}
		
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
		
		if (xa != 0 || ya != 0) { 
			move (xa, ya);
			walking = true;
		} else {			
			walking = false;
		}
	}

}
