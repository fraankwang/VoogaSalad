/**
 * 
 * @author mario_oliver93
 * 
 */


package engine.backend.systems;

import java.util.List;

import engine.backend.FrontEndAccessController;
import engine.backend.Level;
import engine.backend.components.DisplayComponent;
import engine.backend.components.IComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.entities.IEntity;



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
		for(IEntity myEntity : entities){
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
			
			displayController.createCharacterImage(x, y, imageToDisplay, sizex, sizey);
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
