package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.display_elements.grids.tab_grids.LevelsTabGrid;
import authoring_environment.frontend.interfaces.display_element_interfaces.editor_display_interfaces.IEditorDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.ITabDisplays.ILevelsTabDisplay;
import javafx.scene.Node;

public class LevelsTabDisplay implements ILevelsTabDisplay {

	private LevelsTabGrid myLevelsTabGrid;

	public LevelsTabDisplay() {
		myLevelsTabGrid = new LevelsTabGrid();

	}

	@Override
	public IEditorDisplay getEditor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node buildNode() {
		// TODO Auto-generated method stub
		return null;
	}

}
