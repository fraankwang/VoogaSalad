package authoring_environment.frontend.display_elements;

import authoring_environment.frontend.display_elements.tab_displays.EnemiesTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.GameTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.LevelsTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.ModesTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.TowersTabDisplay;
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
	private TabDisplay myTowersTabDisplay;
	private TabDisplay myEnemiesTabDisplay;

	public TabBarElement() {
		myTabPane = new TabPane();
		myGameTabDisplay = new GameTabDisplay();
		myModesTabDisplay = new ModesTabDisplay();
		myLevelsTabDisplay = new LevelsTabDisplay();
		myTowersTabDisplay = new TowersTabDisplay();
		myEnemiesTabDisplay = new EnemiesTabDisplay();
	}

	@Override
	public Node buildNode() {
		Tab gameTab = createTab("Game", myGameTabDisplay.buildNode());
		Tab modeTab = createTab("Modes", myModesTabDisplay.buildNode());
		Tab levelTab = createTab("Levels", myLevelsTabDisplay.buildNode());
		Tab towerTab = createTab("Towers", myTowersTabDisplay.buildNode());
		Tab enemyTab = createTab("Enemies", myEnemiesTabDisplay.buildNode());
		myTabPane.getTabs().addAll(gameTab, modeTab, levelTab, towerTab, enemyTab);
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
	public TabDisplay getTowersTabDisplay() {
		return myTowersTabDisplay;
	}

	@Override
	public TabDisplay getEnemiesTabDisplay() {
		return myEnemiesTabDisplay;
	}

	@Override
	public void show(ITabDisplay display) {
		// TODO Auto-generated method stub

	}

}
