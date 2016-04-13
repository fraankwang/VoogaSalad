package authoring.frontend.display_elements.panels.panel_bars;

import authoring.frontend.display_elements.panels.Panel;

/**
 * The PanelBar is the object on top of the GridViewPanel or
 * EditorViewPanel. It acts as a mini-controller and display for the panel
 * specifically.
 * 
 * @author Frank
 *
 */

public abstract class PanelBar extends Panel {

	public PanelBar(int height, int width) {
		super(height, width);
	}

}
