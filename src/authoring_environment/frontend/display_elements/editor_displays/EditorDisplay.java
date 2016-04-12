package authoring_environment.frontend.display_elements.editor_displays;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grids.Grid;
import authoring_environment.frontend.interfaces.IDisplayEntity;
import authoring_environment.frontend.interfaces.display_element_interfaces.IEditorDisplay;
import javafx.scene.Node;

/**
 * The EditorDisplay superclass is the Editor in charge of each game aspect's
 * modifiable attributes and rules.
 * 
 * @author Frank
 *
 */

public abstract class EditorDisplay implements IEditorDisplay {

	protected Grid myGrid;

	protected IController myController;

	public EditorDisplay(IController controller) {
		myController = controller;
	}
	
	@Override
	public Node buildNode() {
		return myGrid.buildNode();
	}

	@Override
	public IDisplayEntity edit(IDisplayEntity oldEntity) {
		return null;
	}

}
