package graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	
	public static Sprite player_forward = new Sprite(32, 0, 4, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_side = new Sprite(32, 0, 5, SpriteSheet.tiles);
	
	public static Sprite player_forward_1 = new Sprite(32, 1, 4, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 2, 4, SpriteSheet.tiles);

	public static Sprite player_side_1 = new Sprite(32, 1, 5, SpriteSheet.tiles);
	public static Sprite player_side_2 = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_side_3 = new Sprite(32, 3, 5, SpriteSheet.tiles);
	public static Sprite player_side_4 = new Sprite(32, 4, 5, SpriteSheet.tiles);

	public static Sprite player_back_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int colour) {
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColour(colour);
	}
	
	private void setColour(int colour) {
		for (int i=0; i<SIZE*SIZE; i++) {
			pixels[i] = colour;
		}
	}
	
	private void load() {
		for (int y=0; y<SIZE; y++) {
			for (int x=0; x<SIZE; x++) {
				pixels[x+y*SIZE] = sheet.pixels[(this.x + x) + (this.y + y) * sheet.SIZE];
			}
		}
	}
	
}
