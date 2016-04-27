package authoring.frontend.interfaces;

import authoring.frontend.editor_features.ObjectChooser;
import authoring.frontend.interfaces.display_element_interfaces.IMenuBarElement;
import authoring.frontend.interfaces.display_element_interfaces.ITabBarElement;
import javafx.stage.Stage;

/**
 * This interface will allow other components in the UI to have access to the
 * different tab options, including Game, Modes, Levels, Enemies, and Towers.
 * 
 * @author benchesnut, Frank
 *
 */

public interface IViewManager {

	/**
	 * Creates the Scene and primary root for which the @param s is based on.
	 * 
	 * @param s
	 */
	void initialize(Stage s);

	IMenuBarElement getMenuBarElement();

	ITabBarElement getTabBarElement();

	ObjectChooser getImageChooser();
}
