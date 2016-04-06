package authoring_environment.frontend.interfaces;

import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElements.IMenuBarElement;
import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElements.ITabBarElement;
import javafx.stage.Stage;

/**
 * This interface will allow other components in the UI to have access to the
 * different tab options, including Game, Modes, Levels, Enemies, and Towers.
 * @author benchesnut, Frank
 *
 */
public interface IViewManager {

	void initialize(Stage s);
	
	public IMenuBarElement getMenuBarElement();
	
	public ITabBarElement getTabBarElement();
}
