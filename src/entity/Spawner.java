package entity;

import java.util.ArrayList;
import java.util.List;

import level.Level;

import entity.particle.Particle;
import graphics.Screen;

public class Spawner extends Entity {

	private List<Entity> entities = new ArrayList<Entity>();

//	private Type type;
	
	public Spawner(int x, int y, Type type, int amount) {
		this.x = x;
		this.y = y;
//		this.type = type;
		Particle last = null;
		for (int i=0; i<amount; i++) {
			if (type == Type.PARTICLE) {
				
				// nextGaussian is not working really well with new random numbers...
				Particle p;
				do {
					p = new Particle(x, y, 50);
				} while (last != null && p.sameRandom(last));
				
				last = p;
				entities.add(last);
			}
		}
	}
	
	@Override
	public void update() {
		for (int i=0; i<entities.size(); i++) {
			if (entities.get(i).isRemoved()) {
				entities.remove(i);
			} else {				
				entities.get(i).update();
			}
		}
	}
	
	@Override
	public void render(Screen screen) {
		for (Entity e : entities) {
			e.render(screen);
		}
	}
	
	@Override
	public void init(Level level) {
		super.init(level);
		for (Entity e : entities) {
			e.init(level);
		}
	}
	
	public enum Type {
		MOB, 
		PARTICLE;
	}
}
