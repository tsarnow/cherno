package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
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

}
