package mccarthy.sean.entity.mob;

import mccarthy.sean.entity.projectile.BasicProjectile;
import mccarthy.sean.entity.projectile.Projectile;
import mccarthy.sean.graphics.AnimatedSprite;
import mccarthy.sean.graphics.Screen;
import mccarthy.sean.graphics.Sprite;
import mccarthy.sean.graphics.SpriteSheet;
import mccarthy.sean.input.Keyboard;
import mccarthy.sean.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private int moveSpeed = 5;
	private boolean movable = false;
	private boolean walking = false;
	private int fireRate = 0;
	private AnimatedSprite test = new AnimatedSprite(SpriteSheet.test, 64, 64, 3);

	public Player(Keyboard input) {
		this.input = input;
	}

	public Player(int x, int y, Keyboard input) {
		System.out.println("Player created");
		this.x = x;
		this.y = y;
		this.input = input;
		fireRate = BasicProjectile.FIRERATE;
	}

	public void update() {
		test.update();
		if (fireRate > 0) fireRate--;
		int xa = 0, ya = 0;
		if (anim < 7200)
			anim++;
		else
			anim = 0;
		if (input.up) ya--;
		if (input.left) xa--;
		if (input.down) ya++;
		if (input.right) xa++;

		if ((xa != 0 || ya != 0) && movable) {
			move(xa, ya, moveSpeed);
			walking = true;
		} else {
			walking = false;
		}
		clear();
		shootUps();
	}

	private void clear() {
		for (int i = 0; i < room.getProjectiles().size(); i++) {
			Projectile p = room.getProjectiles().get(i);
			if (p.isRemoved()) room.getProjectiles().remove(i);
		}
	}

	int counter;

	private void shootUps() {
		if (Mouse.getButton() == 1 && fireRate == 0) {
			movable = false;
			counter = 0;
			double dx = (Mouse.getX() - 640);
			double dy = (Mouse.getY() - 360);
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = BasicProjectile.FIRERATE;
		} else {
			counter++;
			if (counter >=10) movable = true;
		}
	}

	public void render(Screen screen) {
		if (dir == 0) {
			sprite = Sprite.playeru1;
			if (walking) {
				if (anim % 30 < 10)
					sprite = Sprite.playeru2;
				else if (anim % 30 > 20)
					sprite = Sprite.playeru1;
				else
					sprite = Sprite.playeru3;
			}
		}
		if (dir == 1) {
			sprite = Sprite.playerr1;
			if (walking) {
				if (anim % 90 < 30)
					sprite = Sprite.playerr2;
				else if (anim % 90 > 60)
					sprite = Sprite.playerr1;
				else
					sprite = Sprite.playerr3;
			}
		}
		if (dir == 2) {
			sprite = Sprite.playerd1;
			if (walking) {
				if (anim % 30 < 10)
					sprite = Sprite.playerd2;
				else if (anim % 30 > 20)
					sprite = Sprite.playerd1;
				else
					sprite = Sprite.playerd3;
			}
		}
		if (dir == 3) {
			sprite = Sprite.playerl1;
			if (walking) {
				if (anim % 30 < 10)
					sprite = Sprite.playerl2;
				else if (anim % 30 > 20)
					sprite = Sprite.playerl1;
				else
					sprite = Sprite.playerl3;
			}
		}
		screen.renderPlayer(x - sprite.SIZE, y - sprite.SIZE, sprite);
	}
}
