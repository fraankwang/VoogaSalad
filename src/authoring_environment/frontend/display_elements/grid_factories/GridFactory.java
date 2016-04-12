package authoring_environment.frontend.display_elements.grid_factories;

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

	public abstract Node createPrimaryDisplay();

	public abstract Node createButtonDashboard();

	public abstract Node createLeftSubGrid();

	public abstract Node createRightSubGrid();

}
