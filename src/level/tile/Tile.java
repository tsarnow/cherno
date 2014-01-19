package level.tile;

import graphics.Screen;
import graphics.Sprite;

public abstract class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public abstract void render(int x, int y, Screen screen);
	
	public boolean solid() {
		return false;
	}
}
