package authoring_environment.frontend.display_elements;

import authoring_environment.frontend.interfaces.display_element_interfaces.IMenuBarElement;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

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

	public MenuBarElement() {

	}

	
	@Override
	public Node getNode() {
		return myMenuBar;
	}

	@Override
	public void initialize() {
		myMenuBar = new MenuBar();
		Menu file = new Menu("File");
		Menu create = new Menu("Create");
		Menu help = new Menu("Help");
		myMenuBar.getMenus().addAll(file, create, help);
	}

}
