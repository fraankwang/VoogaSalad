package authoring_environment.frontend.display_elements.grids;

import javafx.scene.Node;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public abstract class EditorGrid extends Grid {

	protected Node myRulesPane;
	protected Node myModifiableAttributesPanel;
	
	protected abstract Node createRulesPanel();
	
	protected abstract Node createModifiableAttributesPanel();	

}
