package engine.frontend.board;
/**
 * @author austinwu
 */
import engine.controller.EngineController;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class EntityView {
	
	private EngineController myController;
	
	private DoubleProperty myX;
	private DoubleProperty myY;
	private String myImageName;
	private ImageView myImageView;
	private int myID;
	private DoubleProperty myW;
	private DoubleProperty myH;
	
	public EntityView(EngineController controller, double xLoc, double yLoc, String image, int id, double width, double height){
		myController = controller;
		myX = new SimpleDoubleProperty(xLoc);
		myY = new SimpleDoubleProperty(yLoc);
		myImageName = image;
		myID = id;
		myW = new SimpleDoubleProperty(width);
		myH = new SimpleDoubleProperty(height);

		myImageView = new ImageView(new Image(myImageName));		
		myImageView.translateXProperty().bind(myController.getEngineView().getScalingFactor().multiply(myX.subtract(myW.divide(2))));
		myImageView.translateYProperty().bind(myController.getEngineView().getScalingFactor().multiply(myY.subtract(myH.divide(2))));
		myImageView.fitWidthProperty().bind(myController.getEngineView().getScalingFactor().multiply(myW));
		myImageView.fitHeightProperty().bind(myController.getEngineView().getScalingFactor().multiply(myH));
		myImageView.setOnMouseClicked(e -> handleClick());
		myImageView.setOnDragDropped( e -> handlePowerUpDrop(e));
	}
	
	public void handleClick(){
		myImageView.requestFocus();
		myController.entityClicked(myID);
	}
		
	public Node getNode(){
		return myImageView;
	}
	
	public void update(double xLoc, double yLoc, String image, double width, double height){
		if(myX.doubleValue() != xLoc){
			myX.setValue(xLoc);
		}
		if(myY.doubleValue() != yLoc){
			myY.setValue(yLoc);
		}
		if(!myImageName.equals(image) ){
			myImageName = image;
			myImageView.setImage(new Image(image));
		}
		if(myW.doubleValue() != width){
			myW.setValue(width);
		}
		if(myH.doubleValue() != height){
			myH.setValue(height);
		}
	}
	
	private void handlePowerUpDrop(DragEvent e){
		e.acceptTransferModes(TransferMode.ANY);
		if (e.getDragboard().hasString()) {
			myController.attemptUpgrade(myID, e.getDragboard().getString());

		}
		e.consume();
		
	}
}
