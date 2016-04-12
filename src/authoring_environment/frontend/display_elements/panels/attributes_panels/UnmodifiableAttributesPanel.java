package authoring_environment.frontend.display_elements.panels.attributes_panels;

import authoring_environment.frontend.display_elements.panels.Panel;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;

/**
 * The UnmodifiableAttributesPanel displays aspect-specific attributes, modifiable or not.
 * This panel will typically be part of the right subgrid.
 * 
 * @author Frank
 *
 */

public abstract class UnmodifiableAttributesPanel extends Panel {

	protected TabDisplay myTabDisplay;
	public UnmodifiableAttributesPanel(int height, int width, TabDisplay tabDisplay) {
		super(height, width);
		myTabDisplay = tabDisplay;
	}

}
