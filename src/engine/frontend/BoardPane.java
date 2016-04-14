package engine.frontend;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class BoardPane {
	private EngineView myEngineView;
	private Pane myPane;
	private Map<Integer, EntityView> myImageMap = new HashMap<>();
	
	public BoardPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){
		myPane = new Pane();
		myPane.setMinWidth(myEngineView.loadUIIntResource("BoardSize"));
		myPane.setMaxWidth(myEngineView.loadUIIntResource("BoardSize"));
		myPane.setMinHeight(myEngineView.loadUIIntResource("BoardSize"));
		myPane.setMaxHeight(myEngineView.loadUIIntResource("BoardSize"));
		myPane.setOnMouseClicked(e -> attemptTower(e.getSceneX(), e.getSceneY()));
		return myPane;
	}
	
	public void createCharacterImage(double xCoord, double yCoord, String image, int id, double width, double height){
		ImageView myPlayer = new ImageView(new Image(image));
		myPlayer.setFitWidth(width);
		myPlayer.setFitHeight(height);
		myPlayer.setX(xCoord);
		myPlayer.setY(yCoord);
		myPane.getChildren().add(myPlayer);
		
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
	public void updateEntity(double xCoord, double yCoord, String image, int id, double width, double height){
		if(myImageMap.containsKey(id)){
			myImageMap.get(id).updateEntity(xCoord, yCoord, image, width, height);
		} else {
			EntityView ev = new EntityView(myEngineView.getEngineController(), xCoord, yCoord, image, id, width, height);
			myImageMap.put(id, ev);
			myPane.getChildren().add(ev.getNode());
		}
	}
	
	public void deleteEntity(int id){
		myPane.getChildren().remove(myImageMap.get(id).getNode());
		myImageMap.remove(id);
	}
	
	public void attemptTower(double xLoc, double yLoc){
		myEngineView.getEngineController().attemptTower(xLoc, yLoc);
	}
}
