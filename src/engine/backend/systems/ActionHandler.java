//This entire file is part of my masterpiece.
//Christine Zhou
package engine.backend.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import engine.backend.entities.IEntity;
import engine.backend.game_object.GameStatistics;
import engine.backend.rules.IAction;
public class ActionHandler {
	private Map<Integer, IEntity> entities;
	private GameStatistics currentGameStatistics;
	
	public ActionHandler(Map<Integer, IEntity> entities, GameStatistics currentGameStatistics) {
		this.entities = entities;
		this.currentGameStatistics = currentGameStatistics;
	}
	
	/**
	 * Applies action by checking if action is applicable for the action type. Otherwise
	 * sends action to successor. 
	 * @param entity
	 * @param actions
	 */
	private void applyActions(IEntity entity, Collection<IAction> actions) {
		actions.forEach(a -> a.applyAction(currentGameStatistics, entity, a));
	}

	/**
	 * Applies actions for a collection of entity IDs.
	 * @param entityIDs
	 * @param actions
	 */
	public void applyActions(Collection<Integer> entityIDs, Collection<IAction> actions) {
		Collection<IEntity> myEntities = new ArrayList<IEntity>();
		entityIDs.forEach(i -> myEntities.add(entities.get(i)));
		myEntities.forEach(entity -> applyActions(entity, actions));
	}

}
