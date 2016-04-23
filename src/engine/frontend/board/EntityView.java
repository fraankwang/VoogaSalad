package engine.frontend.board;
/**
 * @author austinwu
 */
import engine.controller.EngineController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityView {
	
	private EngineController myController;
	
	private double myX;
	private double myY;
	private String myImageName;
	private ImageView myImageView;
	private int myID;
	private double myW;
	private double myH;
	
	public EntityView(EngineController controller, double xLoc, double yLoc, String image, int id, double width, double height){
		myController = controller;
		myX = xLoc;
		myY = yLoc;
		myImageName = image;
		myID = id;
		myW = width;
		myH = height;
		
		//create imageview
		myImageView = new ImageView(new Image(myImageName));
		myImageView.setX(myX);
		myImageView.setY(myY);
		myImageView.setFitWidth(myW);
		myImageView.setFitHeight(myH);
		myImageView.setOnMouseClicked(e -> handleClick());
	}
	
	public void handleClick(){
		myController.entityClicked(myID);
	}
		
	public Node getNode(){
		return myImageView;
	}
	
	public void update(double xLoc, double yLoc, String image, double width, double height){
		if(myX != xLoc){
			myX = xLoc;
			myImageView.setX(myX);
		}
		if(myY != yLoc){
			myY = yLoc;
			myImageView.setY(myY);
		}
		if(!myImageName.equals(image) ){
			myImageName = image;
			myImageView.setImage(new Image(image));
		}
		if(myW != width){
			myW = width;
			myImageView.setFitWidth(myW);
		}
		if(myH != height){
			myH = height;
			myImageView.setFitHeight(myH);
		}
	}
}
