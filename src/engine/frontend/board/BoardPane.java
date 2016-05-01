/**
 * @author austinwu
 */
package engine.frontend.board;

import java.util.HashMap;
import java.util.Map;

import engine.backend.entities.Entity;
import engine.frontend.overall.AbstractPane;
import engine.frontend.overall.EngineView;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardPane extends AbstractPane{
	private ImageView myBackground;
	private Group myGroup;
	
	private Map<Integer, EntityView> myEntityViewMap;
	
	/**
	 * Instantiates BoardPane
	 * @param ev - engineView - main container for scene
	 */
	public BoardPane(EngineView ev){
		super(ev, null);
		myEntityViewMap = new HashMap<Integer, EntityView>();
	}
	
	/**
	 * Initializes BoardPane
	 */
	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding){
		super.buildNode(widthBinding, heightBinding);
		myBackground = new ImageView(new Image(myEngineView.getEngineController().getBackgroundImageFile()));
		myBackground.fitWidthProperty().bind(myPane.widthProperty());
		myBackground.fitHeightProperty().bind(myPane.heightProperty());
		myBackground.setMouseTransparent(true);
		myGroup = new Group();
		myPane.getChildren().addAll(myBackground, myGroup);
		return myPane;
	}
	
	/**
	 * Sets boardPane's background image
	 * @param imageName - string location for map image
	 */
	public void setBackground(String imageName){
		myBackground.setImage(new Image(imageName));
	}
	
	/**
	 * updates entity relative to map with id to correct coordinate and size, if size is negative 
	 * @param xCoord
	 * @param yCoord
	 * @param image
	 * @param id
	 * @param width
	 * @param height
	 */
	public void updateEntity(double xCoord, double yCoord, String image, int id, double width, double height, boolean show){
		if(!show){
			deleteEntity(id);
			return;
		}
		if(myEntityViewMap.containsKey(id)){
			myEntityViewMap.get(id).update(xCoord, yCoord, image, width, height);
		} else {			
			EntityView ev = new EntityView(myEngineView.getEngineController(), xCoord, yCoord, image, id, width, height);
			myEntityViewMap.put(id, ev);
			myPane.getChildren().add(ev.getNode());
		}
	}
	
	/**
	 * Deletes an entity from the map
	 * @param id - unique id of entity to be deleted
	 */
	private void deleteEntity(int id){
		if(myEntityViewMap.containsKey(id)){
			myPane.getChildren().remove(myEntityViewMap.get(id).getNode());
			myEntityViewMap.remove(id);
		}
	}

	/**
	 * Tells engine that a tower has been attempted to be placed at a location
	 * @param mouseXLoc - xPosition of tower to be placed
	 * @param mouseYLoc - yPosition of tower to be placed
	 * @param placingTower - String of tower type to be placed
	 */
	public void attemptTower(double mouseXLoc, double mouseYLoc, String placingTower){
		double xLoc = mouseXLoc - myPane.getLayoutX();
		double yLoc = mouseYLoc - myPane.getLayoutY();
		System.out.println("Tester");
		myEngineView.getEngineController().attemptTower(xLoc,  yLoc, placingTower);	
		System.out.println("Testing more");
		
	}
	
	/**
	 * Returns map containing unique ID to Entity displayed in board
	 * @return
	 */
	public Map<Integer, EntityView> getEntityMap(){
		return myEntityViewMap;
	}

	
	
}
