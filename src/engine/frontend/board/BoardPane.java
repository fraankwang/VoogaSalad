package engine.frontend.board;

import java.util.HashMap;
import java.util.Map;

import engine.frontend.overall.EngineView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class BoardPane {
	private EngineView myEngineView;
	private Map<Integer, EntityView> myEntityViewMap = new HashMap<Integer, EntityView>();
	private Pane myPane;
	private ImageView myBackground;	
	
	public BoardPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){
		myPane = new Pane();
		myPane.minWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("BoardWidth")));
		myPane.minHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("BoardHeight")));
		myPane.maxWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("BoardWidth")));
		myPane.maxHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("BoardHeight")));
		
		myBackground = new ImageView(new Image("Park_Path.png"));
		myBackground.fitWidthProperty().bind(myPane.widthProperty());
		myBackground.fitHeightProperty().bind(myPane.heightProperty());
		myPane.getChildren().add(myBackground);

		createCharacterImage(100, 100, "DrumpfVader.png", 1, 50, 50);
		createCharacterImage(400, 400, "DrumpfVader.png", 2, 50, 50);
		
		return myPane;
	}
	
	public Node getNode(){
		return myPane;
	}

	public void setBackground(String imageName){
		myBackground.setImage(new Image(imageName));
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
