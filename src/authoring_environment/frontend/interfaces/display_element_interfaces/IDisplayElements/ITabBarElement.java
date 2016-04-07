package authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElements;

import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElement;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.ITabDisplay;

/**
 * The ITabBarElement allows for the easy creation of additional TabBar types,
 * provided they contain the relevant game components defined as methods in this
 * interface.
 * 
 * @author Frank, benchesnut
 *
 */

public interface ITabBarElement extends IDisplayElement {

	public void show(ITabDisplay display);

	public ITabDisplay getGameTabDisplay();

	public ITabDisplay getModesTabDisplay();

	public ITabDisplay getLevelsTabDisplay();

	public ITabDisplay getTowersTabDisplay();

	public ITabDisplay getEnemiesTabDisplay();
}
