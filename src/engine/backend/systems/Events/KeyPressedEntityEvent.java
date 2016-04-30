package engine.backend.systems.Events;

import java.util.ArrayList;
import java.util.List;

public class KeyPressedEntityEvent extends EntityEvent{
	
	public KeyPressedEntityEvent(String entityID, String keyPressed) {
		setEventID(entityID, keyPressed);
	}

	private void setEventID(String entityID, String keyPressed) {
		List<String> identifiers = new ArrayList<String>();
		identifiers.add(entityID); 
		identifiers.add(keyPressed);
		super.setEventID(identifiers);
	}
	
	@Override
	public String getEventID() {
		return super.getEventID();
	}

}
