package mccarthy.sean.entity.projectile;

import mccarthy.sean.entity.spawner.ParticleSpawner;
import mccarthy.sean.graphics.Screen;
import mccarthy.sean.graphics.Sprite;

public class BasicProjectile extends Projectile {

	public static final int FIRERATE = 5;

	public BasicProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 400 + random.nextInt(50);
		damage = 20;
		speed = 9;
		sprite = Sprite.basicProjectile;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		if (room.tileCollision((int) (x + nx), (int) (y + ny), sprite.SIZE, 0 , 0)) {
			room.add(new ParticleSpawner((int) x, (int) y, 130, 5, room));
			remove();
		}
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) remove();
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.pow((xOrigin - x), 2) + Math.pow((yOrigin - y), 2));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x, (int) y, this);
	}

}
