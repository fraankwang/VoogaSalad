package authoring_environment.frontend.display_elements.panels;

import authoring_environment.frontend.display_elements.panels.panel_bars.PanelBar;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

/**
 * The GridViewPanel is the primary display within many of the TabDisplays. The
 * GridView contains a scrollable GridPane that shows all the existing game
 * sprites (or levels/modes) created. The PanelBar allows  
 * 
 * @author Frank
 *
 */

public class GridViewPanel extends Panel {

	private GridPane myGridPane;
	private ScrollPane myScrollPane;
	private PanelBar myPanelBar;
	private Button myAddNewButton;

	public GridViewPanel(int height, int width) {
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
