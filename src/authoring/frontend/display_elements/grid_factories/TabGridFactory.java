package authoring.frontend.display_elements.grid_factories;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;

/**
 * The TabGridFactory superclass is responsible for creating the additional
 * unmodifiable attributes Panel UI element. TabGridFactories also have access
 * to their TabDisplay so that they may access their EditorDisplays.
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
	public abstract UnmodifiableAttributesPanel createUnmodifiableAttributesPanel(TabDisplay tabDisplay);

}
