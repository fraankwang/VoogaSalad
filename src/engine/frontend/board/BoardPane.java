package engine.frontend.board;

import java.util.HashMap;
import java.util.Map;

import engine.frontend.overall.EngineView;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BoardPane {
	private EngineView myEngineView;	
	
	private Pane myPane;
	private ImageView myBackground;
	private DoubleBinding scalingFactor;
	private double aspectRatio;
	
	private Map<Integer, EntityView> myEntityViewMap = new HashMap<Integer, EntityView>();
	
	public BoardPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){
		myPane = new Pane();
		/*
		 * board width and board height need to be bound to the maximum possible size of the map given its aspect ratio
		 * that can fit within MaxBoardHeight% of the usable space 
		 */
		System.out.println(myEngineView.getEngineController().getEventManager());
		double mapHeight = myEngineView.getEngineController().getEventManager().getCurrentLevel().getMap().getMapHeight();
		double mapWidth= myEngineView.getEngineController().getEventManager().getCurrentLevel().getMap().getMapWidth();
		aspectRatio = mapHeight/mapWidth;
		//always scale the larger of the two to the boardsize
		//myEngineView.getUsableWidth(myEngineView.loadDoubleResource("ShopWidth"))
		
		/*
		 * 
		 * Lets say the height is greater than the width
		 * then the height of the pane is bound to the height of the map scaled to the boardheight 
		 * and the width of the pane is bound to the width of the map scaled by the same scale ratio^
		 */
		
		if(mapHeight > mapWidth){
			DoubleBinding usableHeight = myEngineView.getUsableHeight(myEngineView.loadDoubleResource("BoardMaxHeight"));
			scalingFactor = usableHeight.divide(mapHeight);
			bindHeight(usableHeight.multiply(scalingFactor));
			bindWidth((new SimpleDoubleProperty(mapWidth)).multiply(scalingFactor));
		} else {
			DoubleBinding usableWidth = myEngineView.getUsableWidth(myEngineView.loadDoubleResource("BoardMaxWidth"));
			scalingFactor = usableWidth.divide(mapWidth);
			bindWidth(usableWidth.multiply(scalingFactor));
			bindHeight((new SimpleDoubleProperty(mapHeight)).multiply(scalingFactor));
		}
		
		myBackground = new ImageView(new Image(myEngineView.getEngineController().getBackgroundImageFile()));
		myBackground.fitWidthProperty().bind(myPane.widthProperty());
		myBackground.fitHeightProperty().bind(myPane.heightProperty());
		myPane.getChildren().add(myBackground);
		return myPane;
	}
	
	private void bindHeight(DoubleBinding db){
		myPane.minHeightProperty().bind(db);
		myPane.maxHeightProperty().bind(db);
	}
	
	private void bindWidth(DoubleBinding db){
		myPane.minWidthProperty().bind(db);
		myPane.maxWidthProperty().bind(db);
	}
	
	
	public Node getNode(){
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
	
	public void attemptTower(double mouseXLoc, double mouseYLoc){
		// need to tell them which type it is too
		double xLoc = mouseXLoc - myPane.getBoundsInParent().getMinX();
		double yLoc = mouseYLoc - myPane.getBoundsInParent().getMinY();

		System.out.println("X location: " + xLoc + "\nY location: " + yLoc);
		
		myEngineView.getEngineController().attemptTower(xLoc,  yLoc);		

	}
}
