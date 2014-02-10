package entity;

import java.util.ArrayList;
import java.util.List;

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
				Particle p;
				
				// nextGaussian is not working really well with new random numbers...
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
	
	public enum Type {
		MOB, 
		PARTICLE;
	}
}
