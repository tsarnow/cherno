package game;
import graphics.Screen;
import input.Keyboard;
import input.Mouse;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import level.Level;
import level.tile.TileCoord;
import entity.mob.Player;

/**
 * http://www.youtube.com/watch?v=2SUtcxJYN6k
 * @author tobi
 *
 */
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private static int width = 300;
	private static int height = width / 16 * 9;
	private static int scale = 3;
	
	private Thread thread;
	private JFrame frame;
	private Keyboard keyboard;
	private Level level;
	private Player player;

	private boolean running = false;

	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		keyboard = new Keyboard();
		TileCoord playerSpawn = new TileCoord(19,  62);
//		level = new SpawnLevel("/textures/level1.png");
		level = Level.spwan;
		player = new Player(playerSpawn.x(), playerSpawn.y(), keyboard);
		player.init(level);
		
		Mouse mouse = new Mouse();
		addKeyListener(keyboard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(updates + " ups, " + frames + " fps");
				frames=0;
				updates=0;
				try {
					Thread.sleep(10);
//					System.out.println("sleep 20");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// pause mode (very buggy)
			if (keyboard.pause) {
				boolean trigger = true;
				while(trigger) {
					try {
						Thread.sleep(100);
						keyboard.update();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (keyboard.pause) {
						trigger = false;
					}
				}
			}
		}
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();							// delete old grafic content
		int xScroll = player.x - (screen.width >> 1);
		int yScroll = player.y - (screen.height >> 1);
		level.render(xScroll, yScroll, screen);	// renders the level to the screen
		player.render(screen);
		
		// copy screen to this component
		for (int i=0; i<pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	private void update() {
		keyboard.update();
		player.update();
		level.update();
	}
	
	public static int getWindowWidth() {
		return width * scale;
	}
	
	public static int getWindowHeight() {
		return height * scale;
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Rain");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
}
