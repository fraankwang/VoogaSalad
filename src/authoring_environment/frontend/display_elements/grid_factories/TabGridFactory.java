package authoring_environment.frontend.display_elements.grid_factories;

import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
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

	protected ITabDisplay myTabDisplay;
	
	public TabGridFactory(ITabDisplay tab) {
		myTabDisplay = tab;
	}
	/**
	 * @return instantiated and formatted myUnmodifiableAttributesPanel
	 */
	public abstract Node createUnmodifiableAttributesPanel();

}
