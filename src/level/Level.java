package level;

import java.util.ArrayList;
import java.util.List;

import level.tile.Tile;
import entity.Entity;
import entity.mob.Player;
import entity.particle.Particle;
import entity.projecttile.Projectile;
import graphics.Screen;

public abstract class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Player> players = new ArrayList<Player>();
	
	public static Level spwan = new SpawnLevel("/textures/level/spawn.png");
	
	public Level(int width, int height) {
		this.width = width; 
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
	
	abstract protected void generateLevel();
	
	abstract protected void loadLevel(String path);
	
	public void update() {
		for (int i=0; i<entities.size(); i++) {
			entities.get(i).update();
		}
	}
	
	private void time() {
		
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getClientPlayer() {
		return players.get(0);
	}
	
	/** 
	 * Renders the tiles according to tiles stored in Level implementation. 
	 * @param xScroll where the player is on x
	 * @param yScroll where the player is on y
	 * @param screen
	 */
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);			// sets the offset for the player
		int x0 = xScroll >> 4;						// most visible left
		int x1 = (xScroll + screen.width + 16) >> 4;		// most visible right
		int y0 = yScroll >> 4;						// most visible top
		int y1 = (yScroll + screen.height + 16) >> 4; 	// most visible bottom

		// go through all visible tiles in the screen
		for (int y=y0; y<y1; y++) {
			for (int x=x0; x<x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for (int i=0; i<entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}
	
	public void addEntity(Entity entity) {
		entity.init(this);
		if (entity instanceof Particle) {
			entities.add(entity);
		} else if (entity instanceof Projectile) {
			entities.add(entity);
		} else if (entity instanceof Player) {
			players.add((Player) entity);
		} else {
			entities.add(entity);
		}
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public List<Entity> getPlayers(Entity e, int radius) {
		return getEntities(players, e, radius);
	}

	public List<Entity> getEntities(Entity e, int radius) {
		return getEntities(entities, e, radius);
	}
	
	private <T extends Entity> List<Entity> getEntities(List<T> entities, Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = e.getX();
		int ey = e.getY();
		for (int i=0; i<entities.size(); i++) {
			Entity entity = entities.get(0);
			int x = entity.getX();
			int y = entity.getY();
			
			int dx = (x - ex) * (x - ex);
			int dy = (y - ey) * (y - ey);
			
			if ((radius * radius) >= (dx + dy)) result.add(entity);
		}
		
		return result;
	}
	
	public boolean entityCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;

		for (int c=0; c<4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x+y*width] == Tile.col_spwan_floor) return Tile.spawn_floor;
		if (tiles[x+y*width] == Tile.col_spwan_grass) return Tile.spawn_grass;
		if (tiles[x+y*width] == Tile.col_spwan_wall1) return Tile.spawn_wall1;
		if (tiles[x+y*width] == Tile.col_spwan_wall2) return Tile.spawn_wall2;
		return Tile.voidTile;
 	}
}
