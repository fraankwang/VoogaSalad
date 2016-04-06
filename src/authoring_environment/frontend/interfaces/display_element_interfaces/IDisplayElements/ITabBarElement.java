package authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElements;

import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElement;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.ITabDisplay;

/**
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
