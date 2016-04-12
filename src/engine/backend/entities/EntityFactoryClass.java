/**
 * 
 * @author mario_oliver93
 * 
 */
package engine.backend.entities;

import engine.backend.GameWorld;
import engine.backend.components.Component;
import engine.backend.components.DisplayComponent;
import exception.DrumpfTowerException;

public class EntityFactoryClass {

	public EntityFactoryClass() {

	}
	
	public Entity makeEntity(GameWorld trumpGame, String[] componentsWanted) {
		Entity trump = new Entity(trumpGame.getGameStats().nextAvailableID());
		// wants to add a display component to trump
		for (String componentType : componentsWanted) {
			Component myComponent = null;
			try {
				myComponent = (Component) Class.forName("backend.game_object.components." + componentType + "Component").newInstance();
				// I hate exceptions
			} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
				try {
					throw new DrumpfTowerException("Component does not exist");
				} catch (DrumpfTowerException e1) {
					e1.printStackTrace();
				}
			}
			trump.addComponent(myComponent);
		}
		return trump;
	}

}
