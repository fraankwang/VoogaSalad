package authoring_environment.frontend.display_elements.grids;

import javafx.scene.Node;

/**
 * The EditorGrid superclass is a subset of Grid, which is in all the EditorDisplays.
 * The extra functionality that EditorGrids have is the creation of an attributes
 * panel (for which all the attributes displayed can be modified).
 * 
 * @author Frank, benchesnut
 *
 */

public abstract class EditorGrid extends Grid {

	protected Node myRulesPanel;
	protected Node myModifiableAttributesPanel;
	
	/**
	 * @return instantiated and formatted myRulesPanel
	 */
	protected abstract Node createRulesPanel();
	
	/**
	 * @return instantiated and formatted myModifiableAttributesPanel
	 */
	protected abstract Node createModifiableAttributesPanel();	

}
