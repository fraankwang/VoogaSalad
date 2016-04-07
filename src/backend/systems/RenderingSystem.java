package backend.systems;

import java.util.List;

import backend.FrontEndAccessController;
import backend.IFrontEndAccess;
import backend.Level;
import backend.game_object.components.Component;
import backend.game_object.components.DisplayComponent;
import backend.game_object.components.PositionComponent;
import backend.game_object.entities.Entity;
import java.util.*;

/**
 * 
 * @author mario_oliver93
 *
 */
public class RenderingSystem extends Systemm {

//	private IFrontEndAccess frontEndController;
	private FrontEndAccessController frontEndController;
	
	public RenderingSystem(FrontEndAccessController frontEndController) {
		this.frontEndController = frontEndController;
	}

	@Override
	public void update(List<Entity> entities) {
		// TODO Auto-generated method stub
		for(Entity myEntity: entities){
			System.out.println(myEntity.toString());
			String imageToDisplay = "";
			double x = Integer.MIN_VALUE;
			double y = Integer.MIN_VALUE;
			for(Component eachComponent: myEntity.getComponents()){
				if(eachComponent.getTag().equals("Display")){
					imageToDisplay = ((DisplayComponent) eachComponent).getImage();
				}
				if(eachComponent.getTag().equals("Position")){
					x = ((PositionComponent) eachComponent).getX();
					y = ((PositionComponent) eachComponent).getY();
				}
			}
			
			frontEndController.createCharacter(x, y, imageToDisplay);
		}
	}


	@Override
	public void execute(List<Level> list) {
		// TODO Auto-generated method stub
		for(Level each: list){
			System.out.println(each.toString());
		}
	}

}
