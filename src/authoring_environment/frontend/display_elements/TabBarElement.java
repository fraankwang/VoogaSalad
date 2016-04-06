package authoring_environment.frontend.display_elements;

import authoring_environment.frontend.display_elements.tab_displays.EnemiesTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.GameTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.LevelsTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.ModesTabDisplay;
import authoring_environment.frontend.display_elements.tab_displays.TowersTabDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElements.ITabBarElement;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.*;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.ITabDisplays.IEnemiesTabDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.ITabDisplays.IGameTabDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.ITabDisplays.ILevelsTabDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.ITabDisplays.IModesTabDisplay;
import authoring_environment.frontend.interfaces.display_element_interfaces.tab_display_interfaces.ITabDisplays.ITowersTabDisplay;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class TabBarElement implements ITabBarElement {

	private TabPane myTabBar;
	private IGameTabDisplay myGameTabDisplay;
	private IModesTabDisplay myModesTabDisplay;
	private ILevelsTabDisplay myLevelsTabDisplay;
	private ITowersTabDisplay myTowersTabDisplay;
	private IEnemiesTabDisplay myEnemiesTabDisplay;

	public TabBarElement() {
		myTabBar = new TabPane();
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
		myTabBar.getTabs().addAll(gameTab, modeTab, levelTab, towerTab, enemyTab);
		return myTabBar;
	}

	private Tab createTab(String name, Node content) {
		Tab t = new Tab(name, content);
		t.setClosable(false);
		t.setStyle("-fx-font-size: 24px;" + "-fx-font: Courier New;" + "-fx-font-weight: bold;");

		// set listeners to change tab stuff?
		return t;
	}

	@Override
	public ITabDisplay getGameTabDisplay() {
		return myGameTabDisplay;
	}

	@Override
	public ITabDisplay getModesTabDisplay() {
		return myModesTabDisplay;
	}

	@Override
	public ITabDisplay getLevelsTabDisplay() {
		return myLevelsTabDisplay;
	}

	@Override
	public ITabDisplay getTowersTabDisplay() {
		return myTowersTabDisplay;
	}

	@Override
	public ITabDisplay getEnemiesTabDisplay() {
		return myEnemiesTabDisplay;
	}

	@Override
	public void show(ITabDisplay display) {
		// TODO Auto-generated method stub

	}

}
