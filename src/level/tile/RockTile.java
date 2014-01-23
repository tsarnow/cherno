package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class RockTile extends Tile {

	public RockTile(Sprite sprite) {
		super(sprite);
	}

	/**
	 * @param x start x position of tile
	 * @param y start y position of tile
	 */
	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	@Override
	public boolean solid() {
		return true;
	}

}
