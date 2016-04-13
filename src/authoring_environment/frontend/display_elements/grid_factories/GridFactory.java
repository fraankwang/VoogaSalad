package authoring_environment.frontend.display_elements.grid_factories;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.panels.Panel;
import javafx.scene.Node;

/**
 * The GridFactory superclass is responsible for creating the UI elements within
 * each TabDisplay's grid. GridFactory is not part of the IDisplayElement
 * interface because it exists simply to create UI elements while not being
 * displayed itself.
 * 
 * @author Frank
 *
 */

public abstract class GridFactory {
	
	private IController myController;
	protected final int ARBITRARY_PANEL_SIZE = 450;
	
	public GridFactory(IController controller) {
		myController = controller;
	}

	public abstract Panel createPrimaryDisplay();

	public abstract Panel createButtonDashboard();

	public abstract Node createLeftSubGrid();

	public abstract Node createRightSubGrid();

}
