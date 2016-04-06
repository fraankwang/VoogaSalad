package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.interfaces.display_element_interfaces.editor_display_interfaces.IEditorDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.ITabDisplays.IGameTabDisplay;
import javafx.scene.Group;
import javafx.scene.Node;

public class GameTabDisplay implements IGameTabDisplay {
	
	public GameTabDisplay() {
		
	}

	@Override
	public IEditorDisplay getEditor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node buildNode() {
		// TODO Auto-generated method stub
		return new Group();
	}
	
}
