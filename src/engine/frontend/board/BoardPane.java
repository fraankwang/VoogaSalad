package engine.frontend.board;

import java.util.HashMap;
import java.util.Map;

import engine.frontend.overall.EngineView;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BoardPane {
	private EngineView myEngineView;	
	
	private Pane myPane;
	private ImageView myBackground;
	
	private Map<Integer, EntityView> myEntityViewMap = new HashMap<Integer, EntityView>();
	
	public BoardPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding){
		myPane = new Pane();
		
		bindWidth(widthBinding);
		bindHeight(heightBinding);
		
		myBackground = new ImageView(new Image(myEngineView.getEngineController().getBackgroundImageFile()));
		myBackground.fitWidthProperty().bind(myPane.widthProperty());
		myBackground.fitHeightProperty().bind(myPane.heightProperty());
		myPane.getChildren().add(myBackground);
		return myPane;
	}
	

	private void bindWidth(DoubleExpression db){
		myPane.minWidthProperty().bind(db);
		myPane.maxWidthProperty().bind(db);
	}
	
	private void bindHeight(DoubleExpression db){
		myPane.minHeightProperty().bind(db);
		myPane.maxHeightProperty().bind(db);
	}
	
	
	public Pane getNode(){
		return myPane;
	}

	public void setBackground(String imageName){
		myBackground.setImage(new Image(imageName));
	}
	
	/**
	 * updates entity with id to correct coordinate and size, if size is negative 
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
	
	private void deleteEntity(int id){
		if(myEntityViewMap.containsKey(id)){
			myPane.getChildren().remove(myEntityViewMap.get(id).getNode());
			myEntityViewMap.remove(id);
		}
	}

	public void attemptTower(double mouseXLoc, double mouseYLoc, String placingTower){
		// need to tell them which type it is too
		double xLoc = mouseXLoc - myPane.getBoundsInParent().getMinX();
		double yLoc = mouseYLoc - myPane.getBoundsInParent().getMinY();

		System.out.println("X location: " + xLoc + "\nY location: " + yLoc);
		myEngineView.getEngineController().attemptTower(xLoc,  yLoc, placingTower);	

	}
}
