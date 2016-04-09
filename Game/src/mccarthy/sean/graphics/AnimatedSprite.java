package mccarthy.sean.graphics;

public class AnimatedSprite extends Sprite {

	private int frame = 0;
	private Sprite sprite;
	private int time = 0;
	private int rate = 5;
	private int length = -1;

	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		super(sheet, width, height);
		this.length = length;
		if (length > sheet.getSprites().length) System.err.println("Error: Length of Animation is Too Long");
	}

	public void update() {
		time++;
		if (time % rate == 0) {
			if (frame > length)
				frame = 0;
			else
				frame++;
			sprite = sheet.getSprites()[frame];
		}
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setFrameRate(int frames) {
		rate = frames;
	}

}
