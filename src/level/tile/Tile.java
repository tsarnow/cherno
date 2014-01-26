package level.tile;

import graphics.Screen;
import graphics.Sprite;
import level.tile.spwan_level.SpawnFloorTile;
import level.tile.spwan_level.SpawnGrassTile;
import level.tile.spwan_level.SpawnHedgeTile;
import level.tile.spwan_level.SpawnWallTile;
import level.tile.spwan_level.SpawnWaterTile;

public abstract class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawn_wall1);
	public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);
	
	public final static int col_spwan_grass = -16711936;
	public final static int col_spwan_floor = -8372224;
	public final static int col_spwan_wall1 = -8421505;
	public final static int col_spwan_wall2 = -16777216;
	public final static int col_spawn_point = -16711681;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	/**
	 * @param x start x position of tile
	 * @param y start y position of tile
	 */
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return false;
	}
}
