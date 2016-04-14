package authoring.frontend.display_elements;

import authoring.frontend.interfaces.display_element_interfaces.IMenuBarElement;
import authoring.frontend.interfaces.display_element_interfaces.ITabBarElement;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * The MenuBarElement acts as a primary UI component that creates and manages
 * the MenuBar, which can be accessed at all times throughout the game authoring
 * environment.
 * 
 * @author Frank, benchesnut
 *
 */

public class MenuBarElement implements IMenuBarElement {

	private MenuBar myMenuBar;
	private ITabBarElement myTabBar;

	public MenuBarElement() {

	}

	@Override
	public Node getNode() {
		return myMenuBar;
	}

	@Override
	public void initialize() {
		myMenuBar = new MenuBar();
		Menu file = createFileMenu();
		Menu create = new Menu("Create");
		Menu help = new Menu("Help");

		myMenuBar.getMenus().addAll(file, create, help);
	}

	private Menu createFileMenu() {
		Menu file = new Menu("File");

		Menu open = new Menu("Open in separate window");
		MenuItem openGame = new MenuItem("Open Game Tab");
		openGame.setOnAction(e -> myTabBar.show(myTabBar.getGameTabDisplay()));
		MenuItem openModes = new MenuItem("Open Modes Tab");
		openModes.setOnAction(e -> myTabBar.show(myTabBar.getModesTabDisplay()));
		MenuItem openLevels = new MenuItem("Open Levels Tab");
		openLevels.setOnAction(e -> myTabBar.show(myTabBar.getLevelsTabDisplay()));
		MenuItem openEntities = new MenuItem("Open Entities Tab");
		openEntities.setOnAction(e -> myTabBar.show(myTabBar.getEntitiesTabDisplay()));

		file.getItems().add(open);
		open.getItems().addAll(openGame, openModes, openLevels, openEntities);
		return file;
	}

	@Override
	public void link(ITabBarElement tabBar) {
		myTabBar = tabBar;

	}

}
