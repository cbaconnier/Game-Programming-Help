package mccarthy.sean.levels.tile;

import mccarthy.sean.graphics.Screen;
import mccarthy.sean.graphics.Sprite;

public class Tile {

	public int x, y;
	public int colorID;
	public Sprite sprite;
	public static int numTiles;
	
	public static Tile grass = new TileGrass(Sprite.grass, 0xFF00FF00);
	public static Tile floor = new TileFloor(Sprite.floor,0xFF914C17);
	public static Tile wall = new TileWall(Sprite.wall, 0xFF654CC4);
	public static Tile voidTile = new TileVoid(Sprite.voidSprite);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
		numTiles++;
	}
	
	public Tile(Sprite sprite, int colorID) {
		this.sprite = sprite;
		this.colorID = colorID;
		numTiles++;
	}

	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}

}
