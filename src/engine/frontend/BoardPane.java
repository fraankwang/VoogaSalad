package engine.frontend;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class BoardPane {
	private EngineView myEngineView;
	
	public BoardPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){
		
		GridPane gridpane = new GridPane();
		gridpane.setMinWidth(myEngineView.loadUIIntResource("BoardSize"));
		gridpane.setMaxWidth(myEngineView.loadUIIntResource("BoardSize"));
		gridpane.setMinHeight(myEngineView.loadUIIntResource("BoardSize"));
		gridpane.setMaxHeight(myEngineView.loadUIIntResource("BoardSize"));
//		gridpane.getChildren().add(new ImageView(new Image("DrumpfVader.png")));
		return gridpane;
	}
}
