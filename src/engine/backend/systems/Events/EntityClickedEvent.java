package engine.backend.systems.Events;

public class EntityClickedEvent extends EntityEvent{
	
	private int entityID;
	
	public EntityClickedEvent(int id){
		this.entityID = id;
	}
	
	public int getClickedEntityID(){
		return entityID;
	}
	
}
