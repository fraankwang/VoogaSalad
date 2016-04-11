package authoring_environment.frontend.interfaces.display_element_interfaces;

import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;

/**
 * The ITabBarElement allows for the easy creation of additional TabBar types,
 * provided they contain the relevant game components defined as methods in this
 * interface.
 * 
 * @author Frank, benchesnut
 *
 */

public interface ITabBarElement extends IDisplayElement {

	void show(ITabDisplay display);

	TabDisplay getGameTabDisplay();

	TabDisplay getModesTabDisplay();

	TabDisplay getLevelsTabDisplay();
	
	TabDisplay getEntitiesTabDisplay();
}
