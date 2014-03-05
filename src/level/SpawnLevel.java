package level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.mob.Chaser;
import entity.mob.Dummy;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		BufferedImage image;
		try {
			image = ImageIO.read(SpawnLevel.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file! " + e.getMessage());
		}
		addEntity(new Chaser(20, 58));
//		addEntity(new Dummy(20, 58));
	}
	
	/*
	 * Grass 	= green
	 * Flower 	= yellow
	 * Rock 	= grey
	 */
	@Override
	protected void generateLevel() {
	}
}
