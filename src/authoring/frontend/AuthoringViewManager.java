package authoring.frontend;

import authoring.frontend.display_elements.MenuBarElement;
import authoring.frontend.display_elements.TabBarElement;
import authoring.frontend.editor_features.ObjectChooser;
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
 * MenuBarElement and the TabBarElement. The highest level UI elements are
 * initialized through this class' initialize method. This class is directly
 * modified by the main GameAuthoring class.
 * 
 * @author Frank, benchesnut
 *
 */

public class AuthoringViewManager implements IViewManager {

	private static final int SCENE_WIDTH = 1200;
	private static final int SCENE_HEIGHT = 800;

	private Scene myPrimaryScene;
	private IMenuBarElement myMenuBar;
	private ITabBarElement myTabBar;
	private ObjectChooser myImageChooser;
	private IAuthoringView myController;

	public AuthoringViewManager(IAuthoringView controller) {
		myController = controller;

	}

	@Override
	public void initialize(Stage s) {
		myTabBar = new TabBarElement(myController);
		myTabBar.initialize();

		myImageChooser = new ObjectChooser();
		myImageChooser.initialize();

		myMenuBar = new MenuBarElement(myImageChooser);
		myMenuBar.initialize();
		myMenuBar.link(myTabBar);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(myMenuBar.getNode());
		borderPane.setCenter(myTabBar.getNode());

		myPrimaryScene = new Scene(borderPane, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);

		myController.setPrimaryScene(myPrimaryScene);
		s.setScene(myPrimaryScene);
		s.show();

		myTabBar.initializeHotKeys();
	}

	@Override
	public IMenuBarElement getMenuBarElement() {
		return myMenuBar;
	}

	@Override
	public ITabBarElement getTabBarElement() {
		return myTabBar;
	}

	@Override
	public ObjectChooser getImageChooser() {
		return myImageChooser;
	}

}
