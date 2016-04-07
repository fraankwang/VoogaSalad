package authoring_environment.frontend.display_elements.grids.tab_grids;

import authoring_environment.frontend.display_elements.grids.TabGrid;
import javafx.scene.Node;
import javafx.scene.control.TabPane;

/**
 * The LevelsTabGrid contains multiple tabs of user-created levels depending on
 * which Mode is selected (sets of levels correspond to each Mode created).
 * 
 * @author Frank, benchesnut
 *
 */

public class LevelsTabGrid extends TabGrid {

	private TabPane myTabPane;
	
	@Override
	protected void assembleGridComponents() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Node createUnmodifiableAttributesPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Node createPrimaryDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Node createButtonDashboard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Node createLeftSubGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Node createRightSubGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node buildNode() {
		return myTabPane;
	}
}
