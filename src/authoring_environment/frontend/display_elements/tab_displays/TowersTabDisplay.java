package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.display_elements.grids.tab_grids.TowersTabGrid;
import authoring_environment.frontend.interfaces.display_element_interfaces.editor_display_interfaces.IEditorDisplay;
import javafx.scene.Node;

public class TowersTabDisplay extends TabDisplay {

	private TowersTabGrid myTowersTabGrid;

	public TowersTabDisplay() {
		myTowersTabGrid = new TowersTabGrid();

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
