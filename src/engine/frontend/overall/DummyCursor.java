package engine.frontend.overall;

/*
 * @author HaydenBader
*/

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DummyCursor {

	private ImageView myImage;
	private EngineView myEngineView;
	
	public DummyCursor(EngineView ev){
		myEngineView = ev;

	}
	
	public Node buildNode(){
		myImage = new ImageView();
		myImage.fitWidthProperty().bind(myEngineView.getBoardPane().getPane().widthProperty().multiply(myEngineView.loadDoubleResource("CursorWidth")));
		myImage.fitHeightProperty().bind(myEngineView.getBoardPane().getPane().heightProperty().multiply(myEngineView.loadDoubleResource("CursorHeight")));
		myImage.setMouseTransparent(true);
		return myImage;
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
		
		if( myEngineView.getStage().getScene().getWidth() < x || x < 0 || y < 0 || y > myEngineView.getStage().getScene().getWidth()){
			if(myImage.isVisible()){
				myImage.setVisible(false);
			}
		}else{
			if(!myImage.isVisible()){
				myImage.setVisible(true);
			}
		}
		
	}
	
	
	
}
