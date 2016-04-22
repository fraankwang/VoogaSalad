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
import engine.controller.EngineController;



/**
 * 
 * @author mario_oliver93
 *
 */

public class RenderingSystem extends GameSystem{


	private EngineController engineController;
	
	public RenderingSystem(EngineController eController) {
		this.engineController = eController;
	}

	@Override
	public void update(Level myLevel, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources) {
		// TODO Auto-generated method stub
		List<IEntity> entities = myLevel.getEntities();
		for(IEntity myEntity : entities){
			String imageToDisplay = "";
			double x = Integer.MIN_VALUE;
			double y = Integer.MIN_VALUE;
			double sizex = 200;
			double sizey = 200;
			boolean show = true;
			if(!myEntity.hasBeenModified()){
				continue;
			}
			for(IComponent eachComponent: myEntity.getComponents()){
				if(eachComponent.getTag().equals(myComponentTagResources.getString("Display"))){
					imageToDisplay = ((DisplayComponent) eachComponent).getImage();
					show = ((DisplayComponent) eachComponent).shouldBeShown();
				}
				if(eachComponent.getTag().equals(myComponentTagResources.getString("Position"))){
					x = ((PositionComponent) eachComponent).getX();
					y = ((PositionComponent) eachComponent).getY();
				}
				if(eachComponent.getTag().equals(myComponentTagResources.getString("Size"))){
					sizex = ((SizeComponent) eachComponent).getWidth();
					sizey = ((SizeComponent) eachComponent).getHeight();
				}
			}
			
			engineController.updateEntity(x, y, imageToDisplay, myEntity.getID(), sizex, sizey, show);
			
			
			myEntity.setHasBeenModified(false);
			
		}
	}

//
//	//@Override
//	public void execute(List<Level> list) {
//		// TODO Auto-generated method stub
//		for(Level each: list){
//			System.out.println(each.toString());
//			//frontEndController.createCharacterImage(x, y, imageToDisplay, sizex, sizey);
//
//		}
//	}


}
