package authoring.frontend;

import authoring.frontend.interfaces.EditorInterface;
import authoring.frontend.interfaces.display.GameDisplayInterface;
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
