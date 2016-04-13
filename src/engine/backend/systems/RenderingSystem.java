/**
 * 
 * @author mario_oliver93
 * 
 */


package engine.backend.systems;

import java.util.List;

import engine.backend.components.DisplayComponent;
import engine.backend.components.IComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.entities.IEntity;
import engine.backend.game_object.Level;
import engine.controller.EngineController;



/**
 * 
 * @author mario_oliver93
 *
 */

public class RenderingSystem extends Systemm {


	private EngineController engineController;
	
	public RenderingSystem(EngineController eController) {
		this.engineController = eController;
	}

	@Override
	public void update(List<IEntity> entities) {
		// TODO Auto-generated method stub
		for(IEntity myEntity : entities){
//			System.out.println(myEntity.toString());
			String imageToDisplay = "";
			double x = Integer.MIN_VALUE;
			double y = Integer.MIN_VALUE;
			double sizex = 350;
			double sizey = 200;
			for(IComponent eachComponent: myEntity.getComponents()){
				if(eachComponent.getTag().equals(getComponentTagResources().getString("Display"))){
					imageToDisplay = ((DisplayComponent) eachComponent).getImage();
				}
				if(eachComponent.getTag().equals(getComponentTagResources().getString("Position"))){
					x = ((PositionComponent) eachComponent).getX();
					y = ((PositionComponent) eachComponent).getY();
				}
				if(eachComponent.getTag().equals(getComponentTagResources().getString("Size"))){
					sizex = ((SizeComponent) eachComponent).getWidth();
					sizex = ((SizeComponent) eachComponent).getHeight();
				}
			}
			
			x = 300;
			y = 300;
			
			engineController.updateEntity(x, y, imageToDisplay, myEntity.getID(), sizex, sizey);
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
