package authoring_environment.frontend;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.MenuBarElement;
import authoring_environment.frontend.display_elements.TabBarElement;
import authoring_environment.frontend.interfaces.IViewManager;
import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElements.IMenuBarElement;
import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElements.ITabBarElement;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The ViewManager is responsible for initializing the stage, the scene, and the
 * BorderPane that contains the two primary UI elements: the MenuBarElement and
 * the TabBarElement.
 * 
 * @author Frank, benchesnut
 *
 */

public class ViewManager implements IViewManager {

	private static final int SCENE_WIDTH = 1200;
	private static final int SCENE_HEIGHT = 800;

	private IMenuBarElement myMenuBar;
	private ITabBarElement myTabBar;
	private IController myController;

	public ViewManager(IController controller) {
		myController = controller;
	}

	@Override
	public void initialize(Stage s) {
		myMenuBar = new MenuBarElement();
		myTabBar = new TabBarElement();

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(myMenuBar.buildNode());
		borderPane.setCenter(myTabBar.buildNode());

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
