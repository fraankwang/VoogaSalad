package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.display_elements.grids.tab_grids.GameTabGrid;
import authoring_environment.frontend.interfaces.display_element_interfaces.editor_display_interfaces.IEditorDisplay;
import javafx.scene.Group;
import javafx.scene.Node;

public class GameTabDisplay extends TabDisplay {
	
	private GameTabGrid myGameTabGrid;
	
	public GameTabDisplay() {
		myGameTabGrid = new GameTabGrid();
		
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
