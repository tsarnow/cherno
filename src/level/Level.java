package level;

import level.tile.Tile;
import graphics.Screen;

public abstract class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	public static Level spwan = new SpawnLevel("/textures/level/spawn.png");
	
	public Level(int width, int height) {
		this.width = width; 
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
	
	abstract protected void generateLevel();
	
	abstract protected void loadLevel(String path);
	
	public void update() {
		
	}
	
	private void time() {
		
	}
	
	/** 
	 * Renders the tiles according to tiles stored in Level implementation. 
	 * @param xScroll where the player is on x
	 * @param yScroll where the player is on y
	 * @param screen
	 */
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);			// sets the offset for the player
		int x0 = xScroll >> 4;						// most visible left
		int x1 = (xScroll + screen.width + 16) >> 4;		// most visible right
		int y0 = yScroll >> 4;						// most visible top
		int y1 = (yScroll + screen.height + 16) >> 4; 	// most visible bottom

		// go through all visible tiles in the screen
		for (int y=y0; y<y1; y++) {
			for (int x=x0; x<x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x+y*width] == Tile.col_spwan_floor) return Tile.spawn_floor;
		if (tiles[x+y*width] == Tile.col_spwan_grass) return Tile.spawn_grass;
		if (tiles[x+y*width] == Tile.col_spwan_wall1) return Tile.spawn_wall1;
		if (tiles[x+y*width] == Tile.col_spwan_wall2) return Tile.spawn_wall2;
		return Tile.voidTile;
 	}
}
