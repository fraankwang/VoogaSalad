/**
 * 
 * @author mario_oliver93
 * 
 */
package engine.backend.entities;
import exception.DrumpfTowerException;
import engine.backend.components.Component;
import engine.backend.components.DisplayComponent;
import engine.backend.game_object.GameWorld;
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
				myComponent = (Component) Class.forName("engine.backend.components." + componentType).newInstance();
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
