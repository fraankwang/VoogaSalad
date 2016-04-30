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
 * @author Ragahv Kedia, mario_oliver93
 *
 */

public class RenderingSystem extends GameSystem {

	public void update(boolean playing, Level myLevel, Map<String, Set<Integer>> myEventMap, InGameEntityFactory myEntityFactory, double currentSecond) {

		Collection<IEntity> entities = myLevel.getEntities().values();
		Collection<IEntity> entitiesToRemove = new ArrayList<IEntity>();
		for(IEntity myEntity : entities){
			String imageToDisplay = "";
			double x = Integer.MIN_VALUE;
			double y = Integer.MIN_VALUE;
			double sizex = Integer.MIN_VALUE;
			double sizey = Integer.MIN_VALUE;
			boolean show = true;
			boolean delete = false;
			
			System.out.println(myEntity.getName());
			
			for (IComponent eachComponent : myEntity.getComponents()) {
				if (eachComponent.getTag().equals(ComponentTagResources.displayComponentTag)) {
					imageToDisplay = ((DisplayComponent) eachComponent).getImage();
					System.out.println(imageToDisplay);
					show = ((DisplayComponent) eachComponent).shouldBeShown();
					delete = ((DisplayComponent) eachComponent).getDelete();
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
			
			sendUpdateEntityEvent(x, y, imageToDisplay, myEntity.getID(), sizex, sizey, show);
			myEntity.broadcastEntity();
			if(delete){
				entitiesToRemove.add(myEntity);
			}
			
			myEntity.setHasBeenModified(false);
		
		}
		
		myLevel.removeEntites(entitiesToRemove);
		
	}
	
	public void sendUpdateEntityEvent(double x, double y, String image, int id, double sizex, double sizey, boolean show){
		UpdateEntityEvent event = new UpdateEntityEvent(x, y, image, id, sizex, sizey, show);
		this.setChanged();
		System.out.println("image5: " + event.getImage());
		notifyObservers(event);
	}

}
