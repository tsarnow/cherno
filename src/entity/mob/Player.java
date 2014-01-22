package entity.mob;

import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	

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
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		
		if (xa != 0 || ya != 0) move (xa, ya);
	}

	@Override
	public void render(Screen screen) {
		if (dir == 0) sprite = Sprite.player_back;
		if (dir == 1) sprite = Sprite.player_right;
		if (dir == 2) sprite = Sprite.player_forward;
		if (dir == 3) sprite = Sprite.player_left;
		screen.renderPlayer(x - 16, y - 16, sprite);
	}
}
