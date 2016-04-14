package authoring.frontend.display_elements.grid_factories;

import authoring.controller.IController;
import authoring.frontend.display_elements.panels.Panel;

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
	protected final int MAX_SIZE = Integer.MAX_VALUE;

	public GridFactory(IController controller) {
		myController = controller;
	}

	public abstract Panel createPrimaryDisplay();

	public abstract Panel createButtonDashboard();

}
