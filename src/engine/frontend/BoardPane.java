package engine.frontend;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
<<<<<<< HEAD
=======
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
>>>>>>> 6dd6b4a5cbc54195098583566868d7feb6b0e785

public class BoardPane {
	private EngineView myEngineView;
	private Pane myPane;
	
	public BoardPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){
		myPane = new Pane();
		myPane.setMinWidth(myEngineView.loadUIIntResource("BoardSize"));
		myPane.setMaxWidth(myEngineView.loadUIIntResource("BoardSize"));
		myPane.setMinHeight(myEngineView.loadUIIntResource("BoardSize"));
		myPane.setMaxHeight(myEngineView.loadUIIntResource("BoardSize"));
		return myPane;
	}
	
	public void createCharacterImage(double xCoord, double yCoord, String image, double width, double height){
		ImageView myPlayer = new ImageView(new Image(image));
		myPlayer.setFitWidth(width);
		myPlayer.setFitHeight(height);
		myPlayer.setX(xCoord);
		myPlayer.setY(yCoord);
		myPane.getChildren().add(myPlayer);
	}
}
