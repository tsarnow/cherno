package level;

import java.util.Date;
import java.util.Random;

public class RandomLevel extends Level {

	private static final Random random = new Random(new Date().getTime());
	
	public RandomLevel(int width, int height) {
		super(width, height);
	}

	/**
	 * Generates a random set of tiles
	 */
	@Override
	protected void generateLevel() {
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				tiles[x+y*width] = random.nextInt(4);
			}
		}
	}
}
