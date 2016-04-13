package engine.frontend;

import engine.controller.EngineController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityView {
	
	private int myID;
	private Node myNode;
	private EngineController myController;
	
	public EntityView(EngineController controller, double xLoc, double yLoc, String image, int id, double width, double height){
		myController = controller;
		myID = id;
		Image myImage = new Image(image);
		ImageView myImageView = new ImageView(myImage);
		myImageView.setX(xLoc);
		myImageView.setY(yLoc);
		myImageView.setFitWidth(width);
		myImageView.setFitHeight(height);
		myImageView.setOnMouseClicked(e -> handleClick());
		myNode = myImageView;
	}
	
	
	public void handleClick(){
		myController.entityClicked(myID);
	}

	public EntityView updateEntity(double xCoord, double yCoord, String image, int id, double width, double height) {
		// TODO Auto-generated method stub
		return null;
	}
}
