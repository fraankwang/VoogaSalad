package engine.backend.systems;

import engine.backend.game_object.Level;

public interface ISystem {

	/**
	 * Updates each system.
	 * @param myLevel
	 * @param currentSecond
	 * @param setUp TODO
	 * @param playing
	 */
	public void update(Level myLevel, double currentSecond,
			SystemSetUp setUp);

}
