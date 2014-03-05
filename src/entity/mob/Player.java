package entity.mob;

import java.util.List;

import level.Level;
import entity.Entity;
import entity.projecttile.WizardProjectile;
import game.Game;
import graphics.AnimatedSprite;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;
import input.Keyboard;
import input.Mouse;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;				// animation counter (0 - 59) 
	private int flip = 0;				// flip the current player sprite?!
	private int fireRate = 0;
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 0, 0, 5);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 0, 0, 5);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 0, 0, 5);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 0, 0, 5);
	
	private AnimatedSprite animSprite = null;
	
	public Player(Keyboard input) {
		this.input = input;
		this.sprite = Sprite.player_forward;
		animSprite = down;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		animSprite = down;
		fireRate = WizardProjectile.FIRE_RATE;
	}
	
	@Override
	public void update() {
//		animSprite = right;
		
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		int xa=0, ya=0;
		if (input.up) {
			animSprite = up;
			ya-=2;
		}
		if (input.down) {
			animSprite = down;
			ya+=2;
		}
		if (input.left) {
			animSprite = left;
			xa-=2;
		}
		if (input.right) {
			animSprite = right;
			xa+=2;
		}
		
		if (xa != 0 || ya != 0) { 
			move (xa, ya);
			walking = true;
		} else {			
			walking = false;
		}
		
		clear();
		updateShooting();
	}

	private void clear() {
		for (int i=0; i<level.getEntities().size(); i++) {
			Entity e = level.getEntities().get(i);
			if (e.isRemoved()) {
				level.getEntities().remove(i);
			}
		}
	}

	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			int dx = Mouse.getX() - Game.getWindowWidth() / 2;
			int dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			
 			shoot(x, y, dir);
 			fireRate = WizardProjectile.FIRE_RATE;
		}
	}
	
	@Override
	public void render(Screen screen) {
		if (anim < 7500) anim++; 
		else anim = 0;
		
		if (fireRate > 0) fireRate--;
		
		sprite = animSprite.getSprite();
		screen.renderSprite(x, y, sprite, flip);
	}
}
