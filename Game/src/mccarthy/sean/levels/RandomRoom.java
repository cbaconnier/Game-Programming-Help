package mccarthy.sean.levels;

import java.util.Random;

import mccarthy.sean.levels.tile.Tile;

public class RandomRoom extends Room {

	private static  final Random random = new Random();

	public RandomRoom(int width, int height) {
		super(width, height);
	}

	protected void generateRoom() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tilesInt[x + y * width] = random.nextInt(Tile.numTiles);
			}
		}
	}

}
