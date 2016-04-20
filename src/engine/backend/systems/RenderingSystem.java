/**
 * 
 * @author mario_oliver93
 * 
 */

package engine.backend.systems;

import java.util.List;
import java.util.ResourceBundle;

import engine.backend.components.DisplayComponent;
import engine.backend.components.IComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.UpdateEntityEvent;
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

	public void update(Level myLevel, InGameEntityFactory myEntityFactory, double currentSecond, ResourceBundle myComponentTagResources) {
		// TODO Auto-generated method stub
		List<IEntity> entities = myLevel.getEntities();
		//System.out.println("update rendering system " + entities.toString());
		
		for (IEntity myEntity : entities) {
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
				if (eachComponent.getTag().equals(myComponentTagResources.getString("Display"))) {
					imageToDisplay = ((DisplayComponent) eachComponent).getImage();
					show = ((DisplayComponent) eachComponent).shouldBeShown();
				}
				if (eachComponent.getTag().equals(myComponentTagResources.getString("Position"))) {
					x = ((PositionComponent) eachComponent).getX();
					y = ((PositionComponent) eachComponent).getY();
				}
				if (eachComponent.getTag().equals(myComponentTagResources.getString("Size"))) {
					sizex = ((SizeComponent) eachComponent).getWidth();
					sizey = ((SizeComponent) eachComponent).getHeight();
				}
			}
			
			
			sendUpdateEntityEvent(x, y, imageToDisplay, myEntity.getID(), sizex, sizey, show);

			myEntity.setHasBeenModified(false);

		}
	}
	
	public void sendUpdateEntityEvent(double x, double y, String image, int id, double sizex, double sizey, boolean show){
		UpdateEntityEvent event = new UpdateEntityEvent(x, y, image, id, sizex, sizey, show);
		this.setChanged();
		notifyObservers(event);
	}
	
	//
	// //@Override
	// public void execute(List<Level> list) {
	// // TODO Auto-generated method stub
	// for(Level each: list){
	// System.out.println(each.toString());
	// //frontEndController.createCharacterImage(x, y, imageToDisplay, sizex,
	// sizey);
	//
	// }
	// }

}
