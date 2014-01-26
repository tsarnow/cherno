package level.tile;

import graphics.Sprite;

public class RockTile extends Tile {

	public RockTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public boolean solid() {
		return true;
	}

}
