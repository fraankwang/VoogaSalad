package authoring_environment.frontend.display_elements.panels.panel_bars;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * The GridPanelBar contains a description as well as controls to change the
 * number of columns displaying the various sprites (or modes/levels).
 * 
 * @author Frank
 *
 */

public class GridPanelBar extends PanelBar {

	private HBox myDescription;
	private HBox myGridDimensionControls;
	private Button myIncreaseColumnsButton;
	private Button myDecreaseColumnsButton;

	public GridPanelBar(int height, int width) {
		super(height, width);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void assembleComponents() {
		// TODO Auto-generated method stub

	}

}
