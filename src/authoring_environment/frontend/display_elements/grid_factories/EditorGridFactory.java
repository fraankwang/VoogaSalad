package authoring_environment.frontend.display_elements.grid_factories;

import javafx.scene.Node;

/**
 * The EditorGridFactory superclass is responsible for creating the additional
 * modifiable attributes and Rules Panels UI elements.
 * 
 * 
 * @author Frank
 *
 */

public abstract class EditorGridFactory extends GridFactory {

	/**
	 * @return instantiated and formatted myRulesPanel
	 */
	public abstract Node createRulesPanel();
	
	/**
	 * @return instantiated and formatted myModifiableAttributesPanel
	 */
	public abstract Node createModifiableAttributesPanel();	
}
