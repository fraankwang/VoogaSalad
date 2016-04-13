package authoring.frontend;

import authoring.frontend.interfaces.display.DisplayInterface;
import authoring.frontend.interfaces.display.EnemiesDisplayInterface;
import authoring.frontend.interfaces.display.GameDisplayInterface;
import authoring.frontend.interfaces.display.LevelsDisplayInterface;
import authoring.frontend.interfaces.display.ModesDisplayInterface;
import authoring.frontend.interfaces.display.TabBarInterface;
import authoring.frontend.interfaces.display.TowersDisplayInterface;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabBar implements TabBarInterface {

	private TabPane tabBar;
	private GameDisplayInterface gameDisplay;
	private ModesDisplayInterface modeDisplay;
	private LevelsDisplayInterface levelDisplay;
	private TowersDisplayInterface towerDisplay;
	private EnemiesDisplayInterface enemyDisplay;

	public TabBar() {
		tabBar = new TabPane();
		gameDisplay = new GameDisplay();
		modeDisplay = new ModesDisplay();
		levelDisplay = new LevelsDisplay();
		towerDisplay = new TowersDisplay();
		enemyDisplay = new EnemiesDisplay();
	}
	
	@Override
	public Node buildNode() {
		Tab gameTab = createTab("Game", gameDisplay.buildNode());
		Tab modeTab = createTab("Modes", modeDisplay.buildNode());
		Tab levelTab = createTab("Levels", levelDisplay.buildNode());
		Tab towerTab = createTab("Towers", towerDisplay.buildNode());
		Tab enemyTab = createTab("Enemies", enemyDisplay.buildNode());
		tabBar.getTabs().addAll(gameTab, modeTab, levelTab, towerTab, enemyTab);
		return tabBar;
	}
	
	private Tab createTab(String name, Node content) {
		Tab t = new Tab(name, content);
		t.setClosable(false);
		return t;
	}

	@Override
	public DisplayInterface getGameDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DisplayInterface getModesDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DisplayInterface getLevelsDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DisplayInterface getTowersDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DisplayInterface getEnemiesDisplay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void show(DisplayInterface display) {
		// TODO Auto-generated method stub
		
	}

}
