package engine.backend.systems.Events;

import engine.frontend.shop.CurrentView;

public class EntityClickedEvent extends EntityEvent{
	
	private CurrentView currentView;
	
	public EntityClickedEvent(int id, CurrentView cv){
		super.addEntityID(id);
		this.currentView = cv;
	}
	
	public CurrentView getCurrentView(){
		return currentView;
	}
}
