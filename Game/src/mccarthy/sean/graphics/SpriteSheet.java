package mccarthy.sean.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] pixels;

	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/groundsprites.png", 512);
	public static SpriteSheet player = new SpriteSheet("/textures/sheets/player.png", 256, 192);

	public static SpriteSheet test = new SpriteSheet(player, 0, 0, 1, 3, 64);
	
	private Sprite[] sprites;

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height)
			SIZE = width;
		else
			SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		int frame = 0;
		for (int ya = 0; ya < h; y++) {
			for (int xa = 0; xa < w; xa++) {
				int[] spritePixels = new int [spriteSize * spriteSize];
				for (int y0 = 0; y0 < h; y++) {
					for (int x0 = 0; x0 < w; x0++) {
						spritePixels[x0 + y0 * spriteSize]  = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) *width];
						
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
