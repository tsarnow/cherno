package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public static final SpriteSheet tiles = new SpriteSheet("/textures/grid.png", 256);
	public static final SpriteSheet spawn_level = new SpriteSheet("/textures/spawn_level.png", 48);
	public static final SpriteSheet projectile_wizard = new SpriteSheet("/textures/projectile.png", 48);
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
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
}
