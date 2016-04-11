package authoring_environment.frontend.display_elements;

import authoring_environment.frontend.display_elements.tab_displays.EntitiesTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.GameTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.LevelsTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.ModesTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabBarElement;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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

	public TabBarElement() {
		myTabPane = new TabPane();
		myGameTabDisplay = new GameTabDisplay();
		myModesTabDisplay = new ModesTabDisplay();
		myLevelsTabDisplay = new LevelsTabDisplay();
		myEntitiesTabDisplay = new EntitiesTabDisplay();
	}

	@Override
	public Node buildNode() {
		Tab gameTab = createTab("Game", myGameTabDisplay.buildNode());
		Tab modeTab = createTab("Modes", myModesTabDisplay.buildNode());
		Tab levelTab = createTab("Levels", myLevelsTabDisplay.buildNode());
		Tab entityTab = createTab("Entities", myEntitiesTabDisplay.buildNode());
		myTabPane.getTabs().addAll(gameTab, modeTab, levelTab, entityTab);
		return myTabPane;
	}

	private Tab createTab(String name, Node content) {
		Tab t = new Tab(name, content);
		t.setClosable(false);
		t.setStyle("-fx-font-size: 24px;" + "-fx-font: Courier New;" + "-fx-font-weight: bold;");

		// set listeners to change tab stuff?
		return t;
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
	public void show(ITabDisplay display) {
		// TODO Auto-generated method stub

	}

	@Override
	public TabDisplay getEntitiesTabDisplay() {
		return myEntitiesTabDisplay;
	}

}
