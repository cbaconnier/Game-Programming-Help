package mccarthy.sean.levels.tile;

import mccarthy.sean.Game;
import mccarthy.sean.graphics.Screen;
import mccarthy.sean.graphics.Sprite;

public class TileVoid extends Tile {

	public TileVoid(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << Game.TILE_SIZE_BIT, y << Game.TILE_SIZE_BIT, this);
	}

}
