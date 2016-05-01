package authoring.frontend;

import authoring.frontend.configuration.Constants;
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
import main.Main;

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

	private static final int SCENE_WIDTH = Constants.getInt("SCENE_WIDTH");
	private static final int SCENE_HEIGHT = Constants.getInt("SCENE_HEIGHT");

	private Scene myPrimaryScene;
	private IMenuBarElement myMenuBar;
	private ITabBarElement myTabBar;
	private ObjectChooser myObjectChooser;
	private IAuthoringView myController;
	private Main myMain;
	private Stage myStage;

	public AuthoringViewManager(IAuthoringView controller, Stage s, Main main) {
		myController = controller;
		myMain = main;
		myStage = s;

		myTabBar = new TabBarElement(myController);
		myObjectChooser = new ObjectChooser();
		myMenuBar = new MenuBarElement(myObjectChooser, myController, s, main);

	}

	@Override
	public void initialize(Stage s) {
		myTabBar = new TabBarElement(myController);
		myTabBar.initialize();
		myObjectChooser = new ObjectChooser();
		myObjectChooser.initialize();
		myMenuBar = new MenuBarElement(myObjectChooser, myController, myStage, myMain);
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
	public ObjectChooser getObjectChooser() {
		return myObjectChooser;
	}

}
