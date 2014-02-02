package entity.mob;

import entity.Entity;
import entity.projecttile.WizardProjectile;
import game.Game;
import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;
import input.Mouse;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;				// animation counter (0 - 59) 
	private boolean walking = false;	// is char moving?
	private int step = 0;				// how many steps are made (1 - 4)
	private int flip = 0;				// flip the current player sprite?!
	private int fireRate = 0;
	
	public Player(Keyboard input) {
		this.input = input;
		this.sprite = Sprite.player_forward;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		fireRate = WizardProjectile.FIRE_RATE;
	}
	
	@Override
	public void update() {
		int xa=0, ya=0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		
		if (xa != 0 || ya != 0) { 
			move (xa, ya);
			walking = true;
		} else {			
			walking = false;
			step = 0;
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
		
		flip = 0;
		if (dir == 0) {
			sprite = moveAnimationPlayer(Sprite.player_back, Sprite.player_back_1, Sprite.player_back_2);
		}
		if (dir == 1) {
			sprite = moveAnimationPlayer(Sprite.player_side, Sprite.player_side_1, Sprite.player_side_2, Sprite.player_side_3, Sprite.player_side_4);
			flip = 1;
		}
		if (dir == 2) {
			sprite = moveAnimationPlayer(Sprite.player_forward, Sprite.player_forward_1, Sprite.player_forward_2);
		}
		if (dir == 3) {
			sprite = moveAnimationPlayer(Sprite.player_side, Sprite.player_side_1, Sprite.player_side_2, Sprite.player_side_3, Sprite.player_side_4);
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);
	}

	// side animation for player
	private Sprite moveAnimationPlayer(Sprite stand,
			Sprite step1, Sprite step2, Sprite step3,
			Sprite step4) {
		Sprite sprite = stand;
		if (walking) {
			calculateAnimationSpeed(8);
			if (step == 1) {
				sprite = step1;
			} else if (step == 2) {
				sprite = step2;
			} else if (step == 3) {
				sprite = step3;
			} else {
				sprite = step4;
			}
		}
		
		return sprite;
	}

	private void calculateAnimationSpeed(int speed) {
		if (anim % speed == 0) {
			if (step > 3) {
				step = 0;
			}
			step++;
		}
	}

	private Sprite moveAnimationPlayer(Sprite stand,
			Sprite step1, Sprite step2) {
		Sprite sprite = stand;
		if (walking) {
			calculateAnimationSpeed(10);
			if (step == 1) {
				sprite = step1;
				flip = 0;
			} else if (step == 2) {
				sprite = step2;
			} else if (step == 3) {
				sprite = step2;
				flip = 1;
			} else {
				sprite = step1;
				flip = 1;				
			}
		}
		return sprite;
	}
}
