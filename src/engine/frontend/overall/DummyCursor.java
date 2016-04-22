package engine.frontend.overall;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DummyCursor {

	private ImageView myImage;
	
	public DummyCursor(double width, double height){
		
		myImage = new ImageView();
		myImage.setFitWidth(width);
		myImage.setFitHeight(height);	
	}
	
	public Node getNode(){
		return myImage;
	}
	
	public void changePic(Image inImage){
		myImage.setImage(inImage);
	}
	
	public void updateLocation(double x, double y){
		myImage.setX(x);
		myImage.setY(y);
	}
	
	
	
}
