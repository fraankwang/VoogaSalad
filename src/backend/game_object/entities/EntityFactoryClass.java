/**
 * 
 * @author mario_oliver93
 * 
 */
package backend.game_object.entities;

import backend.GameObject;
import backend.game_object.components.Component;
import exception.DrumpfTowerException;

public class EntityFactoryClass {

	public EntityFactoryClass() {
		// TODO Auto-generated constructor stub
	}

	public Entity makeEntity(GameObject trumpGame, String[] componentsWanted) throws DrumpfTowerException{
		Entity trump = new Entity(trumpGame.getGameStats().nextAvailableID());
		// wants to add a display component to trump
		for (String componentType : componentsWanted) {
			System.out.println(componentType);
			Component myComponent = null;
			try {
				myComponent = (Component) Class.forName("backend.game_object.components." + componentType + "Component").newInstance();
				// I hate exceptions
			} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
				throw new DrumpfTowerException("Component does not exist");
			}
			trump.addComponent(myComponent);
		}
		return trump;
	}

}
