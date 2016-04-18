package engine.frontend;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ShopPane {
	private EngineView myEngineView;
	private Pane myPane;
	
	public ShopPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){
		myPane = new Pane();
		myPane.setStyle("-fx-background-color: #000000;");
		myPane.setMinSize(myEngineView.loadUIIntResource("ShopWidth")/2, myEngineView.loadUIIntResource("ShopHeight")/2);
		myPane.setPrefSize(myEngineView.loadUIIntResource("ShopWidth"), myEngineView.loadUIIntResource("ShopHeight"));
		return myPane;
	}
}
