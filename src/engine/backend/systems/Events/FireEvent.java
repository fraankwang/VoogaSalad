package engine.backend.systems.Events;

import engine.backend.entities.IEntity;

public class FireEvent extends Event{
	
	private IEntity firingEntity;
	
	public FireEvent(IEntity firingEntity){
		this.firingEntity = firingEntity;
	}
	
	public IEntity getFiringEntity(){
		return firingEntity;
	}
	
}
