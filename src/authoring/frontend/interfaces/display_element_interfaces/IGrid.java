package authoring.frontend.interfaces.display_element_interfaces;

import authoring.frontend.display_elements.panels.Panel;

/**
 * The IGrid interface allows for multiple types of Grids (which populate
 * TabDisplays or EditorDisplays) to be easily created.
 * 
 * @author Frank, benchesnut
 *
 */

public interface IGrid extends IDisplayElement {

	public Panel getPrimaryDisplay();

	public void initializeHotKeys();

}
