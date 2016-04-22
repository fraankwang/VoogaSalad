package engine.backend.game_object;

import engine.backend.entities.Entity;

public class Wave {

	public Wave(int numOfEntities) {
		for(int i = 0; i < numOfEntities; i++){
			Entity entity = new Entity();
		}
	}

}
