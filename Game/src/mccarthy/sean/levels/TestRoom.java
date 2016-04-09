package mccarthy.sean.levels;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestRoom extends Room {

	public TestRoom(String path) {
		super(path);
	}

	protected void loadRoom(String path) {
		try {
			BufferedImage image = ImageIO.read(TestRoom.class.getResource(path));
			int w = this.width = image.getWidth();
			int h = this.height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error!  Level file unable to load");
		}
	}

	protected void generateRoom() {

	}

}
