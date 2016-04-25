/**
 * 
 * @author mario_oliver93
 * 
 */

package engine.backend.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import engine.backend.components.DisplayComponent;
import engine.backend.components.IComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.IEvent;
import engine.backend.systems.Events.UpdateEntityEvent;
import engine.backend.utilities.ComponentTagResources;
import engine.controller.EngineController;

/**
 * 
 * @author mario_oliver93
 *
 */

public class RenderingSystem extends GameSystem {

	private EngineController engineController;

	public RenderingSystem(EngineController eController) {
		this.engineController = eController;
	}

	public void update(Level myLevel, Map<String, Set<Integer>> myEventMap, InGameEntityFactory myEntityFactory, double currentSecond) {
		// TODO Auto-generated method stub

		Collection<IEntity> entities = myLevel.getEntities().values();
		Collection<IEntity> entitiesToRemove = new ArrayList<IEntity>();
		for(IEntity myEntity : entities){
			String imageToDisplay = "";
			double x = Integer.MIN_VALUE;
			double y = Integer.MIN_VALUE;
			double sizex = 200;
			double sizey = 200;
			boolean show = true;
			if (!myEntity.hasBeenModified()) {
				continue;
			}
			for (IComponent eachComponent : myEntity.getComponents()) {
				if (eachComponent.getTag().equals(ComponentTagResources.displayComponentTag)) {
					imageToDisplay = ((DisplayComponent) eachComponent).getImage();
					show = ((DisplayComponent) eachComponent).shouldBeShown();
				}
				if (eachComponent.getTag().equals(ComponentTagResources.positionComponentTag)) {
					x = ((PositionComponent) eachComponent).getX();
					y = ((PositionComponent) eachComponent).getY();
				}
				if (eachComponent.getTag().equals(ComponentTagResources.sizeComponentTag)) {
					sizex = ((SizeComponent) eachComponent).getWidth();
					sizey = ((SizeComponent) eachComponent).getHeight();
				}
			}
			
			//System.out.println("Name:  " + myEntity.getName() + myEntity.getID());
			sendUpdateEntityEvent(x, y, imageToDisplay, myEntity.getID(), sizex, sizey, show);
			
			if(!show){
				entitiesToRemove.add(myEntity);
			}
			
			myEntity.setHasBeenModified(false);
		
		}
		
		entities.removeAll(entitiesToRemove);
	//	System.out.println(entities.size());
	}
	
	public void sendUpdateEntityEvent(double x, double y, String image, int id, double sizex, double sizey, boolean show){
		UpdateEntityEvent event = new UpdateEntityEvent(x, y, image, id, sizex, sizey, show);
		this.setChanged();
		notifyObservers(event);
	}

}
