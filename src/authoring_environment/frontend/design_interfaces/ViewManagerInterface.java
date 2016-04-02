package authoring_environment.frontend.design_interfaces;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;

/**
 * This interface will allow other components in the UI to have access to the
 * different tab options, including Game, Modes, Levels, Enemies, and Towers.
 * @author benchesnut
 *
 */
public interface ViewManagerInterface {

	Scene initializeScene();
	
	MenuBar getMenuBar();
	
	TabBarInterface getTabBar();
}
