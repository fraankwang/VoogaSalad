package engine.frontend;

import engine.controller.EngineController;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShopView {
	
	private EngineView myEngineView;
	String myType;
	private ImageView myImageView;
	
	public ShopView(EngineView ev, double xLoc, double yLoc, String image, String type, double width, double height){
		myEngineView = ev;
		myType = type;
		myImageView = new ImageView(new Image(image));
		myImageView.setX(xLoc);
		myImageView.setY(yLoc);
		myImageView.setFitWidth(width);
		myImageView.setFitHeight(height);
		myImageView.setOnDragDetected(e -> handleClick());
	}

	public ShopView(EngineView ev){
		myType = "Drumpf";
		myEngineView = ev;
		Image myImage = new Image("DrumpfVader.png");
		myImageView = new ImageView(myImage);
		myImageView.setX(0);
		myImageView.setY(0);
		myImageView.setFitWidth(80);
		myImageView.setFitHeight(80);
		myImageView.setOnMousePressed(e -> handleClick());
	}
	
	
	public void handleClick(){
		Image myCursorImage = myImageView.getImage();
		myEngineView.getEngineController().shopClicked(myType);
		myEngineView.getStage().getScene().setCursor(new ImageCursor(myCursorImage));
	}
	
	public Node getNode(){
		return myImageView;
	}
}
