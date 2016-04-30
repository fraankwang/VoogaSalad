package engine.backend.systems.Events;


public class KeyPressedEntityEvent extends EntityEvent{
	private String keyPressed;
	
	public KeyPressedEntityEvent(int entityID, String keyPressed) {
		super.addEntityID(entityID);
		this.keyPressed = keyPressed;
	}
	
	@Override
	public String getEventID() {
		return super.getEventID();
	}

	public String getKeyPressed() {
		return keyPressed;
	}

}
