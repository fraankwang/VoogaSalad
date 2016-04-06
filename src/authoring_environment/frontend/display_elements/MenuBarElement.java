package authoring_environment.frontend.display_elements;

import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElements.IMenuBarElement;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class MenuBarElement implements IMenuBarElement {

	private MenuBar myMenuBar;
	
	public MenuBarElement() { 
		myMenuBar = new MenuBar();
		Menu file = new Menu("File");
		Menu create = new Menu("Create");
		Menu help = new Menu("Help");
		myMenuBar.getMenus().addAll(file, create, help);
	}
	
	@Override
	public Node buildNode() {
		return myMenuBar;
	}
	
}
