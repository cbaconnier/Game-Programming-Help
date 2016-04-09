package mccarthy.sean.entity.Particle;

import mccarthy.sean.entity.Entity;
import mccarthy.sean.graphics.Screen;
import mccarthy.sean.graphics.Sprite;

public class Particle extends Entity {

	private Sprite sprite;

	private int life;
	private int time = 0;

	protected double xx, yy, zz;
	protected double xa, ya, za;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) - 5);
		sprite = Sprite.normalParticle;

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = 20 + random.nextFloat() * 8 - 4;
		;
	}

	public void update() {
		time++;
		if (time >= 10000) time = 0;
		if (time > life) remove();
		za -= 0.1;
		if (zz < 0) {
			zz = 0;
			za *= -0.6;
			xa *= 0.4;
			ya *= 0.4;
		}
		move(xx + xa, (yy + ya) + (zz + za));
	}

	private void move(double x, double y) {
		if (collision(x, y)) {
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}

	public boolean collision(double x, double y) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 64) / 64;
			double yt = (y - c / 2 * 64) / 64;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (room.getTile(ix, iy).solid()) solid = true;
		}
		return solid;

	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy - (int) zz, sprite, true);
	}

}
