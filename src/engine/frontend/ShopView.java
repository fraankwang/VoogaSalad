package engine.frontend;

import engine.controller.EngineController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShopView {
	
	private EngineController myController;
	String myType;
	
	public ShopView(EngineController controller, double xLoc, double yLoc, String image, String type, double width, double height){
		myController = controller;
		myType = type;
		ImageView myImageView = new ImageView(new Image(image));
		myImageView.setX(xLoc);
		myImageView.setY(yLoc);
		myImageView.setFitWidth(width);
		myImageView.setFitHeight(height);
		myImageView.setOnMouseClicked(e -> handleClick());
	}

	
	public void handleClick(){
		myController.towerClicked(myType);
	}
}
