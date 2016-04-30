package engine.backend.systems;

import java.util.Map;
import java.util.Set;

import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;

public interface ISystem{
	
	/**
	 * Updates each system.
	 * @param playing
	 * @param myLevel
	 * @param myEventMap
	 * @param myEntityFactory
	 * @param currentSecond
	 */
	public void update(boolean playing, Level myLevel, Map<String, Set<Integer>> myEventMap, InGameEntityFactory myEntityFactory, double currentSecond);
	
}
