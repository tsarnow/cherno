package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x, y, this);
	}

}
