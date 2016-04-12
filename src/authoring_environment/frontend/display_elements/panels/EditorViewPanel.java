package authoring_environment.frontend.display_elements.panels;

import authoring_environment.frontend.display_elements.panels.panel_bars.PanelBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;

/**
 * The EditorViewPanel is the primary display in the EditorDisplays. This
 * contains a large ImageView that displays the image and a PanelBar that allows
 * the user to upload an image, displays relevant information and stats, and
 * allows zooming in and out.
 * 
 * @author Frank
 *
 */

public class EditorViewPanel extends Panel {

	private ImageView myImageView;
	private ScrollPane myScrollPane;
	private PanelBar myPanelBar;

	public EditorViewPanel(int height, int width) {
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
