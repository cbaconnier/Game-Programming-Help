package mccarthy.sean.levels;

import java.util.ArrayList;
import java.util.List;

import mccarthy.sean.Game;
import mccarthy.sean.entity.Entity;
import mccarthy.sean.entity.Particle.Particle;
import mccarthy.sean.entity.projectile.Projectile;
import mccarthy.sean.graphics.Screen;
import mccarthy.sean.levels.tile.Tile;

public class Room {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();

	public static Room testRoom = new TestRoom("/levels/testroom.png");

	public Room(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateRoom();
	}

	public Room(String path) {
		loadRoom(path);
		generateRoom();
	}

	protected void generateRoom() {

	}

	protected void loadRoom(String path) {

	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		remove();
	}

	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * size + xOffset) / 64;
			double yt = (y - c / 2 * size + yOffset) / 64;
			if (getTile((int) xt, (int) yt).solid()) solid = true;
		}
		return solid;
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> Game.TILE_SIZE_BIT;
		int x1 = (xScroll + screen.width + Game.TILE_SIZE) >> Game.TILE_SIZE_BIT;
		int y0 = yScroll >> Game.TILE_SIZE_BIT;
		int y1 = (yScroll + screen.width + Game.TILE_SIZE) >> Game.TILE_SIZE_BIT;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else {
			entities.add(e);
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.grass.colorID) return Tile.grass;
		if (tiles[x + y * width] == Tile.floor.colorID) return Tile.floor;
		if (tiles[x + y * width] == Tile.wall.colorID) return Tile.wall;
		return Tile.voidTile;
	}

}
