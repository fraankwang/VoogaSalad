package authoring_environment.frontend;

import authoring_environment.frontend.design_interfaces.DisplayInterface;
import authoring_environment.frontend.design_interfaces.TabBarInterface;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TabPane;

public class TabBar implements TabBarInterface {

	@Override
	public Node buildNode() {
		TabPane tab = new TabPane();
		return tab;
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
