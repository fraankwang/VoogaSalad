/**
 * 
 * @author mario_oliver93
 * 
 */
package backend.game_object.entities;

import backend.GameWorld;
import backend.game_object.components.Component;
import backend.game_object.components.DisplayComponent;
import exception.DrumpfTowerException;

public class EntityFactoryClass {

	public EntityFactoryClass() {
		// TODO Auto-generated constructor stub
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			trump.addComponent(myComponent);
		}
		return trump;
	}

}
