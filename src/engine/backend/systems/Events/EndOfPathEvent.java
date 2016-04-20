package engine.backend.systems.Events;

import engine.backend.entities.IEntity;
import engine.backend.game_object.Level;

public class EndOfPathEvent extends LevelEvent{
	
	public EndOfPathEvent(Level myCurrentLevel){
		super.setmyCurrentLevel(myCurrentLevel);;
	}
	
}
