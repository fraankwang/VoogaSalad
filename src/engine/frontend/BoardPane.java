package engine.frontend;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class BoardPane {
	private EngineView myEngineView;
	
	public BoardPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){
		Rectangle rect = new Rectangle();
		rect.setWidth(myEngineView.loadUIIntResource("BoardSize"));
		rect.setHeight(myEngineView.loadUIIntResource("BoardSize"));
		return rect;
	}
}
