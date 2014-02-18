package graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;
	private int width;
	private int height;
	
	public static Sprite grass = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 0, 6, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	
	//Spawn Level sprites here
	public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_hedge = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_wall1 = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_wall2 = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_floor = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
	
	//Player sprites here:
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
	
	//Projectile sprite
	public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);
	
	//Particle
	public static Sprite particle_normal = new Sprite(3, 0xAAAAAA);
	
	public static Sprite dummy = new Sprite(32, 0, 0, SpriteSheet.dummy_down);
	
	protected Sprite(SpriteSheet sheet, int width, int height) {
		if (width == height) SIZE = width;
		else SIZE = -1;
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.pixels = sheet.pixels;
	}
	
	public Sprite (int width, int height, int colour) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColour(colour);
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.width = size;
		this.height = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int colour) {
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.width = size;
		this.height = size;
		setColour(colour);
	}
	
	public Sprite(int[] pixels, int width, int height) {
		if (width == height) SIZE = width;
		else SIZE = -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	private void setColour(int colour) {
		for (int i=0; i<width*height; i++) {
			pixels[i] = colour;
		}
	}
	
	private void load() {
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				pixels[x+y*width] = sheet.pixels[(this.x + x) + (this.y + y) * sheet.WIDTH];
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
