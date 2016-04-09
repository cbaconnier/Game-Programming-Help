package mccarthy.sean;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import mccarthy.sean.entity.mob.Player;
import mccarthy.sean.graphics.Screen;
import mccarthy.sean.input.Keyboard;
import mccarthy.sean.input.Mouse;
import mccarthy.sean.levels.Room;
import mccarthy.sean.levels.TileCoordinate;

public class Game extends Canvas implements Runnable {

	public int width = 1280;
	public int height = 720;
	public static final int TILE_SIZE = 64;
	public static final int TILE_SIZE_BIT = (int) ((Math.log10(TILE_SIZE)) / (Math.log10(2)));
	public Screen screen;

	private static boolean running = false;
	private static String title = "Game";
	private Thread thread;
	private Keyboard key;
	private Mouse mouse;
	private JFrame frame;
	private Player player;
	private Room room;
	private TileCoordinate spawnpt;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private static final long serialVersionUID = 1L;

	public Game() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		mouse = new Mouse();
		room = Room.testRoom;
		spawnpt = new TileCoordinate(25, 49);
		player = new Player(spawnpt.x(), spawnpt.y(), key);
		player.init(room);

		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		System.out.println("Game created");
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
		System.out.println("Game started");
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void update() {
		key.update();
		player.update();
		room.update();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		room.render(xScroll, yScroll, screen);
		player.render(screen);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public void run() {
		
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1.0) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public static void main(String[] args) {
		Game game = new Game();
		System.out.println("Created");
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}
