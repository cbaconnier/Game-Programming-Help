package mccarthy.sean.entity.spawner;

import mccarthy.sean.entity.Entity;
import mccarthy.sean.levels.Room;

public class Spawner extends Entity{
	
	public enum Type {
		MOB, PARTICLE;
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Room room){
		init(room);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
