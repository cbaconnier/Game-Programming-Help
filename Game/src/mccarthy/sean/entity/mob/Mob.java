package mccarthy.sean.entity.mob;

import mccarthy.sean.entity.Entity;
import mccarthy.sean.entity.projectile.BasicProjectile;
import mccarthy.sean.entity.projectile.Projectile;
import mccarthy.sean.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 2;
	protected boolean moving = false;

	public void move(int xa, int ya, int moveSpeed) {
		if (xa != 0 && ya != 0) {
			move(xa, 0, moveSpeed);
			move(0, ya, moveSpeed);
			return;
		}

		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;

		if (!collision(xa, ya, moveSpeed)) {
			x += xa * moveSpeed;
			y += ya * moveSpeed;
		}
	}

	public void update() {

	}

	protected void shoot(int x, int y, double dir) {
		Projectile p = new BasicProjectile(x, y, dir);
		room.add(p);
	}

	public boolean collision(int xf, int yf, int ms) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xf * ms) + c % 2 * 66 - 65) / 64;
			int yt = ((y + yf * ms) + c / 2 * 66 - 65) / 64;
			if (room.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}

}
