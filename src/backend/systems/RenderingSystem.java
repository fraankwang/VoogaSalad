/**
 * 
 * @author mario_oliver93
 * 
 */


package backend.systems;

import java.util.List;

import backend.FrontEndAccessController;
import backend.Level;
import backend.game_object.components.DisplayComponent;
import backend.game_object.components.IComponent;
import backend.game_object.components.PositionComponent;
import backend.game_object.entities.Entity;
import backend.game_object.entities.IEntity;
<<<<<<< HEAD

/**
 * 
 * @author mario_oliver93
 *
 */
public class RenderingSystem extends Systemm implements ISystem {
=======

import java.util.*;

public class RenderingSystem extends Systemm {
>>>>>>> engine_backend_systems

//	private IFrontEndAccess frontEndController;
	private FrontEndAccessController frontEndController;
	
	public RenderingSystem(FrontEndAccessController frontEndController) {
		this.frontEndController = frontEndController;
	}

	@Override
	public void update(List<IEntity> entities) {
		// TODO Auto-generated method stub
		for(IEntity myEntity: entities){
			System.out.println(myEntity.toString());
			String imageToDisplay = "";
			double x = Integer.MIN_VALUE;
			double y = Integer.MIN_VALUE;
<<<<<<< HEAD
			for(IComponent eachComponent: myEntity.getComponents()){
				if(eachComponent.getTag().equals("Display")){
=======
			double sizex = 80;
			double sizey = 100;
			for(IComponent eachComponent: myEntity.getComponents()){
				if(eachComponent.getTag().equals(getComponentTagResources().getString("Display"))){
>>>>>>> engine_backend_systems
					imageToDisplay = ((DisplayComponent) eachComponent).getImage();
				}
				if(eachComponent.getTag().equals(getComponentTagResources().getString("Position"))){
					x = ((PositionComponent) eachComponent).getX();
					y = ((PositionComponent) eachComponent).getY();
				}
				//if(eachComponent == "Size") -->  getSize()
			}
			
<<<<<<< HEAD
			frontEndController.createCharacter(x, y, imageToDisplay);
		}
	}


	//@Override
	public void execute(List<Level> list) {
		// TODO Auto-generated method stub
		for(Level each: list){
			System.out.println(each.toString());
=======
			frontEndController.createCharacterImage(x, y, imageToDisplay, sizex, sizey);
>>>>>>> engine_backend_systems
		}
	}


}
