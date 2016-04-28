package engine.backend.systems.Events;

import engine.frontend.shop.CurrentView;

public class EntityClickedEvent extends EntityEvent{
	
	private int entityID;
	private CurrentView currentView;
	
	public EntityClickedEvent(int id, CurrentView cv){
		this.entityID = id;
		this.currentView = cv;
	}
	
	public int getClickedEntityID(){
		return entityID;
	}
	
	public CurrentView getCurrentView(){
		return currentView;
	}
}
