package entity.mob;

import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	private int step = 0;

	public Player(Keyboard input) {
		this.input = input;
		this.sprite = Sprite.player_forward;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	@Override
	public void update() {
		int xa=0, ya=0;
		if (anim < 7500) anim++; 
		else anim = 0;
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
	}

	int flip = 0;
	@Override
	public void render(Screen screen) {
		flip = 0;
		if (dir == 0) {
			sprite = moveAnimationPlayer(Sprite.player_back, Sprite.player_back_1, Sprite.player_back_2);
		}
		if (dir == 1) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_side_1;
				} else {
					sprite = Sprite.player_side_2;
				}
			}
			flip = 1;
		}
		if (dir == 2) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_forward_1;
				} else {
					sprite = Sprite.player_forward_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_side_1;
				} else {
					sprite = Sprite.player_side_2;
				}
			}
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);
	}

	private Sprite moveAnimationPlayer(Sprite stand,
			Sprite step1, Sprite step2) {
		Sprite sprite = stand;
		if (walking) {
			if (anim % 10 == 0) {
				if (step > 3) step = 0;
				step++;
				//System.out.println(step);
			}
			if (anim % 20 > 10) {
				sprite = step1;
				flip = 1;
//				if (step == 0) flip = 0; 
				if (step == 3) flip = 1;
			} else {
				sprite = step2;
				if (step == 1) flip = 0; 
//				if (step == 2) flip = 1;
			}
		}
		return sprite;
	}
}
