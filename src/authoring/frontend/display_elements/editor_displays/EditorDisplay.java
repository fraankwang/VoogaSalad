package authoring.frontend.display_elements.editor_displays;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grids.Grid;
import authoring.frontend.interfaces.IDisplayEntity;
import authoring.frontend.interfaces.display_element_interfaces.IEditorDisplay;
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
	protected IAuthoringView myController;

	public EditorDisplay(IAuthoringView controller) {
		myController = controller;
	}

	@Override
	public Node getNode() {
		return myGrid.getNode();

	}

	@Override
	public IDisplayEntity edit(IDisplayEntity oldEntity) {
		return null;
	}

}
