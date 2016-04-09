package mccarthy.sean.entity.spawner;

import mccarthy.sean.entity.Particle.Particle;
import mccarthy.sean.levels.Room;

public class ParticleSpawner extends Spawner {

	private int life;

	public ParticleSpawner(int x, int y, int life, int amount, Room room) {
		super(x, y, Type.PARTICLE, amount, room);
		this.life = life;
		for (int i = 0; i < amount; i++) {
			room.add(new Particle(x, y, life));
		}
	}
}
