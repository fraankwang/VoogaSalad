package authoring.frontend.interfaces.display_element_interfaces;

import authoring.frontend.editor_features.ImageImporter;

/**
 * The IMenuBarElement interface allows for easy creation of variations of a
 * MenuBarElement, the primary purpose of which is to contain the MenuBar and
 * all MenuBar related information.
 * 
 * @author benchesnut, Frank
 *
 */

public interface IMenuBarElement extends IDisplayElement {

	public void initialize();

	public void link(ITabBarElement myTabBar);
	
	ImageImporter getImageImporter();
}
