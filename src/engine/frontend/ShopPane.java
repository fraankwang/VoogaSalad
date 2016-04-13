package engine.frontend;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class ShopPane {
	private EngineView myEngineView;
	
	public ShopPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){
		Rectangle rect = new Rectangle();
		rect.setWidth(myEngineView.loadUIIntResource("TowerWidth"));
		rect.setHeight(myEngineView.loadUIIntResource("TowerHeight"));
		return rect;
	}
}
