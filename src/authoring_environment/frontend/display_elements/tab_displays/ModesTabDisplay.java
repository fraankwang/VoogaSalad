package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.display_elements.grids.tab_grids.ModesTabGrid;
import authoring_environment.frontend.interfaces.display_element_interfaces.editor_display_interfaces.IEditorDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.ITabDisplays.IModesTabDisplay;
import javafx.scene.Node;

public class ModesTabDisplay implements IModesTabDisplay {

	private ModesTabGrid myModesTabGrid;

	public ModesTabDisplay() {
		myModesTabGrid = new ModesTabGrid();

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
