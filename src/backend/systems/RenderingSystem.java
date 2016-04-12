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
<<<<<<< HEAD
=======
import backend.game_object.components.SizeComponent;
import backend.game_object.entities.Entity;
>>>>>>> 75596e71e7d6e24926d79378b45fe8a0dafd53e4
import backend.game_object.entities.IEntity;


/**
 * 
 * @author mario_oliver93
 *
 */

public class RenderingSystem extends Systemm {


//	private IFrontEndAccess frontEndController;
	private FrontEndAccessController displayController;
	
	public RenderingSystem(FrontEndAccessController displayController) {
		this.displayController = displayController;
	}

	@Override
	public void update(List<IEntity> entities) {
		// TODO Auto-generated method stub
		for(IEntity myEntity: entities){
			System.out.println(myEntity.toString());
			String imageToDisplay = "";
			double x = Integer.MIN_VALUE;
			double y = Integer.MIN_VALUE;
			double sizex = 80;
			double sizey = 100;
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
<<<<<<< HEAD
			frontEndController.createCharacterImage(x, y, imageToDisplay, sizex, sizey);

=======
			
			displayController.createCharacterImage(x, y, imageToDisplay, sizex, sizey);
>>>>>>> 75596e71e7d6e24926d79378b45fe8a0dafd53e4
		}
	}


	//@Override
	public void execute(List<Level> list) {
		// TODO Auto-generated method stub
		for(Level each: list){
			System.out.println(each.toString());
			//frontEndController.createCharacterImage(x, y, imageToDisplay, sizex, sizey);

		}
	}


}
