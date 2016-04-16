package authoring.frontend.display_elements;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.tab_displays.EntitiesTabDisplay;
import authoring.frontend.display_elements.tab_displays.GameTabDisplay;
import authoring.frontend.display_elements.tab_displays.LevelsTabDisplay;
import authoring.frontend.display_elements.tab_displays.ModesTabDisplay;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import authoring.frontend.interfaces.display_element_interfaces.ITabBarElement;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The TabBar element is a main UI component that contains and creates all the
 * game-related tabs. If the game must be changed to incorporate or remove
 * components, the TabBar's TabPane is modified within this class, as each of
 * the Tab's contents are set to their respective TabDisplay's Node.
 * 
 * @author Frank, benchesnut
 *
 */

public class TabBarElement implements ITabBarElement {

	private TabPane myTabPane;
	private TabDisplay myGameTabDisplay;
	private TabDisplay myModesTabDisplay;
	private TabDisplay myLevelsTabDisplay;
	private TabDisplay myEntitiesTabDisplay;
	private static final int GAME_TAB_INDEX = 0;
	private static final int MODES_TAB_INDEX = 1;
	private static final int LEVELS_TAB_INDEX = 2;
	private static final int ENTITIES_TAB_INDEX = 3;
	private IAuthoringView myController;

	public TabBarElement(IAuthoringView controller) {
		myController = controller;
	}

	@Override
	public void initialize() {
		myTabPane = new TabPane();

		myGameTabDisplay = new GameTabDisplay(GAME_TAB_INDEX, myController);
		myGameTabDisplay.initialize();
		myModesTabDisplay = new ModesTabDisplay(MODES_TAB_INDEX, myController);
		myModesTabDisplay.initialize();
		myLevelsTabDisplay = new LevelsTabDisplay(LEVELS_TAB_INDEX, myController);
		myLevelsTabDisplay.initialize();
		myEntitiesTabDisplay = new EntitiesTabDisplay(ENTITIES_TAB_INDEX, myController);
		myEntitiesTabDisplay.initialize();

		Tab gameTab = createTab(myGameTabDisplay.getName(), myGameTabDisplay.getNode());
		Tab modeTab = createTab(myModesTabDisplay.getName(), myModesTabDisplay.getNode());
		Tab levelTab = createTab(myLevelsTabDisplay.getName(), myLevelsTabDisplay.getNode());
		Tab entityTab = createTab(myEntitiesTabDisplay.getName(), myEntitiesTabDisplay.getNode());
		myTabPane.getTabs().addAll(gameTab, modeTab, levelTab, entityTab);
	}

	@Override
	public Node getNode() {
		return myTabPane;
	}

	private Tab createTab(String name, Node content) {
		Tab t = new Tab(name, content);
		t.setClosable(false);
		t.setStyle("-fx-font-size: 24px;" + "-fx-font: Courier New;" + "-fx-font-weight: bold;");

		// set listeners to change tab stuff?
		return t;
	}

	private void removeTab(ITabDisplay tabDisplay) {
		myTabPane.getTabs().remove(tabDisplay.getTabIndex());

	}

	private void addTab(ITabDisplay tabDisplay) {
		Tab tab = createTab(tabDisplay.getName(), tabDisplay.getNode());
		myTabPane.getTabs().add(tabDisplay.getTabIndex(), tab);
	}

	@Override
	public TabDisplay getGameTabDisplay() {
		return myGameTabDisplay;
	}

	@Override
	public TabDisplay getModesTabDisplay() {
		return myModesTabDisplay;
	}

	@Override
	public TabDisplay getLevelsTabDisplay() {
		return myLevelsTabDisplay;
	}

	@Override
	public TabDisplay getEntitiesTabDisplay() {
		return myEntitiesTabDisplay;
	}

	@Override
	public void show(ITabDisplay display) {
		Stage s = new Stage();
		s.setTitle(display.getName());
		s.setOnCloseRequest(e -> addTab(display));
		
		BorderPane bp = new BorderPane();
		bp.setCenter(display.getNode());
		Scene scene = new Scene(bp, 1200, 800);
		s.setScene(scene);
		s.show();

		removeTab(display);
	}
	
}
