package backend.systems;

import java.util.ArrayList;
import java.util.List;

import backend.game_object.entities.Entity;

public class MobilizeSystem extends Systems {

	private List<String> rotationComponents = new ArrayList<String>();
	private List<String> movementComponents = new ArrayList<String>();
	
	public MobilizeSystem() {
		// TODO Auto-generated constructor stub
		rotationComponents.add("Direction");
		
	}

	@Override
	public void update(List<Entity> entities) {
		
		//do rotations first

		List<Entity> rotatableEntities = filter(entities, )
		
		//do movements next
		
	}
	
	public void updateRotations(List<Entity> entities){
		
	}
	
	
	

}
