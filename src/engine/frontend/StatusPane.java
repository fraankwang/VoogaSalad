package engine.frontend;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class StatusPane {
	private EngineView myEngineView;
	
	public StatusPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){
		Rectangle rect = new Rectangle();
		rect.setWidth(myEngineView.loadUIIntResource("StatusWidth"));
		rect.setHeight(myEngineView.loadUIIntResource("StatusHeight"));
		return rect;
	}
}
