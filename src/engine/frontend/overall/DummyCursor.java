package engine.frontend.overall;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DummyCursor {

	private ImageView myImage;
	
	public DummyCursor(EngineView ev){
		
		myImage = new ImageView();
		myImage.fitWidthProperty().bind(ev.getStage().widthProperty().multiply(ev.loadDoubleResource("CursorWidth")));
		myImage.fitHeightProperty().bind(ev.getStage().heightProperty().multiply(ev.loadDoubleResource("CursorHeight")));	
	}
	
	public Node getNode(){
		return myImage;
	}
	
	public void changePic(Image inImage){
		myImage.setImage(inImage);
	}
	
	public void updateLocation(double x, double y){
		myImage.setX(x - myImage.getFitWidth()/2);
		myImage.setY(y - myImage.getFitHeight()/2);
	}
	
	
	
}
