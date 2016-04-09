package mccarthy.sean.entity.projectile;

import java.util.Random;

import mccarthy.sean.entity.Entity;
import mccarthy.sean.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double x, y;
	protected double distance;
	protected double speed, range, damage;
	
	protected final Random random = new Random();

	public Projectile(int x, int y, double dir) {
		this.xOrigin = x;
		this.yOrigin = y;
		this.x = x;
		this.y = y;
		angle = dir;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getSpriteSize() {
		return sprite.SIZE;
	}

	protected void move() {

	}

}
