package authoring_environment.frontend.display_elements.grid_factories;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.panels.Panel;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;

/**
 * The TabGridFactory superclass is responsible for creating the additional
 * unmodifiable attributes Panel UI element.
 * 
 * 
 * @author Frank
 *
 */

public abstract class TabGridFactory extends GridFactory {

	protected TabDisplay myTabDisplay;
	
	public TabGridFactory(IController controller, TabDisplay tabDisplay) {
		super(controller);
		myTabDisplay = tabDisplay;
	}

	/**
	 * @return instantiated and formatted myUnmodifiableAttributesPanel
	 */
	public abstract Panel createUnmodifiableAttributesPanel(TabDisplay tabDisplay);

}
