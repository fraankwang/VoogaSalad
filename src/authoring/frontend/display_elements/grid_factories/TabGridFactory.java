package authoring.frontend.display_elements.grid_factories;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;

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
	
	public TabGridFactory(IAuthoringView controller, ITabDisplay tabDisplay) {
		super(controller);
		myTabDisplay = tabDisplay;
	}

	/**
	 * @return instantiated and formatted myUnmodifiableAttributesPanel
	 */
	public abstract Panel createUnmodifiableAttributesPanel(TabDisplay tabDisplay);

}
