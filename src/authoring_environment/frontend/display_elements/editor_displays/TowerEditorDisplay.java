package authoring_environment.frontend.display_elements.editor_displays;

import authoring_environment.frontend.display_elements.grids.editor_grids.TowerEditorGrid;
import authoring_environment.frontend.interfaces.IDisplayEntity;
import authoring_environment.frontend.interfaces.display_element_interfaces.editor_display_interfaces.IEditorDisplays.ITowerEditorDisplay;
import javafx.scene.Node;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class TowerEditorDisplay implements ITowerEditorDisplay {

	private TowerEditorGrid myTowerEditorGrid;

	public TowerEditorDisplay() {
		myTowerEditorGrid = new TowerEditorGrid();

	}

	@Override
	public IDisplayEntity edit(IDisplayEntity oldEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node buildNode() {
		// TODO Auto-generated method stub
		return null;
	}

}
