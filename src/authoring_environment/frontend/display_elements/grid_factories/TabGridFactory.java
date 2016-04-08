package authoring_environment.frontend.display_elements.grid_factories;

import javafx.scene.Node;

/**
 * The TabGridFactory superclass is responsible for creating the additional
 * unmodifiable attributes Panel UI element.
 * 
 * 
 * @author Frank
 *
 */

public abstract class TabGridFactory extends GridFactory {

	/**
	 * @return instantiated and formatted myUnmodifiableAttributesPanel
	 */
	public abstract Node createUnmodifiableAttributesPanel();

}
