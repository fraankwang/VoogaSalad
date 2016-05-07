package engine.backend.systems;

import engine.backend.game_object.Level;

public interface ISystem {

	/**
	 * Updates each system.
	 * 
	 * @param setUp
	 */
	public void update(SystemSetUp setUp);

}
