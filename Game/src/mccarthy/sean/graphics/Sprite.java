package mccarthy.sean.graphics;

import mccarthy.sean.Game;

public class Sprite {

	public final int SIZE;
	private int width, height;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;

	public static Sprite grass = new Sprite(Game.TILE_SIZE, 0, 0, SpriteSheet.tiles);
	public static Sprite floor = new Sprite(Game.TILE_SIZE, 1, 0, SpriteSheet.tiles);
	public static Sprite wall = new Sprite(Game.TILE_SIZE, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(Game.TILE_SIZE, 0x100010);

	public static Sprite playeru1 = new Sprite(64, 0x4A0000);
	public static Sprite playeru2 = new Sprite(64, 0x8A0000);
	public static Sprite playeru3 = new Sprite(64, 0xCA0000);
	public static Sprite playerl1 = new Sprite(64, 0x004A00);
	public static Sprite playerl2 = new Sprite(64, 0x008A00);
	public static Sprite playerl3 = new Sprite(64, 0x00CA00);
	public static Sprite playerd1 = new Sprite(64, 0x4A004A);
	public static Sprite playerd2 = new Sprite(64, 0x8A008A);
	public static Sprite playerd3 = new Sprite(64, 0xCA00CA);
	public static Sprite playerr1 = new Sprite(64, 0x00004A);
	public static Sprite playerr2 = new Sprite(64, 0x00008A);
	public static Sprite playerr3 = new Sprite(64, 0x00004CA);

	public static Sprite basicProjectile = new Sprite(16, 0xFF0000);

	public static Sprite normalParticle = new Sprite(4, 0xFF0000);

	protected Sprite(SpriteSheet sheet, int width, int height) {
		if (width == height) SIZE = width;
		else SIZE = -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.x = x * size;
		this.width = size;
		this.height = size;
		this.y = y * size;
		this.sheet = sheet;
		pixels = new int[size * size];
		load();
	}

	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}

	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) { // incomplete
		SIZE = -1;
		this.width = width;
		this.height = height;
	}

	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	public Sprite(int[] pixels, int width, int height) {
		if (width == height) SIZE = width;
		else SIZE = -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	
	}

	public void setColor(int color) {
		for (int i = 0; i < width * height; i++)
			pixels[i] = color;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
