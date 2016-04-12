package authoring.frontend.interfaces;

import authoring.frontend.interfaces.display.TabBarInterface;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

/**
 * This interface will allow other components in the UI to have access to the
 * different tab options, including Game, Modes, Levels, Enemies, and Towers.
 * @author benchesnut
 *
 */
public interface ViewManagerInterface {

	void initialize(Stage s);
	
	MenuBar getMenuBar();
	
	TabBarInterface getTabBar();
}
