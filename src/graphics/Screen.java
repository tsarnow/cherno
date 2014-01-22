package graphics;

import level.tile.Tile;

public class Screen {

	public int width;
	public int height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	
	public int xOffset, yOffset;
	
	public int[] tiles = new int [MAP_SIZE * MAP_SIZE];
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}
	
	public void clear() {
		for (int i=0; i<pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	/**
	 * Renders a tile to the screen (pixels). 
	 * @param xp tile position on x screen
	 * @param yp tile position on y screen
	 * @param tile
	 */
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y=0; y<tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x=0; x<tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) {
					break;
				}
				if (xa < 0) {
					// in the tutorial the mechanism is xa = 0;  
					continue; 	// if x coordinate is out of screen index -> contine (should not be visible)
				}
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	public void renderPlayer(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y=0; y<32; y++) {
			int ya = y + yp;
			for (int x=0; x<32; x++) {
				int xa = x + xp;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) {
					break;
				}
				if (xa < 0) {
					// in the tutorial the mechanism is xa = 0;  
					continue; 	// if x coordinate is out of screen index -> contine (should not be visible)
				}
				int col = sprite.pixels[x+y*32];
				// pink color on mac Oo
				if (col != -327425) pixels[xa + ya * width] = sprite.pixels[x+y*32];
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
