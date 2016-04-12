package authoring_environment.frontend.display_elements.panels.panel_bars;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * The EditorPanelBar contains a button to upload new image, a
 * description/stats, and the functionality to zoom in or out of the image.
 * 
 * @author Frank
 *
 */

public class EditorPanelBar extends PanelBar {

	private HBox myDescription;
	private Button myUploadImageButton;
	private HBox myZoomControls;
	private Button myZoomOutButton;
	private Button myZoomInButton;

	public EditorPanelBar(int height, int width) {
		super(height, width);
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
