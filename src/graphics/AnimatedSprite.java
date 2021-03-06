package graphics;


public class AnimatedSprite extends Sprite {

	private int frame = 0;
	private Sprite sprite;
	private int rate = 8;
	private int time = 0;
	private int length = -1;
	
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		super(sheet, width, height);
		this.length = length;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length) System.err.println("Error! Length of animation is to long");
	}
	
	public void update() {
		time++;
		if (time % rate ==0) {
			if (frame >= length - 1) frame = 0;
			else frame++;
			sprite = sheet.getSprites()[frame];
			time = 0;
		}
//		System.out.println(sprite + ", Frame: " + frame);
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setFrameRate(int frames) {
		this.rate = frames;
	}

	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1) {
			throw new IndexOutOfBoundsException("Out of Range");
		}
		sprite = sheet.getSprites()[index];
	}
}
