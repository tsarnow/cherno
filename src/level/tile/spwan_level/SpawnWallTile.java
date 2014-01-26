package level.tile.spwan_level;

import graphics.Sprite;
import level.tile.Tile;

public class SpawnWallTile extends Tile {

	public SpawnWallTile(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	public boolean solid() {
		return false;
	}
}
