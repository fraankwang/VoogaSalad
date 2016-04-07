package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.frontend.display_elements.grids.tab_grids.EnemiesTabGrid;
import authoring_environment.frontend.interfaces.display_element_interfaces.editor_display_interfaces.IEditorDisplay;
import javafx.scene.Node;

public class EnemiesTabDisplay extends TabDisplay {

	private EnemiesTabGrid myEnemiesTabGrid;

	public EnemiesTabDisplay() {
		myEnemiesTabGrid = new EnemiesTabGrid();

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
