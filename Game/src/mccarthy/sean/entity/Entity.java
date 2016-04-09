package mccarthy.sean.entity;

import java.util.Random;

import mccarthy.sean.graphics.Screen;
import mccarthy.sean.levels.Room;

public abstract class Entity {

	public int x, y;
	private boolean removed = false;
	protected Room room;
	protected final Random random = new Random();

	public void update() {

	}

	public void render(Screen screen) {

	}

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Room room) {
		this.room = room;
	}

}
