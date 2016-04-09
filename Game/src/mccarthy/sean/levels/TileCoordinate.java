package mccarthy.sean.levels;

import mccarthy.sean.Game;

public class TileCoordinate {

	private int x, y;

	public TileCoordinate(int x, int y) {
		this.x = x * Game.TILE_SIZE;
		this.y = y * Game.TILE_SIZE;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public int[] xy() {
		int[] r = new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}
}
