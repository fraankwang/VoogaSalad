package authoring_environment.frontend;

import authoring_environment.frontend.design_interfaces.EditorInterface;
import authoring_environment.frontend.design_interfaces.display.GameDisplayInterface;
import javafx.scene.Group;
import javafx.scene.Node;

public class GameDisplay implements GameDisplayInterface {
	
	public GameDisplay() {
		
	}

	@Override
	public EditorInterface getEditor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node buildNode() {
		// TODO Auto-generated method stub
		return new Group();
	}
	
}
