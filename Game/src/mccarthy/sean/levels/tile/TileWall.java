package mccarthy.sean.levels.tile;

import mccarthy.sean.Game;
import mccarthy.sean.graphics.Screen;
import mccarthy.sean.graphics.Sprite;

public class TileWall extends Tile {

	public TileWall(Sprite sprite, int colorID) {
		super(sprite, colorID);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << Game.TILE_SIZE_BIT, y << Game.TILE_SIZE_BIT, this);
	}

	public boolean solid() {
		return true;
	}	
}
