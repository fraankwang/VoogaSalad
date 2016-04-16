package authoring.frontend;

import authoring.frontend.display_elements.MenuBarElement;
import authoring.frontend.display_elements.TabBarElement;
import authoring.frontend.interfaces.IViewManager;
import authoring.frontend.interfaces.display_element_interfaces.IMenuBarElement;
import authoring.frontend.interfaces.display_element_interfaces.ITabBarElement;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The AuthoringViewManager is responsible for initializing the stage, the
 * scene, and the BorderPane that contains the two primary UI elements: the
 * MenuBarElement and the TabBarElement.
 * 
 * @author Frank, benchesnut
 *
 */

public class AuthoringViewManager implements IViewManager {

	private static final int SCENE_WIDTH = 1200;
	private static final int SCENE_HEIGHT = 800;

	private IMenuBarElement myMenuBar;
	private ITabBarElement myTabBar;
	private IAuthoringView myController;

	public AuthoringViewManager(IAuthoringView controller) {
		myController = controller;
	}

	@Override
	public void initialize(Stage s) {
		myTabBar = new TabBarElement(myController);
		myTabBar.initialize();
		myMenuBar = new MenuBarElement();
		myMenuBar.initialize();
		myMenuBar.link(myTabBar);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(myMenuBar.getNode());
		borderPane.setCenter(myTabBar.getNode());

		Scene scene = new Scene(borderPane, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
		s.setScene(scene);
		s.show();
	}

	@Override
	public IMenuBarElement getMenuBarElement() {
		return myMenuBar;
	}

	@Override
	public ITabBarElement getTabBarElement() {
		return myTabBar;
	}

}
