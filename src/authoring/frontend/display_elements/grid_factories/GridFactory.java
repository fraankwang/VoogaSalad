package authoring.frontend.display_elements.grid_factories;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.button_dashboards.ButtonDashboard;

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

	protected IAuthoringView myController;
	protected static final int MAX_SIZE = Integer.MAX_VALUE;

	public GridFactory(IAuthoringView controller) {
		myController = controller;
	}

	/**
	 * Responsible for returning the primary display on the Grid.
	 * 
	 * @return
	 */
	public abstract Panel createPrimaryDisplay();

	/**
	 * Responsible for returning the ButtonDashboard on the Grid. This
	 * ButtonDashboard could be Editor or Main.
	 * 
	 * @return
	 */
	public abstract ButtonDashboard createButtonDashboard();

}
