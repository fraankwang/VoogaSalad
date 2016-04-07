package authoring_environment.frontend.display_elements.grids;

import javafx.scene.Node;

/**
 * The TabGrid superclass is a subset of Grid, which is in all the TabDisplays.
 * The extra functionality that TabGrids have is the creation of an attributes
 * panel (for which all the attributes displayed cannot be modified).
 * 
 * @author Frank, benchesnut
 *
 */

public abstract class TabGrid extends Grid {

	protected Node myUnmodifiableAttributesPanel;

	/**
	 * @return instantiated and formatted myUnmodifiableAttributesPanel
	 */
	protected abstract Node createUnmodifiableAttributesPanel();
}
