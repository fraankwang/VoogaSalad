package engine.frontend;

import engine.controller.EngineController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityView {
	
	private int myID;
	private ImageView myImageView;
	private EngineController myController;
	
	public EntityView(EngineController controller, double xLoc, double yLoc, String image, int id, double width, double height){
		myController = controller;
		myID = id;
		myImageView = new ImageView(new Image(image));
		myImageView.setX(xLoc);
		myImageView.setY(yLoc);
		myImageView.setFitWidth(width);
		myImageView.setFitHeight(height);
		myImageView.setOnMouseClicked(e -> handleClick());
	}
	
	public void handleClick(){
		myController.entityClicked(myID);
	}
	
	public Node getNode(){
		return myImageView;
	}
	
	public void updateEntity(double xCoord, double yCoord, String image, double width, double height){
		myImageView.setX(xCoord);
		myImageView.setY(yCoord);
		myImageView.setFitWidth(width);
		myImageView.setFitHeight(height);
		myImageView.setImage(new Image(image));
	}
}
