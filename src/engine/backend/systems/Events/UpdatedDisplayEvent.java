package engine.backend.systems.Events;

public class UpdatedDisplayEvent extends EntityEvent {

	public UpdatedDisplayEvent(int entityID) {
		super.addEntityID(entityID);
	}
	
	public UpdatedDisplayEvent(){
		System.out.println("hello");
	}

	@Override
	public String toString() {
		return "I am a display event";
	}
	
	

}
