package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] pixels;
	
	public static final SpriteSheet tiles = new SpriteSheet("/textures/grid.png", 256);
	public static final SpriteSheet spawn_level = new SpriteSheet("/textures/spawn_level.png", 48);
	public static final SpriteSheet projectile_wizard = new SpriteSheet("/textures/projectile.png", 48);
	
	public static final SpriteSheet player = new SpriteSheet("/textures/player.png", 160, 128);
	public static final SpriteSheet player_down = new SpriteSheet(player, 0, 0, 5, 1, 32);
	public static final SpriteSheet player_left = new SpriteSheet(player, 0, 1, 5, 1, 32);
	public static final SpriteSheet player_up = new SpriteSheet(player, 0, 2, 5, 1, 32);
	public static final SpriteSheet player_right = new SpriteSheet(player, 0, 3, 5, 1, 32);
	
	public static final SpriteSheet dummy = new SpriteSheet("/textures/pokemon/poke_player.png", 256, 256);
	public static final SpriteSheet dummy_down = new SpriteSheet(dummy, 0, 6, 6, 1, 32);
	public static final SpriteSheet dummy_left = new SpriteSheet(dummy, 0, 4, 6, 1, 32);
	public static final SpriteSheet dummy_up = new SpriteSheet(dummy, 0, 7, 6, 1, 32);
	public static final SpriteSheet dummy_right = new SpriteSheet(dummy, 0, 5, 6, 1, 32);
	
	private Sprite[] sprites;
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		
		// copy the pixels from sheet to new instance
		for (int yi=0; yi < h; yi++) {
			int yp = yy + yi;
			for (int xi=0; xi < w; xi++) {
				int xp = xx + xi;
				pixels[xi + yi * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		
		// loop for images within the sheet
		int frame = 0;
		this.sprites = new Sprite[height * width];
		for (int ya=0; ya < height; ya++) {
			for (int xa=0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				
				// loop the pixels within the image
				for (int yi=0; yi < spriteSize; yi++) {
					for (int xi=0; xi < spriteSize; xi++) {
						spritePixels[xi + yi * spriteSize] = pixels[(xi + xa * spriteSize) + (yi + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
			
		}
	}
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		loadImage();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		pixels = new int[width * height];
		WIDTH = width;
		HEIGHT = height;
		SIZE = -1;
		loadImage();
	}
	
	private void loadImage() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Sprite[] getSprites(){
		return sprites;
	}
}
