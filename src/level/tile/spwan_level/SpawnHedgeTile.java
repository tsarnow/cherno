package level.tile.spwan_level;

import graphics.Sprite;
import level.tile.Tile;

public class SpawnHedgeTile extends Tile {

	public SpawnHedgeTile(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	public boolean solid() {
		return false;
	}
}
