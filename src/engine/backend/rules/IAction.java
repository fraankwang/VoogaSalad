//This entire file is part of my masterpiece.
//Christine Zhou
package engine.backend.rules;

import engine.backend.entities.IEntity;
import engine.backend.game_object.GameStatistics;

public interface IAction {
	
	public void setSuccessor(IAction sucessor);
	public void applyAction(GameStatistics currentGameStatistics, IEntity entity, IAction action);
}
