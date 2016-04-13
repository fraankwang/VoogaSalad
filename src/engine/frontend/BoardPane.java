package engine.frontend;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
	
	public void updateEntity(double xCoord, double yCoord, String image, int id, double width, double height){
		Integer myKey = new Integer(id);
		EntityView ev = new EntityView(myEngineView.getEngineController(), xCoord, yCoord, image, id, width, height);
		myImageMap.put(myKey, ev);
		myPane.getChildren().add(ev.getNode());
	}
	
	public void attemptTower(double xLoc, double yLoc){
		myEngineView.getEngineController().attemptTower(xLoc, yLoc);
	}
}
