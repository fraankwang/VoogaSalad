package authoring_environment.frontend.display_elements.tab_grids;

import authoring_environment.frontend.interfaces.display_element_interfaces.tab_element_interfaces.ITabGrids.IGameTabGrid;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class GameTabGrid implements IGameTabGrid {

	@Override
	public Node buildNode() {
		return new GridPane();
	}

}
