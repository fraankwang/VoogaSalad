package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.editor_displays.EditorDisplay;
import authoring_environment.frontend.display_elements.grids.Grid;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;

/**
 * The TabDisplay superclass acts as a container for it's Grid and corresponding
 * editor.
 * 
 * @author Frank
 *
 */

public abstract class TabDisplay implements ITabDisplay {

	protected EditorDisplay myEditorDisplay;
	protected Grid myGrid;
	protected IController myController;

	public TabDisplay(IController controller) {
		myController = controller;
	}

	@Override
	public Node buildNode() {
		return myGrid.buildNode();
	}

	@Override
	public EditorDisplay getEditorDisplay() {
		return myEditorDisplay;
	}

}
